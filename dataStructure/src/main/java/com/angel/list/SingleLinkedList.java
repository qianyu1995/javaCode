package com.angel.list;

/**
 * 单链表
 */
public class SingleLinkedList<E>
{
	/**
	 * 头结点 尾节点
	 */
	private Node<E> head, rear;
	
	/**
	 * 链表元素格式
	 */
	private int size = 0;
	
	/**
	 * 构造函数
	 */
	public SingleLinkedList()
	{
		head = new Node<>();
		rear = head;
	}
	
	
	/**
	 * 返回头结点
	 */
	public Node<E> getHead()
	{
		return head;
	}
	
	/**
	 * 返回首元节点
	 *
	 * @return
	 */
	public Node<E> getFirstNode()
	{
		return head.next;
	}
	
	/**
	 * 返回尾节点
	 *
	 * @return
	 */
	public Node<E> getLastNode()
	{
		return rear;
	}
	
	
	/**
	 * 添加节点
	 *
	 * @param e
	 */
	public void add(E e)
	{
		//初始化节点
		Node<E> temp = new Node(e , null);
		//尾部添加一个节点
		rear.next = temp;
		//重置尾节点
		rear = temp;
		//元素个数自增
		size++;
	}
	
	/**
	 * 查询指定索引出的节点
	 *
	 * @param index
	 * @return
	 */
	public E find(int index)
	{
		if ( index < 0 || index >= size )
		{
			throw new IllegalArgumentException("Remove failed,index is illegal");
		}
		Node<E> temp = head;
		for ( int i = 0; i <= index; i++ )
		{
			temp = temp.next;
		}
		return temp.e;
	}
	
	/**
	 * 删除链表指定位置的元素
	 *
	 * @param index
	 * @return
	 */
	public E remove(int index)
	{
		if ( index < 0 || index >= size )
		{
			throw new IllegalArgumentException("Index is illegal");
		}
		Node temp = head;
		for ( int i = 0; i < index; i++ )
		{
			temp = temp.next;
		}
		Node delNode = temp.next;
		temp.next = delNode.next;
		delNode.next = null;
		if ( rear.equals(delNode) )
		{
			rear = temp;
		}
		size--;
		return (E) delNode.e;
	}
	
	/**
	 * 链表是否为空
	 *
	 * @return
	 */
	public Boolean isEmpty()
	{
		return size == 0;
	}
	
	/**
	 * 打印链表
	 *
	 * @return
	 */
	public String list()
	{
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("[");
		for ( int i = 0; i < size; i++ )
		{
			stringBuffer.append(find(i));
			if ( i != size - 1 )
			{
				stringBuffer.append(", ");
			}
		}
		stringBuffer.append("]");
		return stringBuffer.toString();
	}
	
	/**
	 * 逆序输出
	 */
	public Node<E> reverse(Node<E> head)
	{
		//空链表和一个结点的链表无需反转
		if ( head == null || head.next == null )
		{
			return head;
		}
		Node res = reverse(head.next);
		//第二个结点的next指向第一个结点
		head.next.next = head;
		//第一个结点的next指向null
		head.next = null;
		return res;
	}
	
	/**
	 * 循环输出
	 *
	 * @param node
	 * @return
	 */
	public static Node reverseList(Node node)
	{
		Node pre = null;
		Node next = null;
		while ( node != null )
		{
			next = node.next;
			node.next = pre;
			pre = node;
			node = next;
		}
		return pre;
	}
}