package com.higanbana.multithreading.aqs;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @author 陈明
 * @date 2019/9/20 15:11
 */
public class CyclicBarrierTest
{
	public static void main(String[] args)
	{
		CyclicBarrier barrier = new CyclicBarrier(2);
		System.out.println("快上车来不及解释了");
		
		new Thread(() ->
		{
			try
			{
				TimeUnit.SECONDS.sleep(5);
				System.out.println(Thread.currentThread() + "已上车");
				barrier.await();
				System.out.println("所有人已上车，发车");
			} catch ( InterruptedException | BrokenBarrierException e )
			{
				e.printStackTrace();
			}
		} , "Jane").start();
		
		new Thread(() ->
		{
			try
			{
				TimeUnit.SECONDS.sleep(3);
				System.out.println(Thread.currentThread() + "已上车");
				barrier.await();
				System.out.println("所有人已上车，发车");
			} catch ( InterruptedException | BrokenBarrierException e )
			{
				e.printStackTrace();
			}
		} , "Mike").start();
	}
}
