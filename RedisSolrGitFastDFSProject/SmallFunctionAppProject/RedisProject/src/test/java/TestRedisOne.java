import org.junit.Test;
import redis.clients.jedis.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2018/7/12.
 */
public class TestRedisOne {

    @Test
    public void TestRedisOne(){
        //按照面向对象思想,要想操作一个对象,要么是创建对象,要么是静态方法
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.connect();
        jedis.auth("123456");
        String userinfo = jedis.get("userinfo");
        System.out.println(userinfo);
        jedis.close();
    }

    //测试连接池
    @Test
    public void TestPool(){
        JedisPool jedisPool=new JedisPool("127.0.0.1", 6379);
        Jedis jedis = jedisPool.getResource();
        jedis.connect();
        jedis.auth("123456");
        String userinfo = jedis.get("userinfo");
        System.out.println(userinfo);
        jedis.close();
    }

    //测试集群
    @Test
    public void TestJiQun() {
        Set<HostAndPort> nodes = new HashSet<>();
        HostAndPort hostAndPort = new HostAndPort("10.9.166.173", 7001);
        HostAndPort hostAndPort2 = new HostAndPort("10.9.166.173", 7002);
        HostAndPort hostAndPort3 = new HostAndPort("10.9.166.173", 7003);
        HostAndPort hostAndPort4 = new HostAndPort("10.9.166.173", 7004);
        HostAndPort hostAndPort5 = new HostAndPort("10.9.166.173", 7005);
        HostAndPort hostAndPort6 = new HostAndPort("10.9.166.173", 7006);
        nodes.add(hostAndPort);
        nodes.add(hostAndPort2);
        nodes.add(hostAndPort3);
        nodes.add(hostAndPort4);
        nodes.add(hostAndPort5);
        nodes.add(hostAndPort6);
        JedisCluster jedisCluster=new JedisCluster(nodes);
        String abc = jedisCluster.get("abc");
        System.out.println(abc);
    }

    @Test
    //测试事务提交,可以在cmd命令中输入keys * 查看所有键测试是否添加成功
    public void TestTransaction(){
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.connect();
        jedis.auth("123456");
        Transaction transaction= jedis.multi();
        transaction.set("a1","a");
        transaction.set("a2","a");
        transaction.set("a3","a");
        transaction.set("a4","a");
        transaction.set("a5","a");
        transaction.exec();
        jedis.close();
    }

    //测试watch：watch相当于一个大事务，当它监管的内容有任何变化时，unwatch之前的所有内容都将回滚
    @Test
    public void TestWatch(){
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.connect();
        jedis.auth("123456");
        //正常set键值，如果键值一样，后面的值会覆盖前面的值，但如果都使用setnx，后面的值就set不进去了。但是如果前面的用setnx，后面的却是用set，那依然会是后面的覆盖前面的值
        //jedis.setnx("ccc", "aaaa");
        //jedis.setnx("ccc", "dasa");
        //设置键为1000的键值有效期为2秒，即2秒后键为1000的键值会消失
        jedis.expire("1000",2);
        //监管键为bbb的键值对儿，只要bbb的键值有任何变化，watch到unwatch之间的所有操作都将回滚
        jedis.watch("bbb");
        Transaction transaction = jedis.multi();
        transaction.set("aaaaa11111112222","Fsdfsdfsd");
        transaction.set("aaaaa111111122","Fsdfsdfsd");
        transaction.set("aaaaa1121111222","Fsdfsdfsd");
        transaction.set("aaaaa1131111222","Fsdfsdfsd");
        List<Object> list = transaction.exec();
        jedis.unwatch();
        jedis.close();
    }

    @Test
    public  void TestAuxiliary(){
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.connect();
        jedis.auth("123456");
        jedis.set("bbb","dasda");
        jedis.close();
    }

}
