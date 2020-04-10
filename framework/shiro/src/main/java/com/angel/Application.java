package com.angel;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author 陈明
 * @date 2020/4/1 15:07
 */

public class Application
{
	private static Logger logger = LoggerFactory.getLogger(Application.class);
	
	public static void main(String[] args)
	{
		//1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		//2、得到SecurityManager实例 并绑定给SecurityUtils
		SecurityUtils.setSecurityManager(factory.getInstance());
		//3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("zhang","123");
		try
		{
			//4、登录,即身份验证
			subject.login(usernamePasswordToken);
			
			//用户是否是RunAs用户
			if ( !subject.isRunAs() )
			{
				subject.runAs(new SimplePrincipalCollection("wang", ""));
				System.out.println(subject.hasRole("role2"));
				System.out.println(subject.getPrincipals());
			}
			
			
			
			
			
			
			/*//判断拥有角色：role1
			System.out.println(subject.hasRole("role1"));;
			System.out.println(subject.hasRole("role2"));
			//System.out.println(subject.hasRoles(Arrays.asList("role1","role2","role3")));
			
			//判断用户权限
			System.out.println(subject.isPermitted("user:create"));
			System.out.println(subject.isPermitted("user:update"));*/
		}catch ( Exception e )
		{
			//5、身份验证失败
			e.printStackTrace();
		}
		//6、退出
		subject.logout();
		subject.logout();
	}
}
