import com.higanbana.domain.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import util.HibernateUtils;


/**
 * @author 陈明
 * @date 2020/3/25 14:48
 */
public class SecondCacheTest
{
	private static  SessionFactory factory;
	static
	{
		factory = new Configuration().configure().buildSessionFactory();
	}
	
	@Test
	//演示:类缓存
	public void fun1()
	{
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		
		Customer customer1 = (Customer) session.get(Customer.class , 1);
		
		session.clear();//清空一级缓存中的内容
		
		Customer customer2 = (Customer) session.get(Customer.class , 1);
		
		System.out.println(customer1 == customer2);//false
		//二级缓存在缓存数据时,并不是以对象的形式缓存. 缓存的是对象数据的散列. 每次从二级缓存拿 会在一级缓存中组装成对象.
		
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
	
	@Test
	public void fun2()
	{
		// * 将二级缓存关闭，查询3将触发select语句。
		Session s1 = factory.openSession();
		s1.beginTransaction();
		
		//1 查询id=1 -- 执行select （查询后，将数据存放在一级缓存，之后由一级缓存同步到二级缓存）
		Customer c1 = (Customer) s1.get(Customer.class , 1);
		System.out.println(c1);
		//2 查询id=1 --从一级缓存获取
		Customer c2 = (Customer) s1.get(Customer.class , 1);
		System.out.println(c2);
		
		s1.getTransaction().commit();
		s1.close();
		
		System.out.println("----------");
		
		Session s2 = factory.openSession();
		s2.beginTransaction();
		
		//3 查询id=1 -- 从二级缓存获取
		Customer c3 = (Customer) s2.get(Customer.class , 1);
		System.out.println(c3);
		
		s2.getTransaction().commit();
		s2.close();
		
	}
	
	@Test
	//演示:时间戳
	public void fun3(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		
		Customer customer1 = (Customer) session.get(Customer.class, 1);
		
		session.createQuery("update Customer set name=:name where id = :id ")
				.setString("name", "rose").setInteger("id", 1).executeUpdate();
		
		session.clear();
		
		Customer customer2 = (Customer) session.get(Customer.class, 1);
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
}
