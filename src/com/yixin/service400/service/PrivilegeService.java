package com.yixin.service400.service;

import java.util.List;
import com.yixin.service400.base.BaseDao;
import com.yixin.service400.bean.TService400Privilege;



public interface PrivilegeService extends BaseDao<TService400Privilege> {

    List<TService400Privilege> findByLevel(Integer menuid,Integer level) throws Exception;
}