package com.tangjie.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tangjie.dao.ResourceDao;
import com.tangjie.model.Resource;

@Service("resourceService")
public class ResourceServiceImpl implements ResourceService{
	@Autowired
	private ResourceDao resourceDao;
	
	@Override
	public Resource add(Resource obj) {
		// TODO Auto-generated method stub
		return resourceDao.add(obj);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		resourceDao.delete(id);
	}

	@Override
	public Resource load(int id) {
		// TODO Auto-generated method stub
		return resourceDao.load(id);
	}

	@Override
	public void update(Resource obj) {
		// TODO Auto-generated method stub
		resourceDao.update(obj);
	}

	@Override
	public List<Resource> listResource() {
		// TODO Auto-generated method stub
		return resourceDao.listResource();
	}

}
