package com.tangjie.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tangjie.model.Resource;
import com.tangjie.model.Role;
import com.tangjie.model.User;
import com.tangjie.model.UserRole;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao{

	@Override
	public User loadByUsername(String username) {
		// TODO Auto-generated method stub		
		String hql="from User where username=?";
		return (User)super.queryObject(hql, username);
	}

	@Override
	public List<User> listUser() {
		// TODO Auto-generated method stub
		return super.list("from User");
	}

	@Override
	public void addUserRole(Integer userId, Integer roleId) {
		// TODO Auto-generated method stub
		UserRole ur=null;
		ur=loadUserRole(userId, roleId);
		if(ur==null){
			ur=new UserRole();
			ur.setUserId(userId);
			ur.setRoleId(roleId);
			this.getSession().save(ur);
		}
	}

	@Override
	public void updateStatus(User user) {
		// TODO Auto-generated method stub
		String hql="update User u set u.status=? where u.id=?";
		super.updateByHql(hql, new Object[]{user.getStatus(),user.getId()});
	}

	@Override
	public void deleteUserRoleByUserId(Integer userId) {
		// TODO Auto-generated method stub
		String hql="delete from UserRole ur where ur.userId=?";
		super.updateByHql(hql, new Object[]{userId});;
	}

	@Override
	public List<Resource> listResourcesByUserId(Integer userId) {
		// TODO Auto-generated method stub
		String hql="select res from "
				+ "User u,Resource res,UserRole ur,RoleResource rr "
				+ "where "
				+ "ur.userId=u.id and ur.roleId=rr.roleId and rr.resId=res.id and u.id=?";
		return super.listobj(hql, new Object[]{userId});
	}

	@Override
	public void batchDeleteUser(List<Integer> userIds) {
		// TODO Auto-generated method stub
		String hql="delete from User u where u.id in (:ids)";
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("ids", userIds);
		this.updateByHqlAlias(hql,map);
	}

	@Override
	public void DeleteUserRoleByUserId(Integer userId) {
		// TODO Auto-generated method stub
		String hql="delete from UserRole ur where ur.userId=?";
		this.updateByHql(hql, userId);
	}

	@Override
	public UserRole loadUserRole(Integer userId, Integer roleId) {
		// TODO Auto-generated method stub
		String hql="select ur from UserRole ur where ur.userId=? and ur.roleId=?";
		return (UserRole)super.queryObject(hql,new Object[]{userId,roleId});	
	}

	@Override
	public List<Role> listRoleByUserId(Integer userId) {
		// TODO Auto-generated method stub
		String hql="select r from UserRole ur,Role r,User u where u.id=ur.userId and ur.roleId=r.id and u.id=?";
		return super.listobj(hql, new Object[]{userId});
	}

	@Override
	public List<String> listSnByUser(int uid) {
		// TODO Auto-generated method stub
		String hql="select r.sn from UserRole ur,Role r,User u where u.id=ur.userId and ur.roleId=r.id and u.id=?";
		return super.listobj(hql, new Object[]{uid});
	
	}

}
