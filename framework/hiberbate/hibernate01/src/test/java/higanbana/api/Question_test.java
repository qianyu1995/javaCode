package higanbana.api;


import com.higanbana.domain.User;
import com.higanbana.util.HibernateUtils;
import org.hibernate.Session;
import org.junit.Test;

import java.util.List;

/**
 * @author 陈明
 * @date 2019/11/27 10:06
 */
//学生问题
//2 Hql查询,查询结果会放入Session一级缓存中.但是每次调用Hql查询都会生成Sql语句?
// 并不代表 Hql没有使用1级缓存.
public class Question_test
{
	@Test
	public void fun1(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		List<User> list1 = session.createQuery("from User").list(); // 发送sql
		
		List<User> list2 = session.createQuery("from User").list();// 发送sql
		
		System.out.println(list1.get(0)==list2.get(0));//true =>
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
	
	//问题: 缓存中的数据如果与数据库中的不同步,会怎么样?
	// 会优先使用缓存中的. 使用JDBC
	// 在一级缓存中出现该问题的几率比较小.
	//openSession==> 一级缓存生命周期开始
	//session.close();=> 一级缓存销毁
	@Test
	public void fun2(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		
		User u1 = (User) session.get(User.class, "1");
		
		User u2 = (User) session.get(User.class, "1");
		
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
}
