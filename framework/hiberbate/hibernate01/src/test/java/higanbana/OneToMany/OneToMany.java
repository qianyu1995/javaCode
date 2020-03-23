package higanbana.OneToMany;

import com.higanbana.domain.Customer;
import com.higanbana.domain.Order;
import com.higanbana.util.HibernateUtils;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import java.util.Set;


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
		//------------------------------------------------
		
		Customer c = (Customer) session.get(Customer.class , 1);
		
		session.delete(c);
		
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
	
}
