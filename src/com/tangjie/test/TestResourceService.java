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

import com.tangjie.model.Resource;
import com.tangjie.model.Role;
import com.tangjie.model.User;
import com.tangjie.service.ResourceService;
import com.tangjie.service.RoleService;
import com.tangjie.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring.xml")
public class TestResourceService {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private ResourceService resourceService;
	
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
		
		Resource res=new Resource();
		res.setName("系统管理");
		res.setUrl("/admin/*");
		resourceService.add(res);
		
		res=new Resource();
		res.setName("用户管理");
		res.setUrl("/admin/user/*");
		resourceService.add(res);
		
		res=new Resource();
		res.setName("用户添加");
		res.setUrl("/admin/user/add");
		resourceService.add(res);
		
		res=new Resource();
		res.setName("用户删除");
		res.setUrl("/admin/user/delete");
		resourceService.add(res);
		
		res=new Resource();
		res.setName("角色管理");
		res.setUrl("/admin/role/*");
		resourceService.add(res);
		
		res=new Resource();
		res.setName("角色添加");
		res.setUrl("/admin/role/add");
		resourceService.add(res);
		
		res=new Resource();
		res.setName("角色修改");
		res.setUrl("/admin/role/update");
		resourceService.add(res);
	}
	@Test
	public void testListResource() {
		System.out.println(resourceService.listResource());
	}
	
}
