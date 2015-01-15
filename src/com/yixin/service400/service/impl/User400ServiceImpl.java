package com.yixin.service400.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import com.yixin.service400.base.BaseDaoImpl;
import com.yixin.service400.bean.TService400Applicationform;
import com.yixin.service400.bean.TService400Role;
import com.yixin.service400.bean.TService400User;
import com.yixin.service400.service.User400Service;

@Service("user400Service")
public class User400ServiceImpl extends BaseDaoImpl<TService400User> implements
		User400Service {

	@Override
	public void addUser(TService400User ts) throws Exception {
		this.save(ts);
	}

	@SuppressWarnings("unchecked")
	@Override
	public int getTotalCountsByParams(Map map) throws Exception{
		if(map.get("username")!=null && !("").equals(map.get("username").toString())){
				return getSession().createQuery("FROM TService400User where username like :username").setString("username","%"+map.get("username").toString()+"%").list().size();
		}
		return getSession().createQuery("FROM TService400User").list().size();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TService400User> queryPageByParams(Map map) throws Exception{
		String sql="";
		if(map.get("username")!=null && !("").equals(map.get("username").toString())){
			sql="select * from (select t.*, rownum rn from (select * from t_service400_user t where t.username like '"+"%"+map.get("username").toString()+"%"+"' order by t.id desc) t where rownum<="+map.get("endRow")+") where rn>="+map.get("startRow");
			return getSession().createSQLQuery(sql).addEntity(TService400User.class).list();
		}
		sql="select * from (select t.*, rownum rn from (select * from t_service400_user t order by t.id desc) t where rownum<="+map.get("endRow")+") where rn>="+map.get("startRow");
		return getSession().createSQLQuery(sql).addEntity(TService400User.class).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TService400Role> selectRoleList() throws Exception{
		return getSession().createQuery("FROM TService400Role").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TService400Applicationform> selectApplicationformList() throws Exception{
		return getSession().createQuery("FROM TService400Applicationform").list();
	}

	@Override
	public int checkUserExist(String username,Long userid) throws Exception{
		if(userid!=-1)
		    return getSession().createQuery("FROM TService400User u where u.username=:username and u.id!=:userid").setString("username", username).setLong("userid", userid).list().size();
		return getSession().createQuery("FROM TService400User u where u.username=:username").setString("username", username).list().size();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Long> user400numserviceidByUserid(Long userid) throws Exception{
		return getSession().createSQLQuery("select service_400num from t_service400_user_400num where userid=:userid").setLong("userid", userid).list();
	}

	@Override
	public int checkPassword(TService400User currentUser, String password) throws Exception{
		return getSession().createQuery("From TService400User u where u.username=:username and u.password=:password").setString("username", currentUser.getUsername()).setString("password", DigestUtils.md5Hex(password)).list().size();
	}
}
