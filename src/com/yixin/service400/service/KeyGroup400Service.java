package com.yixin.service400.service;

import com.yixin.service400.bean.TService400User;



public interface KeyGroup400Service {

	void deleteGroup(Long groupid, Long id,TService400User currentUser) throws Exception;
	
	void updateStatusByDeleteIds(String deleteids,String date) throws Exception;
	
	void updateStatusByIds(String ids,String date) throws Exception;
}
