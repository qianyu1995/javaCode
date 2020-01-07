package com.higanbana.multithreading.aqs;

import java.util.concurrent.Exchanger;

/**
 * @author 陈明
 * @date 2019/9/20 14:48
 */
public class ExchangerTest
{
	public static void main(String[] args)
	{
		final Exchanger<String> exchanger = new Exchanger<>();
		
		new Thread(() ->
		{
			System.out.println("thread1开始");
			try
			{
				String exchange = exchanger.exchange("来自thread1的数据");
				System.out.println("接收thread2发送的数据：" + exchange);
			} catch ( InterruptedException e )
			{
				e.printStackTrace();
			}
			System.out.println("thread1结束");
		} , "thread1").start();
		
		new Thread(() ->
		{
			System.out.println("thread2开始");
			try
			{
				String exchange = exchanger.exchange("来自thread2的数据");
				System.out.println("接收thread1发送的数据：" + exchange);
			} catch ( InterruptedException e )
			{
				e.printStackTrace();
			}
			System.out.println("thread2结束");
		} , "thread2").start();
		
		
	}
}
