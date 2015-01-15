package com.yixin.service400.service.impl;

import org.springframework.stereotype.Service;

import com.yixin.service400.base.BaseDaoImpl;
import com.yixin.service400.bean.TService400User400num;
import com.yixin.service400.service.User400numService;
@Service
public class User400numServiceImpl extends BaseDaoImpl<TService400User400num> implements
		User400numService {

	@Override
	public void deleteByUserid(Long userid) throws Exception{
		getSession().createSQLQuery("delete from t_service400_user_400num where userid=:userid").setLong("userid", userid).executeUpdate();
	}

}
