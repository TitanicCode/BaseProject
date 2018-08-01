package com.custom.distributelock.redislock;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2018/7/31.
 */
public class Redislock {

    public void buySimple(){
        Jedis jedis=new Jedis("10.9.166.125",8100);
        //jedis.auth("radis001");
        jedis.decrBy("item1000",2000);
        jedis.close();
    }
    @Test
    //问题：这样当有10个人同时访问该函数时，会把redis中item1000的值一直减到-10000，这样是不是不科学，我们redis中存的10000可是钱啊，钱还能负数喽啊
    //解决办法：加锁，但是要考虑到要加分布式锁，因为这个锁必须要实现所有服务器共享
    public void testBuySimple(){
        for(int i=0;i<10;i++){
            buySimple();
        }
    }

    //这种方案可以再加上判断，一旦redis中的10000的值没了，可以判断一下，然后不再允许客户访问该函数
    //但是这个方法有个问题，你会发现无论设置有效期放在哪个位置，只要服务器在执行jedis.setnx("goumai1000", "redislocal");时坏掉，这个锁就打不开了
    //解决办法：将有效期设置和setnx函数放置到一条语句
    public void BuyNx() throws InterruptedException {
        Jedis jedis=new Jedis("10.9.166.125");
        //jedis.auth("redis001");
        //当setnx值为1时表示键设置成功，否则设置失败，nx函数不允许重复键载入
        Long setnx = jedis.setnx("goumai1000", "redislocal");
        if(setnx==1){
            jedis.decrBy("item1000",2000);
            jedis.del("goumai1000");
            //jedis的有效期要比业务消费时间多，你总不能业务还没走完，他就失效了吧
            jedis.expire("goumai1000",20000);
            Thread.sleep(10000);
        }else{
            System.out.println("有人正在购买");
        }
        jedis.close();
    }

    public void buyRedisLockFinally() {
        Jedis jedis=new Jedis("10.9.166.125",8100);
        //jedis.auth("radis001");
        //先判断钱还够不够,不够直接返回

        //有效期设置和nx函数执行在同一条语句，解决上面函数遗留的问题
        //time单位是秒
        //String set = jedis.set("goumai1000", "redislock", "nx", "ex", 10);
        Random random = new Random();
        String set = jedis.set("goumai1000", "suibian"+random.nextInt(1000000), "nx", "ex", 10);

        if (set!=null) {
            //添加监听是为了解决以下问题：当用户过了set的判断之后万一宕机了，当它的key过了有效期之后，又醒过了了，这个时候是绝对不能允许他继续往下执行的
            jedis.watch("goumai1000");//监听自己加的锁
            //使用线程目的是为了执行失败可以回滚，配合watch
            Transaction transaction = jedis.multi();

            //这里是为了模拟用户中途宕机
//            try {
//                Thread.sleep(22000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            transaction.decrBy("item1000", 2000);

            //这里是为了判断key过期了导致数据不见了，但是结果没有失败
            Jedis jedis1=new Jedis("10.9.166.125",8100);
            //jedis.auth("radis001");
            String goumai1000 = jedis1.get("goumai1000");
            if (goumai1000 == null) {
                //事物销毁
                transaction.discard();
                jedis.unwatch();
            }else{
                //如果监听的key还有值,则继续,这个值如果是被人覆盖的,则本身就会失败
                List<Object> exec = transaction.exec();
                jedis.del("goumai1000");
                System.out.println(exec);
            }
            // jedis.unwatch();
        }else{
            System.out.println("有人正在购买");
            //等待别人执行完成,可以不断重试,递归重试,可以wait ,等待唤醒
            //等待发送消息,接收到消息唤醒
        }
    }

    @Test
    public void test1() {
        for (int i = 0; i < 10; i++) {
            //使用线程模拟高并发
            new Thread(){
                @Override
                public void run() {
                    buyRedisLockFinally();
                }
            }.start();
        }
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}
