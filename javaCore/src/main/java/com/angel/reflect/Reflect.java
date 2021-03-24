package com.angel.reflect;

import java.lang.reflect.Field;


public class Reflect
{
	public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException
	{
		Class user = User.class;
		User entity_user = new User();
		System.out.println(user.getName());
		Field[] array = user.getDeclaredFields();
		for ( Field field : array )
		{
			field.setAccessible(Boolean.TRUE);
			System.out.println(field.getName() + " " + field.getType() +"　" + field.getModifiers());
		}
		
		Field name = user.getDeclaredField("name");
		name.setAccessible(Boolean.TRUE);
		name.set(entity_user, "angel");
		System.out.println(entity_user.toString());
	}
	
}


class User
{
	private String name;
	
	private Integer age;
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public Integer getAge()
	{
		return age;
	}
	
	public void setAge(Integer age)
	{
		this.age = age;
	}
	
	@Override
	public String toString()
	{
		return "User{" +
				"name='" + name + '\'' +
				", age=" + age +
				'}';
	}
}