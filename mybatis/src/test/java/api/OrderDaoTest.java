package api;

import com.higanbana.dao.OrdersDao;
import com.higanbana.domain.OrdersExt;
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
 * @date 2020/3/27 14:41
 */
public class OrderDaoTest
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
	
	@Test
	public void testFindOrdersAndUser() {
		
		
		OrdersDao mapper = sqlSession.getMapper(OrdersDao.class);
		
		List<OrdersExt> list = mapper.findOrdersAndUser();
		System.out.println(list);
		sqlSession.close();
	}
}
