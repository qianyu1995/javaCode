package com.angel.multithreading.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * public final int get() //��ȡ��ǰ��ֵ
 * public final int getAndSet(int newValue)//��ȡ��ǰ��ֵ���������µ�ֵ
 * public final int getAndIncrement()//��ȡ��ǰ��ֵ��������
 * public final int getAndDecrement() //��ȡ��ǰ��ֵ�����Լ�
 * public final int getAndAdd(int delta) //��ȡ��ǰ��ֵ��������Ԥ�ڵ�ֵ
 * boolean compareAndSet(int expect, int update) //����������ֵ����Ԥ��ֵ������ԭ�ӷ�ʽ����ֵ����Ϊ����ֵ��update��
 * public final void lazySet(int newValue)//��������ΪnewValue,ʹ�� lazySet ����֮����ܵ��������߳���֮���һС��ʱ���ڻ��ǿ��Զ����ɵ�ֵ��
 */
public class AtomicIntegerTest
{
	public static void main(String[] args)
	{
		
		int temvalue = 0;
		AtomicInteger i = new AtomicInteger(0);
		temvalue = i.getAndSet(3);
		System.out.println("temvalue:" + temvalue + ";  i:" + i);//temvalue:0;  i:3
		temvalue = i.getAndIncrement();
		System.out.println("temvalue:" + temvalue + ";  i:" + i);//temvalue:3;  i:4
		temvalue = i.getAndAdd(5);
		System.out.println("temvalue:" + temvalue + ";  i:" + i);//temvalue:4;  i:9
		
		
		System.out.println(i.getAndAdd(10));
		System.out.println(i.get());
		System.out.println(i.compareAndSet(19,20 ));
		System.out.println(i.get());
	}
}
