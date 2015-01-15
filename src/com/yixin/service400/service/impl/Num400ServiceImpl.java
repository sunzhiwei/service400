package com.yixin.service400.service.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.yixin.service400.base.BaseDaoImpl;
import com.yixin.service400.bean.TService400;
import com.yixin.service400.bean.TService400Applicationform;
import com.yixin.service400.service.Num400Service;
@Service("num400Service")
public class Num400ServiceImpl extends BaseDaoImpl<TService400> implements
		Num400Service {

	@Override
	public List<TService400> get400List(TService400 ts4) throws Exception {
		String hql = "from TService400 where 1=1";
		if(ts4.getPhonenum()!=null&&!ts4.getPhonenum().trim().equals("")){
			hql += " and phonenum like '%"+ts4.getPhonenum()+"%'";
		}
		if(ts4.getOperationCompany()!=null&&!ts4.getOperationCompany().trim().equals("")){
			hql += " and operationCompany='"+ts4.getOperationCompany()+"'";
		}
		hql +=" and status =0 order by phonenum";
		Query query = this.getSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<TService400> all = query.list();
		return all;
	}

	@Override
	public void addTService400(TService400 ts) throws Exception {
		this.save(ts);
	}

	@Override
	public TService400 getTService400(long parseLong) throws Exception {
		TService400 ts = this.getById(parseLong);
		return ts;
	}

	@Override
	public void update400(TService400 ts) throws Exception {
		this.update(ts);
	}

	@Override
	public void delete400(long parseLong) throws Exception {
		this.delete(parseLong);
	}

	@Override
	public void setApply(long service400Id) throws Exception {
		TService400 ts = this.getById(service400Id);
		ts.setStatus(TService400Applicationform.STATUS_YSH);
		this.update(ts);
	}

	@SuppressWarnings("unchecked")
	@Override
	public int getTotalCountsByParams(Map map) throws Exception{
		String hql="from TService400 where 1=1";
		if(map.get("phonenum") != null && !("").equals(map.get("phonenum").toString())){
			hql+=" and phonenum like '%"+map.get("phonenum").toString()+"%'";
		}
		if(map.get("company") != null && !("").equals(map.get("company").toString())){
			hql+=" and operation_company='"+map.get("company").toString()+"'";
		}
		return getSession().createQuery(hql).list().size();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TService400> queryPageByParams(Map map) throws Exception{
		String sql="select * from (select t.*, rownum rn from (select * from  t_service400 t where 1=1 ";
		if(map.get("phonenum") != null && !("").equals(map.get("phonenum").toString())){
			sql+=" and t.phonenum like '%"+map.get("phonenum").toString()+"%'";
		}
		if(map.get("company") != null && !("").equals(map.get("company").toString())){
			sql+=" and t.operation_company='"+map.get("company").toString()+"'";
		}
		sql+=" order by t.id desc) t where rownum<="+ map.get("endRow")+ ") where rn>=" + map.get("startRow");
		return getSession().createSQLQuery(sql).addEntity(TService400.class).list();
	}

	@Override
	public int getRepeatCounts(String phonenum) throws Exception{
		return getSession().createQuery("from TService400 where phonenum=:phonenum").setString("phonenum", phonenum).list().size();
	}
	
}
