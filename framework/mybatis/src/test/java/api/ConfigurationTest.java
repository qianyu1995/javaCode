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

/**
 * @author 陈明
 * @date 2020/3/25 17:26
 */
public class ConfigurationTest
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
	
	
	
	
	//读取配置文件
	@Test
	public void fun1() throws IOException
	{
		//读取配置文件
		//全局配置文件的路径
		
		
		//调用SqlSession的增删改查方法
		//第一个参数：表示statement的唯一标示
		User user = new User();
		user.setUsername("angel");
		sqlSession.insert("com.higanbana.dao.UserDao.insertUser", user);
		System.out.println(user);
		//关闭资源
		sqlSession.close();
	}
	
	
	//读取配置文件 mapper
	@Test
	public void fun2() throws Exception
	{
		//第一个参数：表示statement的唯一标示
		User user = new User();
		user.setUsername("angel");
		UserDao userDao = sqlSession.getMapper(UserDao.class);
		userDao.insertUser(user);
		System.out.println(user.getId());
		//关闭资源
		sqlSession.close();
	}
	
	
	
}
