package com.wgw.rabbit.work;

//
//                            _ooOoo_  
//                           o8888888o  
//                           88" . "88  
//                           (| -_- |)  
//                            O\ = /O  
//                        ____/`---'\____  
//                      .   ' \\| |// `.  
//                       / \\||| : |||// \  
//                     / _||||| -:- |||||- \  
//                       | | \\\ - /// | |  
//                     | \_| ''\---/'' | |  
//                      \ .-\__ `-` ___/-. /  
//                   ___`. .' /--.--\ `. . __  
//                ."" '< `.___\_<|>_/___.' >'"".  
//               | | : `- \`.;`\ _ /`;.`/ - ` : | |  
//                 \ \ `-. \_ __\ /__ _/ .-` / /  
//         ======`-.____`-.___\_____/___.-`____.-'======  
//                            `=---='  
//  
//         .............................................  
//                  佛祖镇楼                  BUG辟易  
//          佛曰:  
//                  写字楼里写字间，写字间里程序员；  
//                  程序人员写程序，又拿程序换酒钱。  
//                  酒醒只在网上坐，酒醉还来网下眠；  
//                  酒醉酒醒日复日，网上网下年复年。  
//                  但愿老死电脑间，不愿鞠躬老板前；  
//                  奔驰宝马贵者趣，公交自行程序员。  
//                  别人笑我忒疯癫，我笑自己命太贱；  
//  



import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.wgw.rabbit.util.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by jackiechan on 18-7-17/上午10:11
 * 工作模式,可以有多个接收者,但是一条消息只能被一个接收者处理
 * 使用场景,集群环境,比如下单操作,只要有一台机器能处理就行,不需要所有机器都处理
 */
public class Sender {
    private static  final String QUEUE_NAME="test_work_yaosishi";
    public static void main(String[] args) throws IOException, InterruptedException, TimeoutException {

        Connection connection = ConnectionUtil.getConnection();

        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);


        for (int i = 0; i < 100; i++) {
            String message="婷姐"+i+"号";
            channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
            System.out.println("发送的内容是:  "+message);
            //Thread.sleep(i*10);
        }
        channel.close();
        connection.close();


    }
}
