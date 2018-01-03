package com.tangjie.test;

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

import com.tangjie.model.Role;
import com.tangjie.model.User;
import com.tangjie.service.RoleService;
import com.tangjie.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring.xml")
public class TestRoleService {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private RoleService roleService;
	
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
		/**
		 * ADMIN
			EMP
			TEST
		 */
		Role r=new Role();
		r.setName("管理员");
		r.setSn("ADMIN");
		roleService.add(r);
		r=new Role();
		r.setName("普通员工");
		r.setSn("EMP");
		roleService.add(r);
		r=new Role();
		r.setName("测试人员");
		r.setSn("TEST");
		roleService.add(r);
	}
	
	
	
	@Test
	public void testAddRoleRes(){
		
		roleService.addRoleResource(2, 2);
		roleService.addRoleResource(2, 3);
		roleService.addRoleResource(2, 4);
	}
	
	@Test
	public void testRoleload(){
		System.out.println(roleService.load(1));
	}

}
