package com.higanbana.proxy.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author 陈明
 * @date 2020/1/7 11:20
 */
public class MyInvocationHandler implements InvocationHandler
{
	//被代理的对象
	private Object target = null;
	
	//通过构造函数传递一个对象
	public MyInvocationHandler(Object _obj)
	{
		this.target = _obj;
	}
	
	@Override
	public Object invoke(Object proxy , Method method , Object[] args) throws Throwable
	{
		//设置返回值
		Object result = null;
		//前置通知
		this.before();
		//执行被代理的方法
		result = method.invoke(this.target , args);
		//后置通知
		this.after();
		
		//返回值
		return result;
	}
	
	public void before()
	{
		System.out.println("执行before方法");
	}
	
	//后置通知
	public void after()
	{
		System.out.println("执行after方法");
	}
}
