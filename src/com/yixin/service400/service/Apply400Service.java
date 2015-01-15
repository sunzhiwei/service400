package com.yixin.service400.service;

import java.util.List;
import java.util.Map;

import com.yixin.service400.bean.TService400Applicationform;
import com.yixin.service400.bean.TService400User;

public interface Apply400Service {

	void addApply(TService400Applicationform ts) throws Exception;

	List<TService400Applicationform> getTService400ApplicationformList() throws Exception;

	TService400Applicationform getTService400ApplicationformById(Long id) throws Exception;

	void supApply(TService400Applicationform ts) throws Exception;

	void closeLoop(TService400Applicationform ts) throws Exception;

	List<TService400Applicationform> getHasApplyTService400ApplicationformList(TService400User currentUser,int count) throws Exception;

	@SuppressWarnings("unchecked")
	int getTotalCountsByParams(Map map) throws Exception;;
	
	@SuppressWarnings("unchecked")
	List<TService400Applicationform> queryPageByParams(Map map) throws Exception;
	
	int checkAdminByUser(TService400User currentUser) throws Exception;;
}
