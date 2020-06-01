package com.wyz.springboothelloworld.mq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @Author: WangYouzheng
 * @Date: 2020/5/28 19:20
 * @Description: 消费者
 */
public class Consumer {
	public static void main(String[] args) throws JMSException {
		// manualReceive();
		manualAcknowledge();
	}

	/**
	 * 自动签收
	 */
	public static void autoReceive() throws JMSException {
		// 去给连接工厂传入基础的连接参数。使用默认的账号密码 admin admin
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, "tcp://127.0.0.1:61616");
		// 创建连接
		Connection connection = connectionFactory.createConnection();
		// 启动连接
		connection.start();
		// 创建会话工厂
		Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
		// 创建队列
		Destination destination = session.createQueue("my-queue2");
		// 创建消费者
		MessageConsumer consumer = session.createConsumer(destination);
		while (true) {
			System.out.println("开始调用了哈");
			//
			TextMessage receive = (TextMessage) consumer.receive();
			if (receive != null) {
				String text = receive.getText();
				System.out.println("消费者获取到消息：" + text);
			} else {
				break;
			}
			System.out.println("调用结束了哈");
		}
	}

	/**
	 * 手动事务接收
	 */
	public static void manualReceive() throws JMSException {
		// 去给连接工厂传入基础的连接参数。使用默认的账号密码 admin admin
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, "tcp://127.0.0.1:61616");
		// 创建连接
		Connection connection = connectionFactory.createConnection();
		// 启动连接
		connection.start();
		// 创建会话工厂 只是创建session   开启事务
		Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
		// 创建队列
		Destination destination = session.createQueue("manal-queue2");
		// 创建消费者
		MessageConsumer consumer = session.createConsumer(destination);
		while (true) {
			System.out.println("开始调用了哈");
			//
			TextMessage receive = (TextMessage) consumer.receive();
			if (receive != null) {
				String text = receive.getText();
				System.out.println("消费者获取到消息：" + text);
			} else {
				break;
			}
			session.commit(); // 不commit 在mq里面是看不到被消费了的
			System.out.println("调用结束了哈");
		}
	}

	public static void manualAcknowledge() throws JMSException {
		// 去给连接工厂传入基础的连接参数。使用默认的账号密码 admin admin
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, "tcp://127.0.0.1:61616");
		// 创建连接
		Connection connection = connectionFactory.createConnection();
		// 启动连接
		connection.start();
		// 创建会话工厂 只是创建session
		Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
		// 创建队列
		Destination destination = session.createQueue("manal-queue2");
		// 创建消费者
		MessageConsumer consumer = session.createConsumer(destination);
		while (true) {
			System.out.println("开始调用了哈");
			//
			TextMessage receive = (TextMessage) consumer.receive();
			if (receive != null) {
				String text = receive.getText();
				System.out.println("消费者获取到消息：" + text);
			} else {
				break;
			}
			// session.commit(); // 不commit 在mq里面是看不到被消费了的
			System.out.println("调用结束了哈");
		}
	}
}
