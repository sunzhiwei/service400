package com.yixin.service400.service;

import java.util.List;
import java.util.Map;

import com.yixin.service400.base.BaseDao;
import com.yixin.service400.bean.TService400Workgroup;
@SuppressWarnings("unchecked")
public interface WorkGroupService extends BaseDao<TService400Workgroup> {

	int getTotalCountsByParams(Map map) throws Exception;
	
	List<TService400Workgroup> queryPageByParams(Map map) throws Exception;
	
	List getWorkOverFlow(Long groupid,int workoverflowisphone) throws Exception;
	
	List getNoWorkOverFlow(Long groupid,int noworkoverflowisphone) throws Exception;
	
    List getWorkOverFlow_new(Long groupid) throws Exception;
	
	List getNoWorkOverFlow_new(Long groupid) throws Exception;
	
	List getAllWorkGroup() throws Exception;
	
	List getNoWorkFlowGroup() throws Exception;
	
	List getAllWorkGroupExceptMe(Long id) throws Exception;
	
	List getNoWorkFlowGroupExceptMe(Long id) throws Exception;
	
	List<TService400Workgroup> getWorkgroupByName(String name) throws Exception;
	
	int getCountoverflowgroup(TService400Workgroup workgroup) throws Exception;
	
	List<TService400Workgroup> getWorkGroupByUsername(String username) throws Exception;
	
	List getStatus(Long workgroupid) throws Exception;

	List<TService400Workgroup> getWorkGroupList() throws Exception;
	
	void updateStatusByIds(String ids,String date) throws Exception;
	
	void updateStatusByDeleteIds(String deleteids,String date) throws Exception;

	void updateCloseLoop(String sendperson) throws Exception;
	
	int checkWGExist(String workgroupname, Long workgroupid) throws Exception;
}