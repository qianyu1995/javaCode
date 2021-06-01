package com.angel.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * cglib基于继承,无法对final、static、 private等方法进行代理
 */
public class CglibProxy
{
	
	public void test()
	{
		System.out.println(1 % 10);
	}
	
	public static void main(String[] args)
	{
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(CglibProxy.class);
		enhancer.setCallback(
				new MethodInterceptor()
				{
					@Override
					public Object intercept(Object obj , Method method , Object[] args , MethodProxy proxy) throws Throwable
					{
						System.out.println("before method run...");
						Object result = proxy.invokeSuper(obj , args);
						System.out.println("after method run...");
						return result;
					}
				}
		);
		
		CglibProxy cglibProxy = (CglibProxy)enhancer.create();
		cglibProxy.test();
	}
}
