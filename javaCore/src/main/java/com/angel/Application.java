package com.angel;


/**
 * @author 陈明
 * @date 2020/4/16 10:22
 */
public class Application
{
	public static void main(String[] args)
	{
		Application application = new Application();
		application.say();
	}
	
	
	public void say()
	{
		try
		{
			int a = 10;
		} catch ( Exception e )
		{
			e.printStackTrace();
			throw e;
		}
	}
}
