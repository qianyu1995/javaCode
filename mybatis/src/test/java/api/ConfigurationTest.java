package api;

import com.higanbana.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author 陈明
 * @date 2020/3/25 17:26
 */
public class ConfigurationTest
{
	//读取配置文件
	@Test
	public void fun1() throws IOException
	{
		//读取配置文件
		//全局配置文件的路径
		String resource = "mybatis.cfg.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		
		//创建SqlSessionFactory
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		//创建SqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		//调用SqlSession的增删改查方法
		//第一个参数：表示statement的唯一标示
		User user = new User();
		user.setUsername("angel");
		sqlSession.insert("com.higanbana.dao.UserDao.insertUser", user);
		System.out.println(user);
		
		//关闭资源
		sqlSession.close();
	}
}
