package higanbana.api;

import com.higanbana.domain.User;
import com.higanbana.util.HibernateUtils;
import org.hibernate.Session;
import org.junit.Test;

import java.util.List;

/**
 * @author 陈明
 * @date 2019/11/26 14:25
 */
public class SessionCache_test
{
	@Test
	//证明session缓存的存在
	public void fun1(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		User u1 = (User) session.get(User.class, "1");// 发送select语句,从数据库取出记录.并封装成对象
		// 持久化状态对象=> 存到缓存中
		
		User u2 = (User) session.get(User.class, "1");//再次查询时,会从缓存中查找,不会发送select
		
		User u3 = (User) session.get(User.class, "1");//再次查询时,会从缓存中查找,不会发送select
		
		System.out.println(u1==u2);//true
		System.out.println(u1==u3);//true
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
	
	@Test
	//session缓存中的快照
	public void fun2(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		User u1 = (User) session.get(User.class, "1");// 发送select语句,从数据库取出记录.并封装成对象
		
		session.update(u1);
		
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
	
	
	@Test
	//session缓存中的快照
	public void fun3(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		User u1 = new User();
		u1.setId("1");
		u1.setName("rose");
		u1.setAge(20);
		session.update(u1);
		
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
	
	
	
	@Test
	//感受一级缓存效率的提高
	public void fun4(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		User u1 = (User) session.get(User.class, "1");
		
		u1.setName("tom");
		session.update(u1);
		u1.setName("jack");
		session.update(u1);
		u1.setName("rose");
		session.update(u1);
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
	// 持久化状态: 本质就是存在缓存中的对象,就是持久化状态.
	
	
	//1.保存对象时使用 save方法
	//  保存对象时使用 persist方法
	// 区别? 没有区别
	// persist(持久) 方法 来自于JPA 接口
	// save(保存) 方法来自于Hibernate
	/**
	 *  presist 不保证立即执行,可能要等到flush;不更新缓存;没有返回值
	 *  save 立即执行;返回主键;更新缓存
	 */
	@Test
	public void fun5(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		User u = new User();
		u.setName("李四");
		u.setId("5");
		session.save(u); //insert语句被打印=> 目的:获得id
		//session.persist(u); //
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
	
	//2.1 HQL查询是否会使用一级缓存? HQL不会使用一级缓存.
	@Test
	public void fun6(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		
		List<User> list1 = session.createQuery("from User").list();
		
		List<User> list2 = session.createQuery("from User").list();
		
		List<User> list3 = session.createQuery("from User").list();
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
	
	//2.2 HQL语句批量查询时,查询结果是否会进入缓存? 查询结果会放入缓存中
	@Test
	public void fun7(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		
		List<User> list1 = session.createQuery("from User").list();
		
		User u = (User) session.get(User.class, "1");
		System.out.println(u);
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
	
	@Test
	//3.1 SQL查询 结果会不会放入1级缓存中? 如果把查询结果封装到对象中,对象会放入一级缓存
	public void fun8(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		
		List<User> list1 = session.createSQLQuery("select * from t_user").addEntity(User.class).list();
		
		User u = (User) session.get(User.class, 1);
		
		System.out.println(u);
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
	@Test
	//3.2 SQL查询 结果会不会放入1级缓存中?没有把查询结果封装到对象中,对象不会放入一级缓存
	public void fun9(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		
		List list1 = session.createSQLQuery("select * from t_user").list();
		
		User u = (User) session.get(User.class, 1);
		
		System.out.println(u);
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
	
	//criteria => 会将查询结果放入一级缓存. 但是查询不会使用一级缓存. 与Hql查询结论一致.
}
