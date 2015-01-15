package com.yixin.service400.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import com.yixin.service400.base.BaseDaoImpl;
import com.yixin.service400.bean.TService400User;
import com.yixin.service400.service.UserService;



@Service
public class UserServiceImpl extends BaseDaoImpl<TService400User> implements UserService {

	public TService400User getByLoginNameAndPassword(String username, String password) throws Exception{
		return (TService400User) getSession().createQuery(//
				"FROM TService400User u WHERE u.username=? AND u.password=?")//
				.setParameter(0, username)//
				.setParameter(1, DigestUtils.md5Hex(password))// 要使用MD5的摘要
				.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BigDecimal> userRoleidList(Integer userid) throws Exception{
		return getSession().createSQLQuery("select roleid from t_service400_user_role ur where ur.userid=:userid")
				.setLong("userid", userid).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BigDecimal> selectPrivilegeidByRoleid(Integer roleid) throws Exception{
		return getSession().createSQLQuery("select privilegeid from t_service400_role_privilege rp where rp.roleid=:roleid")
		.setLong("roleid", roleid).list();
	}
	
}
