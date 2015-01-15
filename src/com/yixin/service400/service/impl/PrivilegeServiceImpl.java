package com.yixin.service400.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yixin.service400.base.BaseDaoImpl;
import com.yixin.service400.bean.TService400Privilege;
import com.yixin.service400.service.PrivilegeService;



@Service
public class PrivilegeServiceImpl extends BaseDaoImpl<TService400Privilege> implements PrivilegeService {

	@SuppressWarnings("unchecked")
	@Override
	public List<TService400Privilege> findByLevel(Integer menuid,Integer level) throws Exception{
		return getSession()
				.createQuery("FROM TService400Privilege p where p.id=:id and p.levelid=:levelid")
				.setLong("id",menuid ).setLong("levelid",level)
				.list();
}
}
