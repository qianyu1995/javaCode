package com.higanbana.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 陈明
 * @date 2019/8/29 10:29
 */
public class HelloJob implements Job
{
	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException
	{
		//打印当前日期
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("\"yyyy-MM-dd HH:mm:ss\"");
		System.out.println(dateFormat.format(date));
	}
}
