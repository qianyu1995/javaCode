package com.higanbana.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 陈明
 * @date 2020/3/26 15:02
 */
public class ProductDetail implements Serializable
{
	private static final long serialVersionUID = -5151952338314895441L;
	
	private String id;
	
	private String productId;
	
	private String productName;
	
	private String qualityLevel;
	
	private String manufacturer;
	
	private String context;
	
	private Date createTime;
	
	private Date updateTime;
	
	public String getId()
	{
		return id;
	}
	
	public void setId(String id)
	{
		this.id = id;
	}
	
	public String getProductId()
	{
		return productId;
	}
	
	public void setProductId(String productId)
	{
		this.productId = productId;
	}
	
	public String getProductName()
	{
		return productName;
	}
	
	public void setProductName(String productName)
	{
		this.productName = productName;
	}
	
	public String getQualityLevel()
	{
		return qualityLevel;
	}
	
	public void setQualityLevel(String qualityLevel)
	{
		this.qualityLevel = qualityLevel;
	}
	
	public String getManufacturer()
	{
		return manufacturer;
	}
	
	public void setManufacturer(String manufacturer)
	{
		this.manufacturer = manufacturer;
	}
	
	public String getContext()
	{
		return context;
	}
	
	public void setContext(String context)
	{
		this.context = context;
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
	
	@Override
	public String toString()
	{
		return "ProductDetail{" +
				"id='" + id + '\'' +
				", productId='" + productId + '\'' +
				", productName='" + productName + '\'' +
				", qualityLevel='" + qualityLevel + '\'' +
				", manufacturer='" + manufacturer + '\'' +
				", context='" + context + '\'' +
				", createTime=" + createTime +
				", updateTime=" + updateTime +
				'}';
	}
}
