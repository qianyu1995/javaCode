package com.angel.multithreading.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * @author 陈明
 * @date 2019/9/18 17:09
 */
public class ReentrantLockTest
{
	private static final ReentrantLock lock = new ReentrantLock();
	
	public static void needLock()
	{
		try
		{
			lock.lock();
			System.out.println(Thread.currentThread().getName() + "开始工作");
			TimeUnit.SECONDS.sleep(5);
		} catch ( InterruptedException e )
		{
			e.printStackTrace();
		} finally
		{
			lock.unlock();
		}
	}
	
	public static void main(String[] args)
	{
		IntStream.range(0 , 2)
				.forEach(i -> new Thread(ReentrantLockTest::needLock).start());
	}
}
