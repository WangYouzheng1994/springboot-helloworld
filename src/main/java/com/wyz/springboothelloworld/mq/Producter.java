package com.wyz.springboothelloworld.mq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.*;

/**
 * @Author: WangYouzheng
 * @Date: 2020/5/21 20:33
 * @Description:
 */
@Component // 放到容器
@EnableScheduling // 定时调度
public class Producter {
	/*public static void main(String[] args) throws Exception {
		// Producter.transactionProducer();
		Producter.manualMessageProducer();
	}*/
	@Autowired
	private JmsMessagingTemplate jmsMessagingTemplate;
	@Autowired
	private Queue queue;

	@Scheduled(fixedDelay = 3000)
	public void send() {
		jmsMessagingTemplate.convertAndSend(queue, System.currentTimeMillis() + "---------springboot 生产者消息。");
	}


	static public void sendMsg(Session session, MessageProducer producer, String i) throws JMSException {
		TextMessage textMessage = session.createTextMessage("hello activemq " + i);
		producer.send(textMessage);
	}

	public static void producerAuto() throws Exception {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, "tcp://127.0.0.1:61616");
		Connection connection = connectionFactory.createConnection();
		connection.start();

		// 是否以事务的方式进行提交， 是否要求自动签收/手动签收
		Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
		Destination destination = session.createQueue("my-queue");
		MessageProducer producer = session.createProducer(destination);
		// 不持久化
		producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		// 设置持久化
		producer.setDeliveryMode(DeliveryMode.PERSISTENT);
		for (int i = 1; i <= 5; i++) {
			System.out.println("我是生产者" + i);
			sendMsg(session, producer, "我是生产者 " + i);
		}
		System.out.println("生产者发送完毕");
	}

	/**
	 * 持久化 + 手动提交
	 *
	 * @throws Exception
	 */
	public static void transactionProducer() throws Exception {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, "tcp://127.0.0.1:61616");
		Connection connection = connectionFactory.createConnection();
		connection.start();

		// 事务提交 + 自动签收
		// Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
		// 事务提交 + 手动签收
		Session session = connection.createSession(Boolean.TRUE, Session.CLIENT_ACKNOWLEDGE);
		Destination destination = session.createQueue("manal-queue2");
		MessageProducer producer = session.createProducer(destination);
		// 不持久化
		// producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		// 设置持久化
		producer.setDeliveryMode(DeliveryMode.PERSISTENT);
		for (int i = 1; i <= 5; i++) {
			System.out.println("我是手动commit生产者" + i);
			sendMsg(session, producer, "我是手动生产者 " + i);
		}
		// session.rollback();
		session.commit();
		System.out.println("生产者发送完毕");
	}

	/**
	 * 设置发送手动签收的数据
	 * @throws Exception
	 */
	public static void manualMessageProducer() throws Exception {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, "tcp://127.0.0.1:61616");
		Connection connection = connectionFactory.createConnection();
		connection.start();

		// 事务提交 + 自动签收
		// Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
		// 事务提交 + 手动签收
		Session session = connection.createSession(Boolean.TRUE, Session.CLIENT_ACKNOWLEDGE);
		Destination destination = session.createQueue("manal-queue");
		MessageProducer producer = session.createProducer(destination);
		// 不持久化
		// producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		// 设置持久化
		producer.setDeliveryMode(DeliveryMode.PERSISTENT);
		for (int i = 1; i <= 5; i++) {
			System.out.println("我是手动commit，手动签收的生产者" + i);
			sendMsg(session, producer, "我是手动commit，手动签收的生产者" + i);
		}
		// session.rollback();
		session.commit();
		System.out.println("生产者发送完毕");
	}
}
