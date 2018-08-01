package com.common.zookeeper.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * Created by Administrator on 2018/8/1.
 * 监听当前节点NodeCache
 */
public class CuratorWatcherNodeCache {

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
        //这条语句创建一个用于缓存对应节点数据的本地缓存对象，如果监听到该节点有变化，会更新到该节点上
        //第二个值是操作的节点，第三个值是是否压缩
        //即便节点不存在，当节点创建的时候也会触发监听
        NodeCache nodeCache=new NodeCache(curatorFramework,"/super111/c1111/c1111",false);
        //设置为初始化的时候就开始监听
        nodeCache.start(true);
        //添加一个监听，并设置回调，节点的增改操作会执行，删除不会执行
        nodeCache.getListenable().addListener(new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                //获取数据，数据已经封装到nodecache对象上面
                System.out.println("path"+nodeCache.getCurrentData().getPath());
                System.out.println("数据"+nodeCache.getCurrentData().getData());
                System.out.println("状态"+nodeCache.getCurrentData().getStat());
            }
        });

        //测试监听
        curatorFramework.setData().forPath("/super111/c1111/c1111","new new new".getBytes());

        Thread.sleep(1000);
    }
}
