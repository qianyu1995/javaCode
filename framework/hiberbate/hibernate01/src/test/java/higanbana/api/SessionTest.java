package higanbana.api;

import com.higanbana.domain.User;
import com.higanbana.util.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;


/**
 * @author 陈明
 * @date 2020/1/7 16:28
 */
public class SessionTest
{
	/**
	 * 添加数据
	 * hibernate.connection.autocommit 值为true时,需要session.flush(),再session.close();
	 * 								   值为false时,需要手动提交
	 */
	@Test
	public void add()
	{
		//1.加载配置文件
		Configuration configuration = new Configuration().configure();
		//2.创建sessionFactory
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		//3.获取session
		Session session = sessionFactory.openSession();
		
		//session.beginTransaction();
		
		User user = new User();
		user.setId("2");
		user.setName("彼岸花");
		session.save(user);
		
		//session.getTransaction().commit();
		
		//关闭资源
		session.flush();
		session.close();
		sessionFactory.close();
	}
	
	/**
	 * 测试hibernate 查询
	 */
	@Test
	public void get()
	{
		//1 读取配置文件
		Configuration configuration = new Configuration().configure();
		//2 根据配置 创建Factory
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		//3 通过获得操作数据库的session对象
		Session session = sessionFactory.openSession();
		User user = (User) session.get("com.higanbana.domain.User", "2");
		System.out.println(user);
		session.close();
		sessionFactory.close();
	}
	
	/**
	 * 测试hibernate 查询
	 */
	@Test
	public void load()
	{
		//1 读取配置文件
		Configuration configuration = new Configuration().configure();
		//2 根据配置 创建Factory
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		//3 通过获得操作数据库的session对象
		org.hibernate.classic.Session session = sessionFactory.openSession();
		User user = (User) session.load("com.higanbana.domain.User", "1");
		System.out.println(user);
		session.close();
		sessionFactory.close();
	}
	
	
	
	/**
	 * 测试hibernate 更新
	 */
	@Test
	public void update()
	{
		//1 读取配置文件
		Configuration conf = new Configuration().configure();
		//2 根据配置 创建Factory
		SessionFactory sessionFactory = conf.buildSessionFactory();
		//3 通过获得操作数据库的session对象
		Session session = sessionFactory.openSession();
		//4 操作数据库
		User user = (User) session.get(User.class, "1");
		user.setName("tom");
		session.flush();
		session.close();
		sessionFactory.close();
	}
	
	/**
	 * 测试hibernate 删除
	 */
	@Test
	public void delete()
	{
		//1 读取配置文件
		Configuration conf = new Configuration().configure();
		//2 根据配置 创建Factory
		SessionFactory sessionFactory = conf.buildSessionFactory();
		//3 通过获得操作数据库的session对象
		Session session = sessionFactory.openSession();
		//4 操作数据库
		Transaction transaction = session.beginTransaction();
		User user = (User) session.get(User.class, "1");
		session.delete(user);
		//5 关闭资源
		transaction.commit();
		session.close();
		sessionFactory.close();
	}
	
	@Test
	//1. evict 将缓存中的对象移除.
	//2. clear 清空1级缓存
	public void fun1(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		session.get(User.class, "1");
		
		session.clear();
		
		session.get(User.class, "1");
		
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
	
	@Test
	//3 refresh 刷新 => 强制刷新缓存中的对象 => (可以用来解决缓存与数据库数据不同步的问题)
	public void fun2(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		User u1 = (User) session.get(User.class, "1");
		
		session.refresh(u1); //将缓存中的对象立刻与数据库同步,会再发送一个sql语句
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
	
	@Test
	//4 flush 对比快照,并提交缓存对象
	public void fun3(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		User u1 = (User) session.get(User.class, "1");
		
		u1.setName("angel");
		
		session.flush();// 立刻提交session缓存中的对象到数据库
		
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
	
	@Test
	//在我们使用Hibernate时候,注意要避免出现,两个相同的ID对象.放入一级缓存的情况.
	public void fun6(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		User u = (User) session.get(User.class, "1");// 持久化,缓存中存在
		
		session.evict(u); // 游离态,缓存中不存在
		
		User u2 = (User) session.get(User.class, "1");// 持久化,缓存中存在
		
		session.update(u); // 将U重新变为持久化状态,缓存中存在
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
}
