package com.wgw.rabbit.topic;

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
 * Created by jackiechan on 18-7-17/下午2:43
 */
public class Recver1 {

    private static final String EXCHANGE_NAME = "luchuan";
    private static final String QUEUE_NAME = "liugehetao";

    public static void main(String[] args) throws  Exception {
        Connection connection = ConnectionUtil.getConnection();

        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //将队列绑定到交换机
        //参数3 关键key,绑定了关键key之后,只有发送到该交换机上面的消息绑定key和设置的key一致的情况下,才会收到消息
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"abc.#");//在绑定到交换机的时候 交换机必须已经存在,而交换机的创建是发送者声明的,所以先启动一次发送者
        //如果要绑定多个key只需要重复绑定即可
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"key.*");//在绑定到交换机的时候 交换机必须已经存在,而交换机的创建是发送者声明的,所以先启动一次发送者
        channel.basicQos(1);
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

        channel.basicConsume(QUEUE_NAME,false,defaultConsumer);//自动应答,设置为false后需要手动告诉服务器收到消息了,设置为true后自动回复服务器
    }
}
