package com.tangjie.service;

import java.util.List;

import com.tangjie.model.Resource;
import com.tangjie.model.Role;
import com.tangjie.model.RoleResource;
import com.tangjie.model.UserRole;

public interface RoleService {
	public Role add(Role role);
	public void delete(int id);
	public Role load(int id);
	public void update(Role role);
	/**
	 * 获取所有角色
	 * @return
	 */
	public List<Role> listRole();
	/**
	 * 根据角色id获取可以访问的资源对象
	 * @param roleId
	 * @return
	 */
	public List<Resource> listRoleResource(int roleId);
	/**
	 * 添加角色资源信息
	 * @param roleId
	 * @param resId
	 */
	public void addRoleResource(int roleId,int resId);
	
	public void deleteRoleResource(int roleId,int resId);
	/**
	 * 获取角色资源信息
	 * @param roleId
	 * @param resId
	 * @return
	 */
	public RoleResource loadResourceRole(int roleId,int resId);
   
	public void deleteRoleAndRoleResourceAndUserRoleByRoleIds(List<Integer> roleIds);
}
