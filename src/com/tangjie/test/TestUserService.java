package com.tangjie.test;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.SessionHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.tangjie.model.User;
import com.tangjie.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring.xml")
public class TestUserService {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private UserService userService;
	
	@Before
	public void setUp(){
		Session s=sessionFactory.openSession();
		TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(s));
	}
	
	@After
	public void testdown(){
		SessionHolder holder=(SessionHolder)TransactionSynchronizationManager.getResource(sessionFactory);
		Session s=holder.getSession();
		s.flush();
		TransactionSynchronizationManager.unbindResource(sessionFactory);
	}
	@Test
	public void testAdd(){
		/*
		 * zhangsan 123
			admin 123
			tangjie 111
		 */
		User u=new User();
		u.setUsername("admin");
		u.setNickname("管理员");
		u.setPassword("123");
		u.setStatus(1);
		userService.add(u);
		
		u=new User();
		u.setUsername("zhangsan");
		u.setNickname("张三");
		u.setPassword("123");
		u.setStatus(1);
		userService.add(u);
		
		u=new User();
		u.setUsername("tangjie");
		u.setNickname("唐杰");
		u.setPassword("123");
		u.setStatus(1);
		userService.add(u);
	}
	
	@Test
	public void testRoleUser(){
		System.out.println("---------------->"+userService.listUserRole(1));
	}
	
	@Test
	public void testAddUserRole(){
		List<Integer> list=new ArrayList<Integer>();
		list.add(2);
		userService.addUserRoles(2,list);
	}
	
	@Test
	public void testUserRes(){
		System.out.println("------------------>"+userService.listAllResource(3));
	}
	
	@Test
	public void testUser(){
		System.out.println("------------------>"+userService.login("zhangsan", "123"));
	}
	@Test
	public void testService(){

	}
	
}
