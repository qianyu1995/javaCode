package com.angel.list;

/**
 * 顺序表
 * <p>
 * 动态数组实现顺序表
 */
public class DynamicArrayList<E>
{
	/**
	 * 存放数据的容器
	 */
	private E[] data;
	
	/**
	 * 容量
	 */
	private int size;
	
	/**
	 * 初始化默认容量
	 */
	private final static int DEFAULT_CAPACITY = 2;
	
	public DynamicArrayList()
	{
		this(DEFAULT_CAPACITY);
	}
	
	public DynamicArrayList(int size)
	{
		data = (E[]) (new Object[size]);
		this.size = 0;
	}
	
	/**
	 * 在所有元素后添加新元素
	 *
	 * @param e
	 */
	public void addLast(E e)
	{
		add(size , e);
	}
	
	/**
	 * 在所有元素前添加新元素
	 *
	 * @param e
	 */
	public void addFirst(E e)
	{
		add(0 , e);
	}
	
	public void add(E e)
	{
		addLast(e);
	}
	
	
	/**
	 * 在index位置插入元素
	 *
	 * @param index
	 * @param e
	 */
	public void add(int index , E e)
	{
		if ( size == data.length )
		{
			resize(data.length*2);
		}
		if ( index < 0 || index > size )
		{
			throw new IllegalArgumentException("Get failed,index is illegal");
		}
		//index位置后的元素向右移动
		for ( int i = size; i > index; i-- )
		{
			data[i] = data[i - 1];
		}
		data[index] = e;
		size++;
	}
	
	/**
	 * 获取指定索引出的数值
	 *
	 * @param index
	 * @return
	 */
	public E get(int index)
	{
		if ( index < 0 || index >= size )
		{
			throw new IllegalArgumentException("Get failed,index is illegal");
		}
		return data[index];
	}
	
	/**
	 * 查找数组中是否有元素e,有就返回下标，没有就返回-1
	 *
	 * @param e
	 * @return
	 */
	public boolean contains(E e)
	{
		for ( int i = 0; i < size; i++ )
		{
			if ( data[i] == e )
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 查找数组中元素e所在索引
	 *
	 * @param e
	 * @return
	 */
	public int find(E e)
	{
		for ( int i = 0; i < size; i++ )
		{
			if ( data[i] == e )
			{
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * 设置index位置元素值为e
	 *
	 * @param index
	 * @param e
	 */
	public void set(int index , E e)
	{
		if ( index < 0 || index >= size )
		{
			throw new IllegalArgumentException("Get failed,index is illegal");
		}
		data[index] = e;
	}
	
	/**
	 * 删除指定位置元素
	 *
	 * @param index
	 * @return
	 */
	public E remove(int index)
	{
		if ( size == 0 )
		{
			throw new IllegalArgumentException("Remove failed,array is empty");
		}
		if ( index < 0 || index >= size )
		{
			throw new IllegalArgumentException("Remove failed,index is illegal");
		}
		for ( int i = index + 1; i < size; i++ )
		{
			data[i - 1] = data[i];
		}
		size--;
		return data[index];
	}
	
	
	/**
	 * 动态调整数组大小
	 *
	 * @param newCapacity
	 */
	private void resize(int newCapacity)
	{
		E[] newData = (E[]) new Object[newCapacity];
		for ( int i = 0; i < size; i++ )
		{
			newData[i] = data[i];
		}
		data = newData;
	}
	
	
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append(String.format("Array size=%d,MaxCapacity=%d\n" , size , data.length));
		builder.append("[");
		for ( int i = 0; i < size; i++ )
		{
			builder.append(data[i]);
			if ( i != size - 1 )
			{
				builder.append(", ");
			}
		}
		builder.append("]");
		return builder.toString();
	}
}
