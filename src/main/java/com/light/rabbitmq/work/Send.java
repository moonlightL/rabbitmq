package com.light.rabbitmq.work;

import com.light.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
/**
 * 直接发送消息 到 队列中
* @ClassName: Send 
* @Description: 
* @author 小飞 
* @date 2017年2月18日 上午11:02:13
 */
public class Send {

    private static final String QUEUE_NAME = "work";
    
    public static void main(String[] args) throws Exception {
        //1.获取连接
        Connection connection = ConnectionUtil.getConnection();
        //2.创建通道
        Channel channel = connection.createChannel();
        //3.申明队列
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        //4.发送消息
        for (int i = 0; i < 100; i++) {
            channel.basicPublish("", QUEUE_NAME, null, ("hello work" + i + "").getBytes());
        }
        
        System.out.println("发送成功");
        //5.释放连接
        channel.close();
        connection.close();
    }
}
