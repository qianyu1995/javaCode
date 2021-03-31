package com.angel.stack;

import java.util.Arrays;

/**
 * @author 陈明
 * @date 2020/4/15 17:17
 */
public class ArrayStack<E> implements Stack<E>
{
	/**
	 * 栈的大小
	 */
	private int maxSize;
	
	/**
	 * 数组，数组模拟栈，数据就放在该数组
	 */
	private E[] stack; //
	
	/**
	 * top表示栈顶，初始化为-1
	 */
	private int top = -1;
	
	public ArrayStack(int maxSize)
	{
		this.maxSize = maxSize;
		stack = (E[]) (new Object[this.maxSize]);
	}
	
	public boolean isFull()
	{
		return top == maxSize - 1;
	}
	
	@Override
	public int getSize()
	{
		return 0;
	}
	
	@Override
	public boolean isEmpty()
	{
		return top == -1;
	}
	
	@Override
	public void push(E value)
	{
		//先判断栈是否满
		if ( isFull() )
		{
			resize(maxSize + maxSize >> 1);
		}
		top++;
		stack[top] = value;
	}
	
	/**
	 * 出栈, 将栈顶的数据返回并删除
	 *
	 * @return
	 */
	@Override
	public E pop()
	{
		//先判断栈是否空
		if ( isEmpty() )
		{
			//抛出异常
			throw new RuntimeException("栈空，没有数据~");
		}
		E value = stack[top];
		top--;
		return value;
	}
	
	/**
	 * 返回栈顶的值
	 * @return
	 */
	@Override
	public E peek()
	{
		//先判断栈是否空
		if ( isEmpty() )
		{
			//抛出异常
			throw new RuntimeException("栈空，没有数据~");
		}
		E value = stack[top];
		return value;
	}
	
	/**
	 * 遍历栈
	 */
	public void list()
	{
		if ( isEmpty() )
		{
			System.out.println("栈空，没有数据~~");
			return;
		}
		//需要从栈顶开始显示数据
		for ( int i = top; i >= 0; i-- )
		{
			System.out.printf("stack[%d]=%d\n" , i , stack[i]);
		}
	}
	
	/**
	 * 动态调整数组大小
	 *
	 * @param newCapacity
	 */
	private void resize(int newCapacity)
	{
		stack = Arrays.copyOf(stack, newCapacity);
	}
}
