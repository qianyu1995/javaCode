package com.angel.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

public class ShiroRealm implements Realm
{
	@Override
	public String getName()
	{
		return "Shiro-realm";
	}
	
	@Override
	public boolean supports(AuthenticationToken authenticationToken)
	{
		//仅支持UsernamePasswordToken类型的Token
		return authenticationToken instanceof UsernamePasswordToken;
	}
	
	@Override
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException
	{
		//1.获取用户名
		String username = (String) authenticationToken.getPrincipal();
		//2.获取密码
		String password = new String((char[]) authenticationToken.getCredentials());
		if ( !"zhang".equals(username) )
		{
			throw new UnknownAccountException();
		}
		if ( !"123".equals(password) )
		{
			throw new IncorrectCredentialsException();
		}
		//如果身份认证验证成功，返回一个AuthenticationInfo实现；
		return new SimpleAuthenticationInfo(username , password , getName());
	}
}
