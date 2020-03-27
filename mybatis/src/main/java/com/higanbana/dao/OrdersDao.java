package com.higanbana.dao;


import com.higanbana.domain.OrdersExt;
import com.higanbana.domain.User;

import java.util.List;



public interface OrdersDao
{
	//一对一之resultType
	List<OrdersExt> findOrdersAndUser();
	
	//一对一之resultMap
	List<OrdersExt> findOrdersAndUserRstMap();
	
	//一对多
	 List<OrdersExt> findOrdersAndDetailRstMap();
	
	//多对多
	List<User> findUserAndItemsRstMap();
	
	//延迟加载
	List<OrdersExt> findOrderAndUserLazyLoading();
}
