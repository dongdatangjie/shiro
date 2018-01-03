package com.tangjie.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tangjie.model.Resource;

@Repository("resourceDao")
public class ResourceDaoImpl extends BaseDaoImpl<Resource> implements ResourceDao{

	@Override
	public List<Resource> listResource() {
		// TODO Auto-generated method stub
		return super.list("from Resource");
	}
}
