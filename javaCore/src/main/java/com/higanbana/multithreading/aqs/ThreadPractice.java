package com.higanbana.multithreading.aqs;

/**
 * @author 陈明
 * @date 2019/9/18 14:19
 */

public class ThreadPractice extends Thread
{
	
	
	public static void main(String[] args)
	{
		Thread thread1 = new ThreadPractice();
		Thread thread2 = new ThreadPractice();
		//jion()方法 阻塞其它线程
		// yield()方法 表示暂停当前正在执行的线程对象（及放弃当前拥有的cup资源）
		//setPriority 设置优先级 1~10 ，如果小于1或大于10，则抛出异常throw new IllegalArgumentException()，默认是5。
		thread1.setPriority(1);
		thread2.setPriority(10);
		thread1.start();
		thread2.start();
		
		
	}
	
}
