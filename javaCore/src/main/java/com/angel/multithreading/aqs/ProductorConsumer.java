package com.angel.multithreading.aqs;

import java.util.concurrent.TimeUnit;

/**
 * @author 陈明
 * @date 2019/9/18 15:37
 */
public class ProductorConsumer
{
	private static int data = 0;
	
	private static volatile boolean used = false;
	
	private final static Object MONITOR = new Object();
	
	public static void main(String[] args)
	{
		new Thread(() ->
		{
			while ( true )
			{
				produceData();
			}
		} , "thread1").start();
		
		new Thread(() ->
		{
			while ( true )
			{
				consumeData();
			}
		} , "thread2").start();
	}
	
	public static void produceData()
	{
		synchronized ( MONITOR )
		{
			while ( !used )
			{
				try
				{
					MONITOR.wait();
				} catch ( InterruptedException e )
				{
					e.printStackTrace();
				}
			}
			
			try
			{
				TimeUnit.SECONDS.sleep(1);
			} catch ( InterruptedException e )
			{
				e.printStackTrace();
			}
			data++;
			System.out.println(Thread.currentThread().getName() + " 生产data = " + data);
			used = false;
			MONITOR.notifyAll();
		}
	}
	
	public static void consumeData()
	{
		synchronized ( MONITOR )
		{
			while ( used )
			{
				try
				{
					MONITOR.wait();
				} catch ( InterruptedException e )
				{
					e.printStackTrace();
				}
			}
			try
			{
				TimeUnit.SECONDS.sleep(1);
			} catch ( InterruptedException e )
			{
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + " 消费data = " + data);
			used = true;
			MONITOR.notifyAll();
		}
	}
}


