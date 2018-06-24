package com.shenhua.powermock.mockito.demo.entity;

public class User {
	
	public long userId;
	
	public String userName;
	
	public int age;
	
	public byte sex;
	
	public UserRule rule;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public byte getSex() {
		return sex;
	}

	public void setSex(byte sex) {
		this.sex = sex;
	}

	public UserRule getRule() {
		return rule;
	}

	public void setRule(UserRule rule) {
		this.rule = rule;
	}
	

}
