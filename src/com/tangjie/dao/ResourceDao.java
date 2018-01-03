package com.tangjie.dao;

import com.tangjie.model.Resource;
import java.util.List;

public interface ResourceDao extends BaseDao<Resource>{
	public List<Resource> listResource();			
}
