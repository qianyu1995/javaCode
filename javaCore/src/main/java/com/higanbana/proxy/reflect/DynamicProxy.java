package com.higanbana.proxy.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author 陈明
 * @date 2020/1/7 11:20
 */
public class DynamicProxy
{
	//定义要代理哪个类
	private Object obj = null;
	
	private InvocationHandler handler;
	
	//通过构造函数传递被代理对象
	public DynamicProxy(Object _obj)
	{
		Class<? extends Object> c = _obj.getClass();
		handler = new MyInvocationHandler(_obj);
		//生成被代理类的代理类
		this.obj = Proxy.newProxyInstance(c.getClassLoader() , c.getInterfaces() , handler);
	}
	
	//执行代理类的方法
	public Object exec(String methodName , Object... args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException
	{
		//返回值
		Object result = null;
		//方法中的参数类型
		Class<?>[] c = new Class[args.length];
		int i = 0;
		//获得参数的类型
		for ( Object o : args )
		{
			c[i] = o.getClass();
			i++;
		}
		//根据方法名称和参数类型查找到唯一一个方法
		Method method=this.obj.getClass().getMethod(methodName, c);
		//执行该方法
		result = method.invoke(this.obj, args);
		return result;
	}
}
