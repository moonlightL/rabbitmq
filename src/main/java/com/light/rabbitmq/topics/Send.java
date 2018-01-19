package com.light.rabbitmq.topics;

import com.light.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * 通过routeKey(占位符匹配) 发送给对应的队列中
 * 交换机类型 topic 该模式先启动 接收端，再启动发送端
 * @ClassName: Send
 * @Description:
 * @author 小飞
 * @date 2017年2月18日 上午10:34:48
 */
public class Send {

    private static final String EXCHANGE_NAME = "topics_exchange";

    public static void main(String[] args) throws Exception {
        // 1.获取连接
        Connection connection = ConnectionUtil.getConnection();
        // 2.创建通道
        Channel channel = connection.createChannel();
        // 3.申明交换机
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        // 4.发送消息
        channel.basicPublish(EXCHANGE_NAME, "user.1", null, "hello topiscs".getBytes());

        System.out.println("发送成功");
        // 5.释放连接
        channel.close();
        connection.close();
    }
}
