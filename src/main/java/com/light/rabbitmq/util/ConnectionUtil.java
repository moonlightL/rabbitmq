package com.light.rabbitmq.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
/**
 * 
* @ClassName: ConnectionUtil 
* @Description: 连接工具类
* @author 小飞 
* @date 2017年2月20日 上午11:07:55
 */
public class ConnectionUtil {

    public static Connection getConnection() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("light");
        factory.setPassword("light");
        factory.setVirtualHost("/test");
        factory.setHost("192.168.2.30");
        factory.setPort(5672);
        Connection connection = factory.newConnection();
        return connection;
    }
}
