package com.wyz.springboothelloworld.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @Author: WangYouzheng
 * @Date: 2020/5/18 20:29
 * @Description: 使用redis 来 存储session数据
 */
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 1000)
public class SessionConfig {
	@Value("${redis.hostname}")
	String HostName;
	@Value("${redis.port}")
	int port;
	@Value("${redis.password}")
	String pass;

	@Bean
	public JedisConnectionFactory connectionFactory() {
		JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
		connectionFactory.setPort(port);
		connectionFactory.setHostName(HostName);
		connectionFactory.setPassword(pass);
		return connectionFactory;
	}
}
