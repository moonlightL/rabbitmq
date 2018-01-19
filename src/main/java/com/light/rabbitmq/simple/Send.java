package com.light.rabbitmq.simple;

import com.light.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Send {

    private static final String QUEUE_NAME = "simple";
    
    public static void main(String[] args) throws Exception {
        //1.获取连接
        Connection connection = ConnectionUtil.getConnection();
        //2.创建通道
        Channel channel = connection.createChannel();
        //3.申明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //4.发送消息
        channel.basicPublish("", QUEUE_NAME, null, "hello simple".getBytes());
        
        System.out.println("发送成功");
        //5.释放连接
        channel.close();
        connection.close();
    }
}
