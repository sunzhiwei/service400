package com.yixin.service400.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yixin.service400.base.BaseDaoImpl;
import com.yixin.service400.bean.TService400UserRole;
import com.yixin.service400.service.UserRoleService;
@Service
public class UserRoleServiceImpl extends BaseDaoImpl<TService400UserRole> implements
		UserRoleService {

	@Override
	public void deleteByUserid(Long userid) throws Exception{
		getSession().createSQLQuery("delete from t_service400_user_role where userid=:userid").setLong("userid", userid).executeUpdate();;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Long> userroleidlist(Long userid) throws Exception{
		return getSession().createSQLQuery("select roleid from t_service400_user_role where userid=:userid").setLong("userid", userid).list();
	}
	
}
