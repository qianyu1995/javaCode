package com.angel.queue;


/**
 * 队列
 * 不可变数组实现的队列  会有空间浪费现象
 */
public class ArrayQueue<E> implements Queue<E>
{
	/**
	 * 表示数组的最大容量
	 */
	private int maxSize;
	
	/**
	 * 队列头
	 */
	private int front;
	
	/**
	 * 队列尾
	 */
	private int rear;
	
	/**
	 * 该数据用于存放数据, 模拟队列
	 */
	private E[] array;
	
	/**
	 * 创建队列的构造器
	 *
	 * @param arrMaxSize
	 */
	public ArrayQueue(int arrMaxSize)
	{
		maxSize = arrMaxSize;
		array = (E[]) new Object[maxSize];
		// 指向队列头部，分析出front是指向队列头的前一个位置
		front = -1;
		//指向队列尾，指向队列尾的数据
		rear = -1;
	}
	
	/**
	 * 判断是否已满
	 *
	 * @return
	 */
	public Boolean isFull()
	{
		return rear == maxSize - 1;
	}
	
	/**
	 * 入列
	 *
	 * @param e
	 */
	@Override
	public void enqueue(E e)
	{
		// 判断队列是否满
		if ( isFull() )
		{
			System.out.println("队列满，不能加入数据~");
			return;
		}
		rear++; // 让rear 后移
		array[rear] = e;
	}
	
	@Override
	public E dequeue()
	{
		// 判断队列是否空
		if ( isEmpty() )
		{
			// 通过抛出异常
			throw new RuntimeException("队列空，不能取数据");
		}
		front++; // front后移
		return array[front];
	}
	
	@Override
	public E getFront()
	{
		// 判断
		if ( isEmpty() )
		{
			throw new RuntimeException("队列空的，没有数据~~");
		}
		return array[front+1];
	}
	
	/**
	 * 获取队列容量
	 *
	 * @return
	 */
	@Override
	public int getSize()
	{
		return rear - front ;
	}
	
	/**
	 * 队列是否为空
	 *
	 * @return
	 */
	@Override
	public boolean isEmpty()
	{
		return front == rear;
	}
}
