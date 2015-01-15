package com.yixin.service400.service;

import java.util.List;

import com.yixin.service400.bean.TService400Applicationform;
import com.yixin.service400.bean.TService400Key;
import com.yixin.service400.bean.TService400KeyWorkgroup;
import com.yixin.service400.bean.TService400User;
import com.yixin.service400.bean.TService400Workgroup;

public interface IVR400Service {

	List<TService400Key> getTService400KeyListByPath(String path) throws Exception;

	void addTService400Key(TService400Key key) throws Exception;

	String getNoHasName(Long id) throws Exception;

	List<TService400Workgroup> getWorkGroupByIVRid(Long pid) throws Exception;

	TService400Key getTService400Key(Long pid) throws Exception;

	void updateIVR400Service(TService400Key key) throws Exception;

	void deleteTService400Key(Long id) throws Exception;

	boolean isHasGroup(Long id) throws Exception;

	void addKeyGroup(Long id, TService400Workgroup groupid,TService400User currentUser) throws Exception;

	void sendIVRMail() throws Exception;
	
	List<TService400Key> getTService400Key(TService400User user) throws Exception;
	
	List<TService400Key> getTService400KeyByCondition(TService400User user,String phonenum) throws Exception;
	
	List<TService400KeyWorkgroup> getTService400KeyWorkgroup(TService400User user) throws Exception;
	
	List<TService400KeyWorkgroup> getTService400KeyWorkgroupByCon(TService400User user,String phonenum) throws Exception;
	
    void updateStatusByIds(String ids,String date) throws Exception;
	
	void updateStatusByDeleteIds(String deleteids,String date) throws Exception;

	void updateCloseLoop(String phonenum) throws Exception;
	
	List<TService400Applicationform> getApplicationformByNum(String phonenum)throws Exception;
}
