package com.angel.multithreading.cas;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.IntStream;

/**
 * LongAdder�������ǳ���ʹ�÷ֶ�CAS�Լ��Զ��ֶ�Ǩ�Ƶķ�ʽ��������������̸߲߳���ִ��CAS���������ܣ�
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
