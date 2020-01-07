package com.higanbana.proxy.reflect;

/**
 * @author 陈明
 * @date 2020/1/7 11:20
 */
public class RealSubject implements Subject
{
	/**
	 * 实际业务操作
	 * @param message
	 */
	@Override
	public void doSomething(String message)
	{
		System.out.println(message);
	}
}
