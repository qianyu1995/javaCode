package com.higanbana.multithreading.aqs;

/**
 * @author 陈明
 * @date 2019/9/18 17:28
 */

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * JDK8 新增了一个锁StampedLock，它是对ReadWriteLock的改进
 */
public class StampedLockTest
{
	private static StampedLock lock = new StampedLock();
	
	private static List<Long> data = new ArrayList<>();
	
	public static void main(String[] args)
	{
		ExecutorService executorService = Executors.newFixedThreadPool(20);
		
		Runnable read = StampedLockTest::read;
		Runnable write = StampedLockTest::write;
		
		IntStream.range(0 , 19).forEach(i -> executorService.submit(read));
		executorService.submit(write);
		
		executorService.shutdown();
	}
	
	private static void read()
	{
		long stamped = -1;
		try
		{
			stamped = lock.readLock(); // 获取悲观锁，阻塞写线程
			TimeUnit.SECONDS.sleep(1);
			String collect = data.stream().map(String::valueOf).collect(Collectors.joining(","));
			System.out.println(Thread.currentThread().getName() + " read value: " + collect);
		} catch ( InterruptedException e )
		{
			e.printStackTrace();
		} finally
		{
			lock.unlockRead(stamped);
		}
	}
	
	private static void write()
	{
		long stamped = -1;
		try
		{
			stamped = lock.writeLock();
			TimeUnit.SECONDS.sleep(1);
			long value = System.currentTimeMillis();
			data.add(value);
			System.out.println(Thread.currentThread().getName() + " write value: " + value);
		} catch ( InterruptedException e )
		{
			e.printStackTrace();
		} finally
		{
			lock.unlockWrite(stamped);
		}
	}
	
}
