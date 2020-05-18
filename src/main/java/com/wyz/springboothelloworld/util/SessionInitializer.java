package com.wyz.springboothelloworld.util;

import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

/**
 * @Author: WangYouzheng
 * @Date: 2020/5/18 20:49
 * @Description:
 */
public class SessionInitializer extends AbstractHttpSessionApplicationInitializer {
	public SessionInitializer() {
		super(SessionConfig.class);
	}
}
