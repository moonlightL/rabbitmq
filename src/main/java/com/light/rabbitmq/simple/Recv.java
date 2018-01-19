package com.light.rabbitmq.simple;

import java.io.IOException;

import com.light.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class Recv {

    private static final String QUEUE_NAME = "simple";

    public static void main(String[] args) throws Exception {
        // 1.获取连接
        Connection connection = ConnectionUtil.getConnection();
        // 2.创建通道
        Channel channel = connection.createChannel();
        // 3.申明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        // 4.监听消息
        channel.basicConsume(QUEUE_NAME, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties,
                    byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("接收:" + message);
            }
        });
    }
}
