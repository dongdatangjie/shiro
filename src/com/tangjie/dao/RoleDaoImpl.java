package com.tangjie.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tangjie.model.Resource;
import com.tangjie.model.Role;
import com.tangjie.model.RoleResource;

@Repository("roleDao")
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao{

	@Override
	public List<Role> listRole() {
		// TODO Auto-generated method stub
		return super.list("from Role");
	}

	@Override
	public List<Resource> listResourcesByRoleId(Integer roleId) {
		// TODO Auto-generated method stub
		String hql="select res from "
				+ "Role r,Resource res,RoleResource rr "
				+ "where rr.roleId=r.id "
				+ "and rr.resId=res.id "
				+ "and r.id=?";
		return super.listobj(hql, new Object[]{roleId});
	}

	@Override
	public void deleteRoleResource(Integer roleId, Integer resourceId) {
		// TODO Auto-generated method stub
		RoleResource rr=null;
		rr=loadResourceRole(roleId, resourceId);
		if(rr!=null){
			this.getSession().delete(rr);
		}
	}

	@Override
	public void addRoleResource(Integer roleId, Integer resourceId) {
		// TODO Auto-generated method stub
		RoleResource rr=null;
		rr=loadResourceRole(roleId, resourceId);
		if(rr==null){
			rr=new RoleResource();
			rr.setResId(resourceId);
			rr.setRoleId(roleId);
			this.getSession().save(rr);
		}
	}

	@Override
	public void batchDeleteRoles(List<Integer> roleIds) {
		// TODO Auto-generated method stub
		String hql="delete from Role r where r.id in (:ids)";
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("ids", roleIds);
		this.updateByHqlAlias(hql,map);
	}

	@Override
	public void DeleteUserRoleByRoleId(Integer roleId) {
		// TODO Auto-generated method stub
		String hql="delete from UserRole ur where ur.roleId=?";
		this.updateByHql(hql,roleId);
	}

	@Override
	public void DeleteRoleResource(Integer roleId) {
		// TODO Auto-generated method stub
		String hql="delete from RoleResource rr where rr.roleId=?";
		this.updateByHql(hql,roleId);
	}

	@Override
	public RoleResource loadResourceRole(Integer roleId, Integer resourceId) {
		// TODO Auto-generated method stub
		String hql="select rr from RoleResource rr "
				+ "where rr.roleId=? and rr.resId=?";
		return (RoleResource)super.queryObject(hql, new Object[]{roleId,resourceId});
	}
		
}
