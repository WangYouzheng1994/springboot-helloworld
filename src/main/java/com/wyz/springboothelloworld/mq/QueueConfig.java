package com.wyz.springboothelloworld.mq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;

/**
 * @Author: WangYouzheng
 * @Date: 2020/6/2 19:26
 * @Description: 配置类
 */
@Configuration
public class QueueConfig {
	@Value("$(queue)")
	private String queue;

	/**
	 * 容器中注入activeMQ的队列  对列为配置文件里面写好的
	 *
	 * @return
	 */
	@Bean
	public Queue logQueue() {
		return new ActiveMQQueue(queue);
	}

	@Bean
	public ActiveMQConnectionFactory activeMQConnectionFactory(){
		return new ActiveMQConnectionFactory( "admin", "admin", "tcp://127.0.0.1:61616");
	}

	/**
	 * 初始化 模板设置
	 *
	 * @param activeMQConnectionFactory
	 * @param queue
	 * @return
	 */
	@Bean
	public JmsTemplate jmsTemplate(ActiveMQConnectionFactory activeMQConnectionFactory, Queue queue) {
		JmsTemplate jmsTemplate = new JmsTemplate();
		// 设置持久化
		jmsTemplate.setDeliveryMode(2);
		jmsTemplate.setConnectionFactory(activeMQConnectionFactory);
		// 设置默认的队列，这个可以在发送消息的时候指定队列的。
		jmsTemplate.setDefaultDestination(queue);
		// 签收方式
		jmsTemplate.setSessionAcknowledgeMode(4);
		return jmsTemplate;
	}

	/**
	 * 定义一个消息监听器连接工厂， 这里定义的是点对点模式的监听器连接工厂
	 *
	 * @param activeMQConnectionFactory
	 * @return
	 */
	@Bean(name = "jmsQueueListener")
	public DefaultJmsListenerContainerFactory jmsQueueListenerContainerFactory(ActiveMQConnectionFactory activeMQConnectionFactory) {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		factory.setConnectionFactory(activeMQConnectionFactory);
		// 设置连接数
		factory.setConcurrency("1-10");
		// 重连间隔时间
		factory.setRecoveryInterval(1000L);
		factory.setSessionAcknowledgeMode(4);
		return factory;
	}
}
