package com.shenhua.powermock.mockito.demo.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.shenhua.powermock.mockito.demo.entity.UserRule;
import com.shenhua.powermock.mockito.demo.entity.User;
import com.shenhua.powermock.mockito.demo.service.UserService;
import com.shenhua.powermock.mockito.demo.utils.IdGenerateUtils;

@Service
public class UserServiceImpl implements UserService{

	@Override
	public User addUser(Map<String, Object> map) {
		User user = new User();
		user.setUserName((String) map.get("userName"));
		user.setAge((int) map.get("age"));
		user.setSex((byte) map.get("sex"));
		user.setUserId(IdGenerateUtils.nextId());
		UserRule rule = new UserRule();
		user.setRule(rule);
		if(addUser1(user)) return user;
		else return null;
	}

	private boolean addUser1(User user) {
		return true;
	}
}
