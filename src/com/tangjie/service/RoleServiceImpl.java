package com.tangjie.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tangjie.dao.RoleDao;
import com.tangjie.model.Resource;
import com.tangjie.model.Role;
import com.tangjie.model.RoleResource;
import com.tangjie.model.UserRole;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;

	@Override
	public Role add(Role role) {
		// TODO Auto-generated method stub
		return roleDao.add(role);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		roleDao.delete(id);
	}

	@Override
	public Role load(int id) {
		// TODO Auto-generated method stub
		return roleDao.load(id);
	}

	@Override
	public void update(Role role) {
		// TODO Auto-generated method stub
		roleDao.update(role);
	}

	@Override
	public List<Role> listRole() {
		// TODO Auto-generated method stub
		return roleDao.listRole();
	}


	@Override
	public List<Resource> listRoleResource(int roleId) {
		// TODO Auto-generated method stub
		return roleDao.listResourcesByRoleId(roleId);
	}

	@Override
	public void addRoleResource(int roleId, int resId) {
		// TODO Auto-generated method stub
		roleDao.addRoleResource(roleId, resId);
	}

	@Override
	public void deleteRoleResource(int roleId, int resId) {
		// TODO Auto-generated method stub
		roleDao.deleteRoleResource(roleId, resId);
	}

	@Override
	public RoleResource loadResourceRole(int roleId, int resId) {
		// TODO Auto-generated method stub
		return roleDao.loadResourceRole(roleId, resId);
	}

	@Override
	public void deleteRoleAndRoleResourceAndUserRoleByRoleIds(List<Integer> roleIds) {
		// TODO Auto-generated method stub
		roleDao.batchDeleteRoles(roleIds);
		for(Integer roleId:roleIds) {
			roleDao.DeleteRoleResource(roleId);
			roleDao.DeleteUserRoleByRoleId(roleId);			
		}
	}
}
