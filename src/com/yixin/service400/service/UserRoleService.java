package com.yixin.service400.service;

import java.util.List;

import com.yixin.service400.base.BaseDao;
import com.yixin.service400.bean.TService400UserRole;

public interface UserRoleService extends BaseDao<TService400UserRole>{

	void deleteByUserid(Long userid) throws Exception;
	
    List<Long> userroleidlist(Long userid) throws Exception;
    
}
