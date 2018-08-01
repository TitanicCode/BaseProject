package com.custom.distributelock.redislock;


import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.CountDownLatch;

/**
 * Created by jackiechan on 18-7-24/下午2:59
 */
public class TestZookeeper {
    //注意这里不要出现http
    private static final String CONNECTION_ADD = "qianfeng.qfjava.cn:8091";
    private static final int TIME_OUT = 5000;
    static int i = 10;

    public static void decr() {
        i--;
        System.out.println(i);
    }
    //实际开发中，下面很多代码都是不需要的，比如为了测试高并发引入的countDownLatch等等等
    public static void main(String[] args) throws InterruptedException {
        //上面设置的超时时间为5秒，当超时候，每隔1000毫秒会重试连接一次，最多尝试10次
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 10);
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder().connectString(CONNECTION_ADD).sessionTimeoutMs(TIME_OUT).retryPolicy(retryPolicy).build();
        curatorFramework.start();
        //信息量，用于阻塞线程
        CountDownLatch countDownLatch = new CountDownLatch(1);
        for (int i1 = 0; i1 < 10; i1++) {
            new Thread(){

                @Override
                public void run() {
                //在这里创建的对象是属于每个现成的线程,模拟的话就相当于每台电脑的对象
                    InterProcessMutex lock =new InterProcessMutex(curatorFramework,"/abc/123");
                    try {
                        //为了演示所有人一起买，当线程创建之后就会进入这个等待，但是程序会继续运行，当for循环结束后，这个等待里面就有了10个线程
                        //然后countDownLatch.countDown()一执行，这里存的10个线程就会一起运行，从而相当于10台不同的电脑在一起请求服务
                        countDownLatch.await();
                        //判断剩余量
                        lock.acquire();//抢锁,抢到的会继续执行,抢不到的就等待
                        //调用静态方法
                        decr();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }finally {
                        try {
                            lock.release();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }


                }
            }.start();
        }


        countDownLatch.countDown();
        Thread.sleep(10000000);
    }
}
