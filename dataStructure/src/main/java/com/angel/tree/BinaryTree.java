package com.angel.tree;


/**
 * 二叉搜索树（BST）
 */
public class BinaryTree<E>
{
	private Node<E> root;
	
	private int size;
	
	
	private class Node<E>
	{
		public E element;
		
		public Node<E> left, right;
		
		public Node(E element)
		{
			this.element = element;
			this.left = null;
			this.right = null;
		}
	}
	
	
	public BinaryTree()
	{
		root = null;
		
		size =0;
	}
	
	/**
	 * 获取节点个数
	 * @return
	 */
	public int size()
	{
		return size;
	}
	
	/**
	 * 判断树是否为空
	 * @return
	 */
	public boolean isEmpty()
	{
		return size == 0;
	}
	
	
	
	
	
	
	
}
