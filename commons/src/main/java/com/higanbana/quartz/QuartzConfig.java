package com.higanbana.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author 陈明
 * @date 2019/8/29 10:22
 */
public class QuartzConfig
{
	public static void main(String[] args) throws SchedulerException
	{
		//jobDetail
		JobDetail jobDetail = JobBuilder
				.newJob(HelloJob.class)
				.withIdentity("helloJob","group1")
				.build();
		
		SimpleTrigger trigger = TriggerBuilder
				.newTrigger()
				.withIdentity("myTrigger")
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).withRepeatCount(10))
				.build();
		
		//创建Scheduler实例
		SchedulerFactory schedulerFactory = new StdSchedulerFactory();
		Scheduler scheduler = schedulerFactory.getScheduler();
		scheduler.start();
		scheduler.scheduleJob(jobDetail,trigger);
	}
}
