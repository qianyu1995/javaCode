package com.angel.multithreading.cas;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.IntStream;

/**
 * LongAdder，他就是尝试使用分段CAS以及自动分段迁移的方式来大幅度提升多线程高并发执行CAS操作的性能！
 */
public class LongAdderDemo
{
	public static void main(String[] args)
	{
		LongAdder longAdder = new LongAdder();
		ExecutorService executor = Executors.newFixedThreadPool(10);
		IntStream.range(0,2).forEach(
				(int i)->
				{
					executor.execute(()->
					{
						longAdder.add(1);;
					});
				}
		);
		
		executor.shutdown();
		while ( !executor.isTerminated() )
		{
		
		}
		
		
		System.out.println(longAdder.intValue());
		
		
	}
}
