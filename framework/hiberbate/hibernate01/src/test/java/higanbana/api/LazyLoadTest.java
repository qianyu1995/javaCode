package higanbana.api;

import com.higanbana.domain.Customer;
import com.higanbana.domain.Order;
import com.higanbana.util.HibernateUtils;
import org.hibernate.Session;
import org.junit.Test;

import java.util.List;
import java.util.Set;

/**
 * @author 陈明
 * @date 2020/3/24 16:11
 */
public class LazyLoadTest
{
	@Test
	//类级别懒加载
	//load方法
	// class lazy属性
	// 默认值: true load获得时,会返回代理对象,不查询数据库.使用时才查询
	public void fun1()
	{
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		Customer c = (Customer) session.load(Customer.class , 1);
		
		System.out.println(c.getName());
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
	
	@Test
	//类级别懒加载
	//load方法
	// class lazy属性
	// lazy: false  load方法执行就会发送sql语句.与get方法一致.
	public void fun2()
	{
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		Customer c = (Customer) session.load(Customer.class , 1);
		System.out.println(c.getName());
		session.getTransaction().commit();
		session.close();
	}
	
	@Test
	//关联级别懒加载
	//默认: 与我关联的数据,在使用时才会加载.
	public void fun3()
	{
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		Customer c = (Customer) session.get(Customer.class , 4);
		
		
		for ( Order o : c.getOrders() )
		{
			System.out.println(o.getName());
		}
		
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
	
	@Test
	//关联级别懒加载
	//lazy: false
	public void fun4()
	{
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		Customer c = (Customer) session.get(Customer.class , 1);
		
		
		for ( Order o : c.getOrders() )
		{
			System.out.println(o.getName());
		}
		
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
	
	@Test
	//关联级别懒加载
	//lazy: false/true
	//fetch:join
	public void fun5()
	{
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		Customer c = (Customer) session.get(Customer.class , 1);
		
		
		for ( Order o : c.getOrders() )
		{
			System.out.println(o.getName());
		}
		
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
	
	@Test
	//关联级别懒加载
	//lazy: false/true
	//fetch:subselect
	public void fun6()
	{
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//1 查询客户
		List<Customer> allCustomer = session.createQuery("from Customer").list();
		Customer customer = allCustomer.get(0);
		System.out.println(customer.getName());
		
		//2 查询客户订单数
		Set<Order> orderSet = customer.getOrders();
		System.out.println(orderSet.size());
		
		//3 查询客户订单详情
		for ( Order order : orderSet )
		{
			System.out.println(order);
		}
		
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
}
