package one_to_one;

import com.higanbana.domain.Address;
import com.higanbana.domain.Company;
import org.hibernate.Session;
import org.junit.Test;
import util.HibernateUtils;

/**
 * @author 陈明
 * @date 2020/3/25 14:10
 */
public class OneToOneTest
{
	/**
	 * 一对一主键关联
	 */
	@Test
	public void fun1()
	{
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		
		Company company = new Company();
		company.setName("极值");
		
		Address addr = new Address();
		addr.setName("上海浦东");
		
		
		addr.setCompany(company);
		
		session.save(company);
		session.save(addr);
		
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
	
	/**
	 * 查询
	 */
	@Test
	public void fun2()
	{
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		
		Company c = (Company) session.get(Company.class , 1);
		Address address = c.getAddress();
		System.out.println(c.getName());
		System.out.println(address.getName());
		
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
}
