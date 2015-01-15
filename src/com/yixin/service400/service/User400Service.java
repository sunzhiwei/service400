package com.yixin.service400.service;

import java.util.List;
import java.util.Map;

import com.yixin.service400.base.BaseDao;
import com.yixin.service400.bean.TService400Applicationform;
import com.yixin.service400.bean.TService400Role;
import com.yixin.service400.bean.TService400User;


public interface User400Service extends BaseDao<TService400User>{

	void addUser(TService400User ts) throws Exception;
	
	@SuppressWarnings("unchecked")
	int getTotalCountsByParams(Map map) throws Exception;
	
	@SuppressWarnings("unchecked")
	List<TService400User>  queryPageByParams(Map map) throws Exception;
	
	List<TService400Role>  selectRoleList() throws Exception;
	
	List<TService400Applicationform> selectApplicationformList() throws Exception;
	
	int checkUserExist(String username,Long userid) throws Exception;
	
	List<Long> user400numserviceidByUserid(Long userid) throws Exception;
	
	int checkPassword(TService400User currentUser,String password) throws Exception;
}
