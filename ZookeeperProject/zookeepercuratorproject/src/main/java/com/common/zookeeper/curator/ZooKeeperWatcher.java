package com.common.zookeeper.curator;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Administrator on 2018/8/1.
 */
public class ZooKeeperWatcher implements Watcher {
    private static final String CONNECTION_ADD="10.9.251.200:8091";
    private static final int SESSIONTIME_OUT=5000;
    //信号量，用于阻塞线程，类似于wait，在zookeeper连接后，我们可以把它放行
    private CountDownLatch countDownLatch=new CountDownLatch(1);
    //原子变量，相当于一个int，可以在多线程并发情况下自增，安全性高
    AtomicInteger seq=new AtomicInteger();
    private ZooKeeper zooKeeper;

    //用于创建与zookeeper的连接
    private void createConnection(String connectionString,int sessionTimeout) throws InterruptedException, IOException {

            //创建链接，以当前调用此方法的对象为监听器，创建监听
            zooKeeper=new ZooKeeper(connectionString,sessionTimeout,this);
            System.out.println("ZooKeeper连接了");
            //为了防止程序退出，需要阻塞主线程，因为如果主线程结束了，监听还没传过去，那监听就没有意义了
            countDownLatch.await();
    }

    //用于清除zookeeper的所有节点
    public void deleteAllNode(String path) throws KeeperException, InterruptedException {
        //关闭监听，判断supper节点
        Stat exists = zooKeeper.exists(path, false);
        if (exists!=null){
            deleteNode(path);
        }
    }

    //用于删除指定节点
    public void deleteNode(String path){
        //-1是指跳过版本检查
        try {
            zooKeeper.delete(path,-1);
            System.out.println("删除节点成功，路径是"+path);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws IOException, InterruptedException {
        ZooKeeperWatcher zooKeeperWatcher=new ZooKeeperWatcher();
        zooKeeperWatcher.createConnection(CONNECTION_ADD,SESSIONTIME_OUT);
        //当我们成功后，zookeeper会发送消息给我们，触发监听操作，而此时会执行一次process方法

        Thread.sleep(1000);

        //zooKeeperWatcher.deleteAllNode("此处添加一个节点");

        //判断是否存在父节点，如果没有要创建父节点，否则之后的操作都是无意义的
    }

    /**
     * 当watcher监听的操作被触发的时候会执行一次
     * watchedEvent是监听事件的封装对象，内部装有监听的类型和状态
     */
    @Override
    public void process(WatchedEvent watchedEvent) {
        if(watchedEvent==null){
            return;
        }
        Event.KeeperState state = watchedEvent.getState();
        Event.EventType type = watchedEvent.getType();

        //自增当前值并返回,用于告诉我这是第几次监听
        int i = seq.incrementAndGet();
        String prefxi="这是监听第"+i+"次";
        System.out.println(prefxi+"收到watch事件");
        System.out.println(prefxi+"事件状态是"+state);
        System.out.println(prefxi+"事件类型是"+type);
        if (Event.KeeperState.SyncConnected==state) {//代表连接成功
            if (Event.EventType.None==type){//首次连接
                System.out.println("成功连接到我们的zookeeper");
                //放行
                countDownLatch.countDown();
            }
        }
    }

    //创建节点
    public boolean createNode(String path,String data){
        //监听只有一次有效，所以当我们做任何事情的时候要先设置监听
        //当每次创建新节点时，都触发该函数设置监听，所以创建节点一次，监听就会被true一次，所以每次创建节点都会接收到消息
        //设置为true，表示要监听，那我们需要一个监听器，监听器可以新建watcher对象
        try {
            zooKeeper.exists(path,true);
            //ZooDefs.Ids.OPEN_ACL_UNSAFE是zookeeper的安全协议
            String s = zooKeeper.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
