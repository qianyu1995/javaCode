package com.higanbana.domain;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author 陈明
 * @date 2020/1/8 16:09
 */
public class Customer
{
	private Integer id;
	
	private String name;
	
	private Set<Order> orders = new HashSet<>();
	
	public Customer()
	{
	
	}
	
	public Customer(Integer id,String name)
	{
		this.id = id;
		this.name= name;
	}
	
	public Integer getId()
	{
		return id;
	}
	
	public void setId(Integer id)
	{
		this.id = id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public Set<Order> getOrders()
	{
		return orders;
	}
	
	public void setOrders(Set<Order> orders)
	{
		this.orders = orders;
	}
	
}
