package com.wgw.rabbit.pubsub;

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

/**
 * Created by jackiechan on 18-7-17/下午2:43
 * 注意队列在注册到交换机之前所有发送出去的消息都收不到,交换机不负责存储消息
 */
public class Sender {

    private static final String EXCHANGE_NAME = "laowangguai";

    public static void main(String[] args) throws Exception{

        Connection connection = ConnectionUtil.getConnection();

        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");//声明一个发布订阅模式的交换机, 格式必须是fanout
        String message = "老王班里有个女生对老王很贴心,这是what鬼666";
        channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
        System.out.println("哦~~~~:"+message);
        channel.close();
        connection.close();
    }
}
