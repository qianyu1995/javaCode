package com.angel.multithreading.aqs;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author 陈明
 * @date 2019/9/20 11:49
 */
public class SemaphoreTest
{
	public static void main(String[] args)
	{
		//定义许可证
		final Semaphore semaphore = new Semaphore(2);
		IntStream.range(0 , 4).forEach(i ->
		{
			new Thread(() ->
			{
				System.out.println(Thread.currentThread().getName() + "开始");
				try
				{
					semaphore.acquire(); // 一次拿一个许可证
					System.out.println(Thread.currentThread().getName() + "获取许可证");
					TimeUnit.SECONDS.sleep(3);
				} catch ( InterruptedException e )
				{
					e.printStackTrace();
				} finally
				{
					System.out.println(Thread.currentThread().getName() + "释放许可证");
					semaphore.release();
				}
				System.out.println(Thread.currentThread().getName() + "结束");
			} , "thread" + (i + 1)).start();
		});
	}
}
