package com.angel.multithreading.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 陈明
 * @date 2021/2/23 15:09
 */
public class ProducerConsumer2
{
	private int number = 0;
	
	private Lock lock = new ReentrantLock();
	
	private Condition condition = lock.newCondition();
	
	public static void main(String[] args)
	{
		ProducerConsumer2 producerConsumer2 = new ProducerConsumer2();
		new Thread(() ->
		{
			for ( int i = 0; i < 5; i++ )
			{
				producerConsumer2.produce();
			}
			
			
		} , "Producer1").start();
		
		
		new Thread(() ->
		{
			for ( int i = 0; i < 5; i++ )
			{
				producerConsumer2.consume();
			}
		} , "Consumer").start();
		
		
	}
	
	
	public void produce()
	{
		lock.lock();
		
		try
		{   //商品数量不为0 阻塞
			while ( number != 0 )
			{
				condition.await();
			}
			number++;
			System.out.println(Thread.currentThread().getName() + "\t" + number);
			condition.signalAll();
		} catch ( Exception e )
		{
			e.printStackTrace();
		} finally
		{
			lock.unlock();
		}
		
		
	}
	
	public void consume()
	{
		lock.lock();
		
		try
		{   //商品数量为0 阻塞
			while ( number == 0 )
			{
				condition.await();
			}
			number--;
			System.out.println(Thread.currentThread().getName() + "\t" + number);
			condition.signalAll();
		} catch ( Exception e )
		{
			e.printStackTrace();
		} finally
		{
			lock.unlock();
		}
		
	}
}
	
	

