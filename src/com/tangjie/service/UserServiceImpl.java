package com.tangjie.service;

import java.util.List;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tangjie.dao.RoleDao;
import com.tangjie.dao.UserDao;
import com.tangjie.model.Resource;
import com.tangjie.model.Role;
import com.tangjie.model.User;
import com.tangjie.util.ShiroKit;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;

	@Override
	public User add(User user) {
		// TODO Auto-generated method stub
		if (ShiroKit.isEmpty(user.getUsername())
				|| ShiroKit.isEmpty(user.getPassword())) {
			throw new RuntimeException("用户名或者密码不能为空");
		}
		user.setPassword(ShiroKit.md5(user.getPassword(), user.getUsername()));
		return userDao.add(user);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		userDao.delete(id);
	}

	@Override
	public User load(int id) {
		// TODO Auto-generated method stub
		return userDao.load(id);
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		userDao.update(user);
	}

	@Override
	public User login(String username, String password) {
		// TODO Auto-generated method stub
		System.out.println(userDao);
		User u = userDao.loadByUsername(username);
		if (u == null)
			throw new UnknownAccountException("用户名或者密码出错");
		if (!u.getPassword().equals(ShiroKit.md5(password, username))) {
			throw new IncorrectCredentialsException("用户名或者密码出错");
		}
		if (u.getStatus() == 0) {
			throw new LockedAccountException("用户账号已经停用");
		}
		return u;
	}

	@Override
	public List<User> listUser() {
		// TODO Auto-generated method stub
		return userDao.listUser();
	}

	@Override
	public User loadByUsername(String username) {
		// TODO Auto-generated method stub
		return userDao.loadByUsername(username);
	}

	

	@Override
	public List<Resource> listAllResource(int uid) {
		// TODO Auto-generated method stub
		return userDao.listResourcesByUserId(uid);
	}

	@Override
	public void add(User user, List<Integer> rids) {
		// TODO Auto-generated method stub
		this.add(user);
		for (int rid : rids) {
			userDao.addUserRole(user.getId(), rid);
		}
	}

	@Override
	public UserDao getUserDao() {
		// TODO Auto-generated method stub
		return userDao;
	}

	@Override
	public List<Role> listUserRole(int uid) {
		// TODO Auto-generated method stub
		return userDao.listRoleByUserId(uid);
	}

	/**
	 * 
	 * 更新用户数据 1、更新用户基本信息 2、更新用户所属角色 （1）先删除所有的角色 （2）再添加绑定的角色
	 * 
	 * @param user
	 * @param rids
	 */
	@Override
	public User update(User user, List<Integer> rids) {
		Integer userId = user.getId();
		userDao.deleteUserRoleByUserId(userId);
		for(Integer roleId:rids) {
			userDao.addUserRole(userId, roleId);			
		}
		this.update(user);
		return user;
	}

	@Override
	public void addUserRoles(int userId, List<Integer> roleIds) {
		// TODO Auto-generated method stub
		for(Integer roleId:roleIds) {
			userDao.addUserRole(userId, roleId);			
		}
	}

	@Override
	public void deleteUserAndRole(List<Integer> ids) {
		if (ids.contains(1)) {
			throw new RuntimeException("不能删除管理员用户");
		}
		// 删除用户列表
		userDao.batchDeleteUser(ids);
		// 依次删除这些用户所绑定的角色 这里我们考虑用户如果没有角色，所以不能用batch
		for(Integer userId:ids) {
			userDao.DeleteUserRoleByUserId(userId);			
		}
	}

	@Override
	public void updateStatus(User user) {
		// TODO Auto-generated method stub
		userDao.updateStatus(user);
	}

	@Override
	public List<String> listSnByUser(int uid) {
		// TODO Auto-generated method stub
		return userDao.listSnByUser(uid);
	}

}
