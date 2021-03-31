package com.angel.stack;

/**
 * @author 陈明
 * @date 2020/4/15 17:16
 */
public interface Stack<E>
{
	int getSize();
	
	boolean isEmpty();
	
	/**
	 *
	 * @param e
	 */
	void push(E e);
	
	E pop();
	
	E peek();
}
