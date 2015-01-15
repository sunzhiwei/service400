package com.yixin.service400.service.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.yixin.service400.base.BaseDaoImpl;
import com.yixin.service400.bean.TService400Applicationform;
import com.yixin.service400.bean.TService400User;
import com.yixin.service400.bean.TService400UserRole;
import com.yixin.service400.service.Apply400Service;
import com.yixin.service400.util.DateUtil;

@Service("apply400Service")
public class Apply400ServiceImpl extends
		BaseDaoImpl<TService400Applicationform> implements Apply400Service {

	@Override
	public void addApply(TService400Applicationform ts) throws Exception {
		this.save(ts);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TService400Applicationform> getTService400ApplicationformList()
			throws Exception {
		String hql = "from TService400Applicationform where status=0 or status=1";
		Query query = this.getSession().createQuery(hql);
		return query.list();
	}

	@Override
	public TService400Applicationform getTService400ApplicationformById(Long id)
			throws Exception {
		TService400Applicationform ts = this.getById(id);
		return ts;
	}

	@Override
	public void supApply(TService400Applicationform ts) throws Exception {
		TService400Applicationform tsa = this.getById(ts.getId());
		tsa.setCostcentre(ts.getCostcentre());
		tsa.setLangingnum(ts.getLangingnum());
		tsa.setLangingnum2(ts.getLangingnum2());
		tsa.setLangingnum3(ts.getLangingnum3());
		tsa.setFactopentime(ts.getFactopentime());
		if (tsa.getFilepath() != null)
			tsa.setFilepath(tsa.getFilepath() + ts.getFilepath());
		else
			tsa.setFilepath(ts.getFilepath());
		tsa.setStatus(TService400Applicationform.STATUS_YSH);
		tsa.setChecktime(DateUtil.getTime());
		this.update(tsa);
	}

	@Override
	public void closeLoop(TService400Applicationform ts) throws Exception {
		TService400Applicationform tsa = this.getById(ts.getId());
		tsa.setClosetime(DateUtil.getTime());
		tsa.setStatus(TService400Applicationform.STATUS_BH);
		this.update(tsa);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TService400Applicationform> getHasApplyTService400ApplicationformList(TService400User currentUser,int count)
			throws Exception {
//		String hql = "from TService400Applicationform where status=3 or status=1";
		String sql="";
		if (count > 0)
			sql = "select * from t_service400_applicationform where phonenum in (select ser.phonenum from t_service400 ser) order by id desc";
		else
			sql = "select * from t_service400_applicationform where phonenum in("
					+ "select ser.phonenum from t_service400_user_400num u400,t_service400 ser,t_service400_user u "
					+ "where u400.service_400num = ser.id and u.id=u400.userid and u.id="
					+ currentUser.getId() + ") order by id desc";
		Query query = this.getSession().createSQLQuery(sql).addEntity(TService400Applicationform.class);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public int getTotalCountsByParams(Map map) throws Exception{
		String hql="from TService400Applicationform where (status=1 or status=0) ";
		if(map.get("applicationperson") != null && !("").equals(map.get("applicationperson").toString())){
			hql+=" and applicationperson like '%"+map.get("applicationperson").toString()+"%'";
		}
		if(map.get("phonenum") != null && !("").equals(map.get("phonenum").toString())){
			hql+=" and phonenum like '%"+map.get("phonenum").toString()+"%'";
		}
		if(map.get("company") != null && !("").equals(map.get("company").toString())){
			hql+=" and company like '%"+map.get("company").toString()+"%'";
		}
		if(map.get("department") != null && !("").equals(map.get("department").toString())){
			hql+=" and department like '%"+map.get("department").toString()+"%'";
		}
		if(map.get("startapplicationtime") != null && !("").equals(map.get("startapplicationtime").toString())){
			hql += " and applicationtime>='" + map.get("startapplicationtime").toString() + "'";
		}
		if(map.get("endapplicationtime") != null && !("").equals(map.get("endapplicationtime").toString())){
			hql += " and applicationtime<='" + map.get("endapplicationtime").toString() + "'";
		}
		return getSession().createQuery(hql).list().size();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TService400Applicationform> queryPageByParams(Map map) throws Exception{
		String sql="select * from (select t.*, rownum rn from (select * from  t_service400_applicationform t where (status=1 or status=0) ";
		if(map.get("applicationperson") != null && !("").equals(map.get("applicationperson").toString())){
			sql+=" and applicationperson like '%"+map.get("applicationperson").toString()+"%'";
		}
		if(map.get("phonenum") != null && !("").equals(map.get("phonenum").toString())){
			sql+=" and phonenum like '%"+map.get("phonenum").toString()+"%'";
		}
		if(map.get("company") != null && !("").equals(map.get("company").toString())){
			sql+=" and company like '%"+map.get("company").toString()+"%'";
		}
		if(map.get("department") != null && !("").equals(map.get("department").toString())){
			sql+=" and department like '%"+map.get("department").toString()+"%'";
		}
		if(map.get("startapplicationtime") != null && !("").equals(map.get("startapplicationtime").toString())){
			sql += " and applicationtime>='" + map.get("startapplicationtime").toString() + "'";
		}
		if(map.get("endapplicationtime") != null && !("").equals(map.get("endapplicationtime").toString())){
			sql += " and applicationtime<='" + map.get("endapplicationtime").toString() + "'";
		}
		sql+=" order by t.id desc) t where rownum<="+ map.get("endRow")+ " ) where rn>=" + map.get("startRow");
		return getSession().createSQLQuery(sql).addEntity(TService400Applicationform.class).list();
	}

	// 验证此用户是否是管理员
	@Override
	public int checkAdminByUser(TService400User currentUser) throws Exception{
		String sql = "select ur.* from t_service400_user u, t_service400_user_role ur, t_service400_role r " +
				"where u.id = ur.userid and r.id = ur.roleid and r.id = 1 " +
				"and u.id ="+currentUser.getId();
		return getSession().createSQLQuery(sql).addEntity(TService400UserRole.class).list().size();
	}

}
