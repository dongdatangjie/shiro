package com.tangjie.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tangjie.model.User;
import com.tangjie.model.UserRole;
import com.tangjie.model.Resource;
import com.tangjie.model.Role;

public interface UserDao extends BaseDao<User>{
	public User loadByUsername(String username);
	public List<User> listUser();
	public List<Role> listRoleByUserId(Integer userId);
	public void addUserRole(Integer userId,Integer roleId);
	public UserRole loadUserRole(Integer userId,Integer roleId);
	public void updateStatus(User user);		
	public void deleteUserRoleByUserId(Integer userId);
	public List<Resource> listResourcesByUserId(Integer userId);
	public void batchDeleteUser(List<Integer> userIds);
	public void DeleteUserRoleByUserId(Integer userId);
	public List<String> listSnByUser(int uid); 

}
