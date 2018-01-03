package com.tangjie.service;

import java.util.List;

import com.tangjie.dao.UserDao;
import com.tangjie.model.Resource;
import com.tangjie.model.Role;
import com.tangjie.model.User;

public interface UserService {
	
	public User add(User user);
	
	public void add(User user,List<Integer> rids);
	
	public void delete(int id);
	
	public User load(int id);
	
	public void update(User user);
	/**
	 * 登录
	 */
	public User login(String username,String password);
	/**
	 * 获取所有用户
	 * @return
	 */
	public List<User> listUser();
	/**
	 * 按照用户名查找用户
	 * @return
	 */	
	public User loadByUsername(String username);
	/**
	 * 查找用户的所有可访问资源
	 * @param uid
	 * @return
	 */
	public List<Resource> listAllResource(int uid);
	
	public UserDao getUserDao();
	/**
	 * 根据用户id获取角色信息	
	 * @return
	 */
	public List<Role> listUserRole(int uid);
	
	 /**
     * // TODO: 2016/9/18   应该设置为一个事务
     * 更新用户数据
     * 1、更新用户基本信息
     * 2、更新用户所属角色
     *    （1）先删除所有的角色
     *    （2）再添加绑定的角色
     * @param user
     * @param rids
     */
	public User update(User user,List<Integer> rids);
	public void addUserRoles(int userId, List<Integer> roleIds); 
	 public void deleteUserAndRole(List<Integer> ids) ;

	public void updateStatus(User user);

	public List<String> listSnByUser(int uid);

}
