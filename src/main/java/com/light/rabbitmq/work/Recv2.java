package com.light.rabbitmq.work;

import java.io.IOException;

import com.light.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
/**
 * 与recv1在同一个队列中，一起消费
* @ClassName: Recv2 
* @Description: 
* @author 小飞 
* @date 2017年2月18日 上午10:37:20
 */
public class Recv2 {

    private static final String QUEUE_NAME = "work";

    public static void main(String[] args) throws Exception {
        // 1.获取连接
        Connection connection = ConnectionUtil.getConnection();
        // 2.创建通道
        final Channel channel = connection.createChannel();
        // 3.申明队列
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        channel.basicQos(1);
        // 4.监听消息，下一行的方法中 false表示取消自动ack
        channel.basicConsume(QUEUE_NAME, false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties,
                    byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("接收2:" + message);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
              //手动响应服务端已接收消息
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });
    }
}
