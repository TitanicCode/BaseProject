package com.common.zookeeper.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorEventType;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2018/8/1.
 */
public class CuratorBase {
    //服务器连接地址，例：192.168.1.7:2181多个地址写在同一个双引号下面，用逗号隔开
    private static final String CONNECTION_ADD="10.9.251.200:8091";
    //超时时间
    private static final int SESSIONTIME_OUT=5000;

    public static void main(String[] args) throws Exception {
        //上面设置的超时时间为5秒，当超时候，每隔1000毫秒会重试连接一次，最多尝试10次
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 10);
        //这条语句通过.设置了很多对象，这是典型的支持链式操作的方式，curator就支持这个链式操作
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder().connectString(CONNECTION_ADD).sessionTimeoutMs(SESSIONTIME_OUT).retryPolicy(retryPolicy).build();
        curatorFramework.start();
        System.out.println(curatorFramework.getState()+"~~~~~~~~"+ ZooKeeper.States.CONNECTED);
        //创建zookeeper节点，withMode是创建节点的格式，在此使用的是持久化格式，也可是设置临时格式的节点
        //curatorFramework.create().withMode(CreateMode.PERSISTENT).forPath("/super2","super2".getBytes());
        //递归创建需要添加creatingParentContainersIfNeeded()方法
        //curatorFramework.create().creatingParentContainersIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/super/c1/c11","c11".getBytes());

        //如果子节点不为空，则不能删除父节点，如果想删除需要添加deletingChildrenIfNeeded()方法
        //curatorFramework.delete().forPath("supper");
        //curatorFramework.delete().deletingChildrenIfNeeded().forPath("/super/c1");


        //修改和读出
//        curatorFramework.setData().forPath("/super111/c1111/c1111","new1 value".getBytes());
//        byte[] bytes = curatorFramework.getData().forPath("/super111/c1111/c1111");
//        System.out.println(new String(bytes));

        //使用线程池操作zookeeper
        //创建线程池，这应该是个成员变量
        ExecutorService pool= Executors.newCachedThreadPool();
        curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).inBackground(new BackgroundCallback() {
            @Override
            public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                CuratorEventType type = curatorEvent.getType();
                int resultCode = curatorEvent.getResultCode();
                System.out.println(type);
                System.out.println(resultCode);
                System.out.println(Thread.currentThread().getName());
            }
        },pool).forPath("/supper3/c3/c33","c33".getBytes());
    }


}
