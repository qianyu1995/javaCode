package higanbana.manytomany;

import com.higanbana.domain.Course;
import com.higanbana.domain.Student;
import com.higanbana.util.HibernateUtils;
import org.hibernate.Session;
import org.junit.Test;

/**
 * @author 陈明
 * @date 2020/3/24 16:28
 */
public class ManyToMany
{
	@Test
	//保存学生 => 通过学生保存课程. 由学生维护外键
	//Student 的
	//inverse =  false
	//cascade =  save-update
	//Course
	//inverse = true
	public void fun1(){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//------------------------------------------------
		Student stu1 = new Student();
		stu1.setName("tom");
		
		Student stu2 = new Student();
		stu2.setName("jerry");
		
		Course c1 = new Course();
		c1.setName("Struts2");
		
		Course c2 = new Course();
		c2.setName("Hibernate");
		
		Course c3 = new Course();
		c3.setName("Spring");
		
		stu1.getCourses().add(c1); //维护关系,级联保存
		stu1.getCourses().add(c2);
		stu1.getCourses().add(c3);
		
		stu2.getCourses().add(c1);
		stu2.getCourses().add(c2);
		stu2.getCourses().add(c3);
		
		session.save(stu1);
		session.save(stu2);
		//------------------------------------------------
		session.getTransaction().commit();
		session.close(); // 游离状态
	}
}
