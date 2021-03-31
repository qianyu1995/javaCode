package com.angel.queue;

/**
 * @author 陈明
 * @date 2020/4/15 17:40
 */
public interface Queue<E>
{
	void enqueue(E e);
	
	E dequeue();
	
	E getFront();
	
	int getSize();
	
	boolean isEmpty();
}
