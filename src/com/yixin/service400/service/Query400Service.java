package com.yixin.service400.service;

import java.util.List;
import java.util.Map;

import com.yixin.service400.bean.TService400File;

public interface Query400Service {

	List<TService400File> getClosable(String time1, String time2) throws Exception;

	TService400File closeLoop(Long id) throws Exception;

	List<TService400File> getHisNotes(String time1, String time2, String time3, String time4) throws Exception;
    
	@SuppressWarnings("unchecked")
	int getTotalCountsByParams(Map map) throws Exception;
	
	@SuppressWarnings("unchecked")
	List<TService400File> queryPageByParams(Map map) throws Exception;
	
    @SuppressWarnings("unchecked")
	int getCLTotalCountsByParams(Map map) throws Exception;
	
	@SuppressWarnings("unchecked")
	List<TService400File> queryCLPageByParams(Map map) throws Exception;

	TService400File getTSfByID(Long id) throws Exception;
}
