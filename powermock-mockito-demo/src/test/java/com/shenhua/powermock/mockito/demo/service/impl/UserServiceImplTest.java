package com.shenhua.powermock.mockito.demo.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.shenhua.powermock.mockito.demo.PowermockDemoApplication;
import com.shenhua.powermock.mockito.demo.entity.User;
import com.shenhua.powermock.mockito.demo.entity.UserRule;
import com.shenhua.powermock.mockito.demo.exception.BusinessException;
import com.shenhua.powermock.mockito.demo.service.UserService;
import com.shenhua.powermock.mockito.demo.utils.IdGenerateUtils;


@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringRunner.class)
@PrepareForTest({IdGenerateUtils.class, UserServiceImpl.class})
@SpringBootTest(classes=PowermockDemoApplication.class)
public class UserServiceImplTest {
	
	@Autowired
	private UserService userService;
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Test
	public void testStatic() {
		long userId = 1l;
		String userName = "张三";
		int age = 18;
		byte sex = 1;
		Map<String, Object> map = new HashMap<>();
		map.put("userName", userName);
		map.put("age", age);
		map.put("sex", sex);
		
		PowerMockito.mockStatic(IdGenerateUtils.class);
		PowerMockito.when(IdGenerateUtils.nextId()).thenReturn(userId);
		
		User user = userService.addUser(map);
		Assert.assertEquals(userId, user.getUserId());
		Assert.assertEquals(userName, user.getUserName());
		Assert.assertEquals(age, user.getAge());
		Assert.assertEquals(sex, user.getSex());
		PowerMockito.verifyStatic(IdGenerateUtils.class);
		
	}
	
	@Test
	public void testPrivate() throws Exception {

		String userName = "张三";
		int age = 18;
		byte sex = 1;
		Map<String, Object> map = new HashMap<>();
		map.put("userName", userName);
		map.put("age", age);
		map.put("sex", sex);
		
		UserService userServiceSpy = PowerMockito.spy(userService);
		
		PowerMockito.doReturn(false).when(userServiceSpy, "addUser1", Mockito.any(User.class));
		
		User user = userServiceSpy.addUser(map);
		Assert.assertNull(user);
		PowerMockito.verifyPrivate(userServiceSpy, Mockito.times(1)).invoke("addUser1", Mockito.any(User.class));
		
	}
	
	@Test
	public void testWhenNew() throws Exception {
		String userName = "张三";
		int age = 18;
		byte sex = 1;
		Map<String, Object> map = new HashMap<>();
		map.put("userName", userName);
		map.put("age", age);
		map.put("sex", sex);
		
		User expectUser = new User();
		expectUser.setUserName(userName);
		expectUser.setAge(age);
		expectUser.setSex(sex);
		UserRule expectRule = new UserRule();
		expectRule.setRuleNo("10010");
		expectUser.setRule(expectRule);
		PowerMockito.whenNew(UserRule.class).withNoArguments().thenReturn(expectRule);
		
		User user = userService.addUser(map);
		Assert.assertNotNull(user.getUserId());
		Assert.assertEquals(expectUser.getUserName(), user.getUserName());
		Assert.assertEquals(expectUser.getAge(), user.getAge());
		Assert.assertEquals(expectUser.getSex(), user.getSex());
		Assert.assertEquals(expectUser.getRule().getRuleNo(), user.getRule().getRuleNo());
		PowerMockito.verifyNew(UserRule.class).withNoArguments();
		
	}
	
	@Test
	public void testExeception() {
		
		String userName = "张三";
		int age = 18;
		byte sex = 1;
		Map<String, Object> map = new HashMap<>();
		map.put("userName", userName);
		map.put("age", age);
		map.put("sex", sex);
		
		PowerMockito.mockStatic(IdGenerateUtils.class);
		PowerMockito.when(IdGenerateUtils.nextId()).thenThrow(new BusinessException("generate error"));
		
		expectedException.expect(BusinessException.class);
		expectedException.expectMessage("generate");
		userService.addUser(map);
		PowerMockito.verifyStatic(IdGenerateUtils.class);
	}

}
