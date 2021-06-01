package com.angel.multithreading.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author ³ÂÃ÷
 * @date 2021/4/28 17:45
 */
public class ThreadPoolExecutorDemo
{
	public static void main(String[] args)
	{
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10 , 10 , 10, TimeUnit.SECONDS , new ArrayBlockingQueue<>(100));
		threadPoolExecutor.allowCoreThreadTimeOut(true);
		threadPoolExecutor.allowsCoreThreadTimeOut();
		
		
		threadPoolExecutor.execute(() ->
		{
			try
			{
				Thread.sleep(5000L);
			} catch ( InterruptedException e )
			{
				e.printStackTrace();
			}
			System.out.println("excue done");
		});
		
		System.out.println(threadPoolExecutor.getActiveCount());
		System.out.println(threadPoolExecutor.getCorePoolSize());
		System.out.println(threadPoolExecutor.getTaskCount());
		System.out.println(threadPoolExecutor.isTerminating());
		
		
		threadPoolExecutor.shutdown();
	}
}
