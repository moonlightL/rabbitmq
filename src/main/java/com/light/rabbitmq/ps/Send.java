package com.light.rabbitmq.ps;

import com.light.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
/**
 * 通过交换机以广播形式发送消息到不同的队列中，交换机类型 fanout
 * 该模式先启动 接收端，再启动发送端 
 * @ClassName: Send 
 * @Description: 
 * @author 小飞 
 * @date 2017年2月18日 上午10:34:48
 */
public class Send {

    private static final String EXCHANGE_NAME  = "ps";
    
    public static void main(String[] args) throws Exception {
        //1.获取连接
        Connection connection = ConnectionUtil.getConnection();
        //2.创建通道
        Channel channel = connection.createChannel();
        //3.申明交换机
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        //4.发送消息
        for (int i = 0; i < 100; i++) {
            channel.basicPublish(EXCHANGE_NAME, "", null, ("hello ps" + i + "").getBytes());
        }
        
        System.out.println("发送成功");
        //5.释放连接
        channel.close();
        connection.close();
    }
}
