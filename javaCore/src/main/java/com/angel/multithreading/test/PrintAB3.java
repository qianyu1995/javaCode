package com.angel.multithreading.test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.LockSupport;

/**
 * @author 陈明
 * @date 2021/4/2 14:32
 */
public class PrintAB3
{
	private static Thread numThread, letterThread;
	
	static CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
	
	public static void main(String[] args) throws InterruptedException
	{
		letterThread = new Thread(() ->
		{
			try
			{
				cyclicBarrier.await();
			} catch ( InterruptedException e )
			{
				e.printStackTrace();
			} catch ( BrokenBarrierException e )
			{
				e.printStackTrace();
			}
			for ( int i = 0; i < 26; i++ )
			{
				LockSupport.park();
				System.out.print((char) ('A' + i));
				LockSupport.unpark(numThread);
				
			}
		} , "letterThread");
		
		numThread = new Thread(() ->
		{
			try
			{
				cyclicBarrier.await();
			} catch ( InterruptedException e )
			{
				e.printStackTrace();
			} catch ( BrokenBarrierException e )
			{
				e.printStackTrace();
			}
			
			for ( int i = 1; i <= 26; i++ )
			{
				System.out.print(i);
				LockSupport.unpark(letterThread);
				LockSupport.park();
				
			}
		} , "numThread");
		
		
		
		letterThread.start();
		Thread.sleep(5000);
		numThread.start();
	}
}
