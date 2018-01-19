package com.light.rabbitmq.topics;

import java.io.IOException;

import com.light.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
/**
 * 需要定义routeKey占位符匹配规则
* @ClassName: Recv1 
* @Description: 
* @author 小飞 
* @date 2017年2月18日 上午10:35:24
 */
public class Recv2 {

    private static final String QUEUE_NAME = "topics_recv_2";
    private static final String EXCHANGE_NAME = "topics_exchange";

    public static void main(String[] args) throws Exception {
        // 1.获取连接
        Connection connection = ConnectionUtil.getConnection();
        // 2.创建通道
        Channel channel = connection.createChannel();
        // 3.申明交换机
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        // 4.队列绑定交换机
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "shop*");
        // 5.消费消息
        channel.basicQos(1);
        channel.basicConsume(QUEUE_NAME, false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                    byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("recv2:" + message);
                
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });
    }
}
