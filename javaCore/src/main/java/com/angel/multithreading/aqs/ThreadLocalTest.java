package com.angel.multithreading.aqs;

/**
 * @author 陈明
 * @date 2019/9/27 14:41
 */
public class ThreadLocalTest
{
	private static ThreadLocal<String> threadLocal = new ThreadLocal<String>()
	{
		@Override
		protected String initialValue()
		{
			return "初始值";
		}
	};
	
	public static void main(String[] args) throws InterruptedException
	{
		
		threadLocal.remove();
		System.out.println(threadLocal.get());
	}
}
