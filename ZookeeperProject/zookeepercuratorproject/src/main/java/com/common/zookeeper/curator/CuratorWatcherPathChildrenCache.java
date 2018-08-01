package com.common.zookeeper.curator;


import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * Created by Administrator on 2018/8/1.
 * 可以监听到子节点的变化
 */
public class CuratorWatcherPathChildrenCache {

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
        //第三个参数设置为true，即改变后的数据会带回来，否则全为null
        PathChildrenCache pathChildrenCache=new PathChildrenCache(curatorFramework,"/super111",true);
        //初始化监听，根据事件驱动监听
        pathChildrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
        pathChildrenCache.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
                //System.out.println(pathChildrenCacheEvent.getData().getPath());
                //执行操作的类型，用于判断到底是哪种类型的操作触发了监听（如是增删啊，还是修改啊）
                PathChildrenCacheEvent.Type type = pathChildrenCacheEvent.getType();
                switch (type){
                //CHILD_ADDED是curator内部设置的常数
                    case CHILD_ADDED:
                        System.out.println("创建"+pathChildrenCacheEvent.getData().getPath());
                        System.out.println("创建"+pathChildrenCacheEvent.getData().getData());
                        break;
                    case CHILD_UPDATED:
                        System.out.println("更新"+pathChildrenCacheEvent.getData().getData());
                        System.out.println("更新"+pathChildrenCacheEvent.getData().getPath());
                        break;
                    case CHILD_REMOVED:
                        System.out.println("删除"+pathChildrenCacheEvent.getData().getPath());
                        System.out.println("删除"+pathChildrenCacheEvent.getData().getData());
                        break;
                }
            }
        });
        Thread.sleep(1000);
        curatorFramework.setData().forPath("/super111/c1111/c1111","321".getBytes());
        Thread.sleep(1000);
        //监听子节点时，创建父节点不会监听
        //curatorFramework.create().forPath("/super","super".getBytes());

    }
}
