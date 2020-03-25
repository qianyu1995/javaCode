package com.higanbana.domain;


/**
 * @author 陈明
 * @date 2020/1/8 16:10
 */

public class Order
{
	private Integer id;
	
	private String name;
	
	private Customer customer;
	
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
	
	public Customer getCustomer()
	{
		return customer;
	}
	
	public void setCustomer(Customer customer)
	{
		this.customer = customer;
	}
	
}
