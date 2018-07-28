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



import com.rabbitmq.client.*;
import com.wgw.rabbit.util.ConnectionUtil;

import java.io.IOException;

/**
 * Created by jackiechan on 18-7-17/上午10:11
 */
public class Recver1 {
    private static  final String QUEUE_NAME="test_work_yaosishi";

    public static void main(String[] args)  throws Exception{
        //建立链接
        Connection connection = ConnectionUtil.getConnection();

        //创建频道
        Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        channel.basicQos(1);//一次只处理一个信息,处理完成之后才会从服务器上再拿一条信息,不加这行代码,会一次性拿很多消息,但是会一条一条处理

        DefaultConsumer defaultConsumer=new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                //处理消息
                System.out.println("接收者1收到的内容是:  "+new String(body));
                try {
                    Thread.sleep(500);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //告诉服务器我收到消息了,回执
                channel.basicAck(envelope.getDeliveryTag(),false);//参数1是消息标记,标记不是我们的队列的名字,是服务器给这个消息加的标记,参数2 设置为true代表拒收消息,fasle代表收到消息
            }
        };
        //参数2表示接受到消息后不会自动告诉发送者确认已接收到，需要我们自己另行添加确认收到的语句
        //手动确认接收的语句在上面：channel.basicAck(envelope.getDeliveryTag(),false);
        channel.basicConsume(QUEUE_NAME,false,defaultConsumer);
        //后面不需要阻塞,但是注意也不要关闭链接
       // channel.close();
       // connection.close();

    }
}
