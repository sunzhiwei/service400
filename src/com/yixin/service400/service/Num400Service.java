package com.yixin.service400.service;

import java.util.List;
import java.util.Map;

import com.yixin.service400.bean.TService400;

public interface Num400Service {

	List<TService400> get400List(TService400 ts4) throws Exception;

	void addTService400(TService400 ts) throws Exception;

	TService400 getTService400(long parseLong) throws Exception;

	void update400(TService400 ts) throws Exception;

	void delete400(long parseLong) throws Exception;

	void setApply(long service400Id) throws Exception;
	
	@SuppressWarnings("unchecked")
	int getTotalCountsByParams(Map map) throws Exception;
	
	@SuppressWarnings("unchecked")
	List<TService400> queryPageByParams(Map map) throws Exception;
	
	int getRepeatCounts(String phonenum) throws Exception;

}
