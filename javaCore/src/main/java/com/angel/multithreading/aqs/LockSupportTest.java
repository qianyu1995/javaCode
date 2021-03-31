package com.angel.multithreading.aqs;

import java.util.concurrent.locks.LockSupport;

/**
 * @author 陈明
 * @date 2021/3/4 10:43
 */
public class LockSupportTest
{
	public static void main(String[] args) throws InterruptedException
	{
		Thread thread = new Thread(()->
		{
			System.out.println("线程开始运行");
			LockSupport.park();
			System.out.println("线程结束");
		});
		
		thread.start();
		
		System.out.println("thread 启动");
		LockSupport.unpark(thread);
		System.out.println("LockSupport 进行了unpark");
	}
}
