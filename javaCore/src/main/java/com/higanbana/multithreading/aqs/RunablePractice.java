package com.higanbana.multithreading.aqs;

/**
 * @author 陈明
 * @date 2019/9/18 15:05
 */
public class RunablePractice implements Runnable
{
	
	@Override
	public void run()
	{
		System.out.println("hello world");
	}
	
	public static void main(String[] args)
	{
		Thread thread = new Thread(new RunablePractice());
		thread.start();
	}
}
