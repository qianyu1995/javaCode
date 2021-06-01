package com.angel.multithreading.cas;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author 陈明
 * @date 2021/4/26 9:44
 */
public class AtomicStampedReferenceDemo
{
	public static void main(String[] args)
	{
		// 实例化、取当前值和 stamp 值
		Integer initialRef = 0, initialStamp = 0;
		AtomicStampedReference<Integer> asr = new AtomicStampedReference<>(initialRef , initialStamp);
		System.out.println("currentValue=" + asr.getReference() + ", currentStamp=" + asr.getStamp());
		
		// compare and set
		final Integer newReference = 666, newStamp = 999;
		final boolean casResult = asr.compareAndSet(initialRef , newReference , initialStamp , newStamp);
		System.out.println("currentValue=" + asr.getReference()
				+ ", currentStamp=" + asr.getStamp()
				+ ", casResult=" + casResult);
		
		
		
	}
}
