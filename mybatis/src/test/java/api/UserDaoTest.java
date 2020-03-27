package api;

import com.higanbana.dao.ProductDao;
import com.higanbana.dao.UserDao;
import com.higanbana.domain.Product;
import com.higanbana.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class UserDaoTest
{
	private static SqlSession sqlSession;
	
	static
	{
		String resource = "mybatis.cfg.xml";
		InputStream inputStream = null;
		try
		{
			inputStream = Resources.getResourceAsStream(resource);
			//创建SqlSessionFactory
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			
			//创建SqlSession 自动提交事务
			sqlSession = sqlSessionFactory.openSession(Boolean.TRUE);
		} catch ( IOException e )
		{
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 一级缓存
	 */
	@Test
	public void fun3() throws Exception
	{
		UserDao userDao = sqlSession.getMapper(UserDao.class);
		User user = userDao.findUserById(1);
		User user2 = userDao.findUserById(1);
		System.out.println(user == user2);
		sqlSession.close();
	}
	
	/**
	 * 二级缓存
	 */
	@Test
	public void fun4() throws Exception
	{
		//读取配置文件
		//全局配置文件的路径
		String resource = "mybatis.cfg.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		
		//创建SqlSessionFactory
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		//创建SqlSession
		SqlSession sqlSession1 = sqlSessionFactory.openSession();
		SqlSession sqlSession2 = sqlSessionFactory.openSession();
		UserDao userDao1 = sqlSession1.getMapper(UserDao.class);
		UserDao userDao2 = sqlSession2.getMapper(UserDao.class);
		User user1 = userDao1.findUserById(1);
		System.out.println(user1);
		sqlSession1.close();
		User user2 = userDao2.findUserById(1);
		System.out.println(user2);
		sqlSession2.close();
	}
	
	/**
	 * 测试一对一以及自动映射
	 */
	@Test
	public void fun5()
	{
		ProductDao productDao = sqlSession.getMapper(ProductDao.class);
		List<Product> value = productDao.getProductDetail();
		for ( Product product : value )
		{
			System.out.println(product.toString());
		}
	}
	
	/**
	 * 	模糊查询
	 *	like "%" #{name} "%"
	 *	like concat("%",#{name},"%")
	 *
	 *
	 * 	如果传入的参数是简单数据类型，${}里面必须写value
	 *	 like "%${value}%"
	 *
	 *
	 */
	@Test
	public  void  fun6() throws Exception
	{
		UserDao userDao = sqlSession.getMapper(UserDao.class);
		List<User> value  = userDao.findUsersByName("test");
		for ( User user : value )
		{
			System.out.println(user);
		}
	}
	
	
}
