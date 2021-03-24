package com.angel.tutorial_8.lambda;

/**
 * @author 陈明
 * @date 2020/1/7 11:28
 */
public class Lambda
{
	Runnable r1 = () -> System.out.println(this);
	Runnable r2 = () -> System.out.println(toString());
	
	@Override
	public String toString()
	{
		return "Hello, lambda!";
	}
	
	public static void main(String[] args)
	{
		new Lambda().r1.run();
		new Lambda().r2.run();
	}
	
}
