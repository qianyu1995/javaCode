package com.higanbana.domain;
import lombok.Data;

/**
 * @author 陈明
 * @date 2020/1/7 14:52
 */
@Data
public class User
{
	private String id;
	
	private String name;
	
	private Integer age;
	
	private String password;
}
