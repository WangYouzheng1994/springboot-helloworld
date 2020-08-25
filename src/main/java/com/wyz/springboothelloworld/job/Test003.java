package com.wyz.springboothelloworld.job;

import javax.xml.ws.Service;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Author: WangYouzheng
 * @Date: 2020/6/16 20:48
 * @Description: 使用定时线程池ScheduledExecutorService 完成 定时任务
 */
public class Test003 {
	public static void main(String[] args) {
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				System.out.println("hello~~");
			}
		};
		ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

		/**
		 * 代码第一次执行的时候 延迟1S， 以后的每1S执行一次。
		 */
		scheduledExecutorService.scheduleAtFixedRate(runnable, 2, 1,TimeUnit.SECONDS);
	}
}
