package com.higanbana.proxy.reflect;

import java.lang.reflect.InvocationTargetException;

/**
 * @author 陈明
 * @date 2020/1/7 11:20
 */
public class Client
{

	private static DynamicProxy proxy;

	private static Subject subject;

	public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
	{
		subject = new RealSubject();
		proxy = new DynamicProxy(subject);
		String[] str = { "1111" };
		proxy.exec("doSomething", str);

	}
}
