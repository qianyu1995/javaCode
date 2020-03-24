package higanbana.onetomany;

import com.higanbana.domain.Customer;
import com.higanbana.domain.Order;
import com.higanbana.util.HibernateUtils;
import org.hibernate.Session;
import org.junit.Test;

import java.util.Iterator;


/**
 * @author 陈明
 * @date 2020/1/8 16:24
 */
public class OneToMany
{
	
	/**
	 * 只保存客户
	 */
	@Test
	public void fun1()
	{
		// 1 创建客户，并保存客户--成功
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		
		Customer customer = new Customer();
		customer.setName("angel");
		
		session.save(customer);
		
		session.getTransaction().commit();
		session.close();
	}
	
	/**
	 * 只保存订单
	 */
	@Test
	public void fun2()
	{
		// 1 创建客户，并保存客户--成功
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		
		Order order = new Order();
		order.setName("电脑");
		session.save(order);
		
		session.getTransaction().commit();
		session.close();
	}
	
	
	/**
	 * 客户关联订单 inverse="false"
	 */
	@Test
	public void fun3()
	{
		// 1 创建客户，并保存客户--成功
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		
		//------------------------------------------------
		Customer c = new Customer();
		c.setName("angel");
		
		Order o1 = new Order();
		o1.setName("肥皂");
		
		c.getOrders().add(o1);
		session.save(c);//保存对象
		session.save(o1);
		session.getTransaction().commit();
		session.close();
	}
	
	
	/**
	 * 客户关联订单 inverse="true"
	 */
	@Test
	public void fun4()
	{
		// 1 创建客户，并保存客户--成功
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		
		//------------------------------------------------
		Customer c = new Customer();
		c.setName("test");
		
		Order o1 = new Order();
		o1.setName("手机");
		
		o1.setCustomer(c);
		
		session.save(c);//保存对象
		session.save(o1);
		session.getTransaction().commit();
		session.close();
	}
	
	// Customer 的 inverse属性: true
	// 会报错 => Customer不负责维护外键, 直接删除Customer 会导致,order引用了无效的id.违反了外键约束.
	@Test
	public void fun5()
	{
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		Customer c = (Customer) session.get(Customer.class , 3);
		session.delete(c);
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
	
	
	/**
	 * 	cascade
	 * ----------------------------------------
	 */
	
	//我们希望在保存Customer时,自动将未保存的Orders当中的Order保存
	//cascade: save-update
	//inverse : false
	@Test
	public void fun6()
	{
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		Customer c = new Customer();
		c.setName("tom");
		
		Order o1 = new Order();
		o1.setName("肥皂");
		
		Order o2 = new Order();
		o2.setName("蜡烛");
		
		c.getOrders().add(o1);//维护关系
		c.getOrders().add(o2); //维护关系
		session.save(c);
		session.getTransaction().commit();
		session.close();
	}
	
	//级联修改
	//cascade: save-update
	//inverse : false
	@Test
	public void fun7()
	{
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		
		Customer customer = (Customer)session.get(Customer.class, 1);
		for ( Order order : customer.getOrders() )
		{
			//order 为持久态 不受cascade、inverse 影响
			order.setName("test");
			/*Order order1 = (Order) session.get(Order.class,order.getId() );
			System.out.println(order == order1);*/
		}
		session.getTransaction().commit();
		session.close();
	}
	
	@Test
	//cascade: delete
	//删除Customer时 ,会将Customer下的订单一并删除
	//inverse : false
	//inverse : true
	public void fun8(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		
		Customer c = (Customer) session.get(Customer.class, 1);//1条 select
		
		
		session.delete(c);//删除Customer
		// 删除两个Order
		
		//------------------------------------------------
		session.getTransaction().commit();
		
		session.close(); // 游离状态
	}
	
	
	@Test
	//inverse:false
	//cascade: delete-orphan 孤儿删除 => 当没有任何外键引用Order时,order 会被删除
	public void fun9(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		Customer c = (Customer) session.get(Customer.class, 1);
		Iterator<Order> it = c.getOrders().iterator();
		//注意: 删除Customer下的订单时,不能使用 c.setOrders(null); c.setOrders(new HashSet());
		while(it.hasNext()){ // 遍历Customer下的订单,并将订单删除 => 维护关系
			it.next();
			it.remove();
		}
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
	
	
}
