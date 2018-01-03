package com.tangjie.service;

import java.util.List;

import com.tangjie.model.Resource;

public interface ResourceService {
	public Resource add(Resource obj);
	public void delete(int id);
	public Resource load(int id);
	public void update(Resource obj);
	/**
	 * 获取所有资源
	 * @return
	 */
	public List<Resource> listResource();
}
