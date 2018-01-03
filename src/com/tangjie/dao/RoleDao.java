package com.tangjie.dao;

import java.util.List;

import com.tangjie.model.Resource;
import com.tangjie.model.Role;
import com.tangjie.model.RoleResource;

public interface RoleDao extends BaseDao<Role>{
	
	public List<Role> listRole();			
	public List<Resource> listResourcesByRoleId(Integer roleId);
	public void deleteRoleResource(Integer roleId,Integer resourceId);
	public RoleResource loadResourceRole(Integer roleId,Integer resourceId);
	public void addRoleResource(Integer roleId,Integer resourceId);
	public void batchDeleteRoles(List<Integer> roleIds);
	public void DeleteUserRoleByRoleId(Integer roleIds);
	public void DeleteRoleResource(Integer roleIds);	
}
