package com.higanbana.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 陈明
 * @date 2020/3/26 14:58
 */
public class Product implements Serializable
{
	private static final long serialVersionUID = 2090476998226117874L;
	
	private String id;
	
	private String name;
	
	private String code;
	
	private BigDecimal price;
	
	private Date createTime;
	
	private Date updateTime;
	
	private ProductDetail productDetail;
	
	
	public String getId()
	{
		return id;
	}
	
	public void setId(String id)
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
	
	public String getCode()
	{
		return code;
	}
	
	public void setCode(String code)
	{
		this.code = code;
	}
	
	public BigDecimal getPrice()
	{
		return price;
	}
	
	public void setPrice(BigDecimal price)
	{
		this.price = price;
	}
	
	public Date getCreateTime()
	{
		return createTime;
	}
	
	public void setCreateTime(Date createTime)
	{
		this.createTime = createTime;
	}
	
	public Date getUpdateTime()
	{
		return updateTime;
	}
	
	public void setUpdateTime(Date updateTime)
	{
		this.updateTime = updateTime;
	}
	
	public ProductDetail getProductDetail()
	{
		return productDetail;
	}
	
	public void setProductDetail(ProductDetail productDetail)
	{
		this.productDetail = productDetail;
	}
	
	@Override
	public String toString()
	{
		return "Product{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", code='" + code + '\'' +
				", price=" + price +
				", createTime=" + createTime +
				", updateTime=" + updateTime +
				", productDetail=" + productDetail +
				'}';
	}
}
