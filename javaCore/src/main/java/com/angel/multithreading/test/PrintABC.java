package com.angel.multithreading.test;

import java.util.concurrent.Semaphore;

/**
 * @author 陈明
 * @date 2021/4/2 14:02
 */
public class PrintABC
{
	private int times = 3;
	private static Semaphore semaphoreA = new Semaphore(1); // 只有A 初始信号量为1,第一次获取到的只能是A
	private static Semaphore semaphoreB = new Semaphore(0);
	private static Semaphore semaphoreC = new Semaphore(0);
	private static Semaphore semaphoreD = new Semaphore(0);
	
	
	public static void main(String[] args)
	{
		PrintABC application = new PrintABC();
		new Thread(() -> application.printer("A" , semaphoreA , semaphoreB)).start();
		new Thread(() -> application.printer("B" , semaphoreB , semaphoreC)).start();
		new Thread(() -> application.printer("C" , semaphoreC , semaphoreD)).start();
		new Thread(() -> application.printer("D" , semaphoreD , semaphoreA)).start();
	}
	
	private void printer(String name , Semaphore current , Semaphore next)
	{
		for ( int i = 0; i < times; i++ )
		{
			try
			{
				current.acquire();
				System.out.print(name);
			} catch ( InterruptedException e )
			{
				e.printStackTrace();
			} finally
			{
				next.release();
			}
		}
		
		
	}
}
