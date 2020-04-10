package com.angel.multithreading.aqs;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;

/**
 * @author 陈明
 * @date 2019/9/18 17:18
 */
public class ReadWriteLockTest
{
	private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);
	// 读锁
	private static ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
	// 写锁
	private static ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
	
	// 存放数据
	private static List<Long> data = new ArrayList<>();
	
	public static void main(String[] args) throws InterruptedException
	{
		new Thread(() ->
		{
			while ( true )
			{
				write();
			}
		} , "writer").start();
		new Thread(() ->
		{
			while ( true )
			{
				read();
			}
		} , "reader").start();
		
	}
	
	public static void write()
	{
		try
		{
			writeLock.lock(); // 写锁
			TimeUnit.SECONDS.sleep(1);
			long value = System.currentTimeMillis();
			data.add(value);
			System.out.println(Thread.currentThread().getName() + " 写入value: " + value);
		} catch ( InterruptedException e )
		{
			e.printStackTrace();
		} finally
		{
			writeLock.unlock(); // 释放写锁
		}
	}
	
	public static void read()
	{
		try
		{
			readLock.lock(); // 获取读锁
			TimeUnit.SECONDS.sleep(1);
			String value = data.stream().map(String::valueOf).collect(Collectors.joining(","));
			System.out.println(Thread.currentThread().getName() + "读取data: " + value);
		} catch ( InterruptedException e )
		{
			e.printStackTrace();
		} finally
		{
			readLock.unlock(); // 释放读锁
		}
	}
	
}
