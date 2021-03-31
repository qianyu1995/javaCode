package com.angel.list;


/**
 * 顺序表
 * <p>
 * 静态数组实现顺序表
 */
public class StaticArrayList
{
	/**
	 * 存放数据的容器
	 */
	private final int[] data;
	
	/**
	 * 容量
	 */
	private int size;
	
	/**
	 * 初始化默认容量
	 */
	private final static int DEFAULT_CAPACITY = 10;
	
	public StaticArrayList()
	{
		this(DEFAULT_CAPACITY);
	}
	
	public StaticArrayList(int size)
	{
		data = new int[size];
		this.size = 0;
	}
	
	/**
	 * 在所有元素后添加新元素
	 *
	 * @param e
	 */
	public void addLast(int e)
	{
		add(size , e);
	}
	
	/**
	 * 在所有元素前添加新元素
	 *
	 * @param e
	 */
	public void addFirst(int e)
	{
		add(0 , e);
	}
	
	public void add(int e)
	{
		addLast(e);
	}
	
	
	/**
	 * 在index位置插入元素
	 *
	 * @param index
	 * @param e
	 */
	public void add(int index , int e)
	{
		if ( size == data.length )
		{
			throw new IllegalArgumentException("Add failed,array is full");
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
	public int get(int index)
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
	public boolean contains(int e)
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
	public int find(int e)
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
	public void set(int index , int e)
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
	public int remove(int index)
	{
		if ( size == 0 )
		{
			throw new IllegalArgumentException("Remove failed,array is empty");
		}
		if ( index < 0 || index >= size )
		{
			throw new IllegalArgumentException("Remove failed,index is illegal");
		}
		for ( int i = index + 1; i < size ; i++ )
		{
			data[i-1] = data[i];
		}
		size--;
		return data[index];
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
