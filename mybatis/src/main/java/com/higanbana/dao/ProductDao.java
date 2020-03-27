package com.higanbana.dao;

import com.higanbana.domain.Product;

import java.util.List;

/**
 * @author 陈明
 * @date 2020/3/26 15:05
 */
public interface ProductDao
{
	/**
	 * 一对一
	 * @return
	 */
	List<Product> getProductDetail();
}
