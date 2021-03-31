package com.angel.multithreading.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 陈明
 * @date 2021/2/23 14:36
 */
public class ProducerConsumer1
{
	public static AtomicInteger atomicInteger = new AtomicInteger();
	
	public volatile boolean flag = true;
	
	public static final int MAX_COUNT = 10;
	
	public static final List<Integer> pool = new ArrayList<>();
	
	
	public static void main(String[] args)
	{
		ProducerConsumer1 shareDataV1 = new ProducerConsumer1();
		new Thread(() ->
		{
			shareDataV1.produce();
			
		} , "Producer1").start();
		
		
		new Thread(() ->
		{
			shareDataV1.consume();
			
		} , "Consumer").start();
		
		try
		{
			Thread.sleep(5000);
		} catch ( InterruptedException e )
		{
			e.printStackTrace();
		}
		shareDataV1.stop();
	}
	
	
	public void produce()
	{
		//判断干活
		while ( flag )
		{
			//每隔1000毫秒生产一个商品
			try
			{
				Thread.sleep(1000);
			} catch ( InterruptedException e )
			{
				e.printStackTrace();
			}
			synchronized ( pool )
			{
				//池子满了，生产者停止生产
				while ( pool.size() == MAX_COUNT )
				{
					try
					{
						System.out.println("pool is full, waiting...");
						pool.wait();
					} catch ( InterruptedException e )
					{
						e.printStackTrace();
					}
				}
				
				//干活
				pool.add(atomicInteger.incrementAndGet());
				System.out.println("生产商品编号:" + atomicInteger.get() + "\t" + "剩余商品数量:" + pool.size());
				//通知
				pool.notifyAll();
			}
			
		}
	}
	
	public void consume()
	{
		//判断干活
		while ( flag )
		{
			//每隔1000毫秒生产一个商品
			try
			{
				Thread.sleep(1000);
			} catch ( InterruptedException e )
			{
				e.printStackTrace();
			}
			synchronized ( pool )
			{
				//池子空了,消费者停止消费
				while ( pool.isEmpty() )
				{
					try
					{
						System.out.println("pool is empty, waiting...");
						pool.wait();
					} catch ( InterruptedException e )
					{
						e.printStackTrace();
					}
				}
				
				//消费
				int temp = pool.get(0);
				pool.remove(0);
				System.out.println("消费商品编号:" + temp + "\t" + "剩余商品数量:" + pool.size());
				//通知
				pool.notifyAll();
			}
			
		}
	}
	
	public void stop()
	{
		flag = false;
		System.out.println("结束任务");
	}
}
