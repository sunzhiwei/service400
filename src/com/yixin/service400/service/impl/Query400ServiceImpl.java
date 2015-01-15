package com.yixin.service400.service.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.yixin.service400.base.BaseDaoImpl;
import com.yixin.service400.bean.TService400File;
import com.yixin.service400.service.Query400Service;
import com.yixin.service400.util.DateUtil;

@Service("query400Service")
public class Query400ServiceImpl extends BaseDaoImpl<TService400File> implements
		Query400Service {

	@SuppressWarnings("unchecked")
	@Override
	public List<TService400File> getClosable(String time1, String time2)
			throws Exception {
		String hql = "from TService400File where status!=10";
		if (time1 != null && !time1.equals("")) {
			hql += " and sendtime>'" + time1 + "'";
		}
		if (time2 != null && !time2.equals("")) {
			hql += " and sendtime<'" + time2 + "'";
		}
		Query query = this.getSession().createQuery(hql);
		return query.list();
	}

	@Override
	public TService400File closeLoop(Long id) throws Exception {
		TService400File tsf = this.getById(id);
		tsf.setCloselooptime(DateUtil.getTime("yyyy-MM-dd"));
		tsf.setStatus(TService400File.STATUS_CLOSELOOP);
		this.update(tsf);
		return tsf;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TService400File> getHisNotes(String time1, String time2,
			String time3, String time4) throws Exception {
		String hql = "from TService400File where status=10";
		if (time1 != null && !time1.equals("")) {
			hql += " and sendtime>'" + time1 + "'";
		}
		if (time2 != null && !time2.equals("")) {
			hql += " and sendtime<'" + time2 + "'";
		}
		if (time3 != null && !time3.equals("")) {
			hql += " and closelooptime>'" + time3 + "'";
		}
		if (time4 != null && !time4.equals("")) {
			hql += " and closelooptime<'" + time4 + "'";
		}
		Query qu = this.getSession().createQuery(hql);
		return qu.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public int getTotalCountsByParams(Map map) throws Exception{
		String hql = "from TService400File where status=10 ";
		if(map.get("sendperson") != null && !("").equals(map.get("sendperson").toString())){
			hql += " and sendperson like '" + map.get("sendperson").toString() + "'";
		}
		if(map.get("type") != null && !("").equals(map.get("type").toString())){
			hql += " and type='" + map.get("type").toString() + "'";
		}
		if(map.get("phonenum") != null && !("").equals(map.get("phonenum").toString())){
			hql += " and phonenum like '" + map.get("phonenum").toString() + "'";
		}
		if(map.get("sendstarttime") != null && !("").equals(map.get("sendstarttime").toString())){
			hql += " and sendtime>='" + map.get("sendstarttime").toString() + "'";
		}
		if(map.get("sendendtime") != null && !("").equals(map.get("sendendtime").toString())){
			hql += " and sendtime<='" + map.get("sendendtime").toString() + "'";
		}
		if(map.get("closestarttime") != null && !("").equals(map.get("closestarttime").toString())){
			hql += " and closelooptime>='" + map.get("closestarttime").toString() + "'";
		}
		if(map.get("closeendtime") != null && !("").equals(map.get("closeendtime").toString())){
			hql += " and closelooptime<='" + map.get("closeendtime").toString() + "'";
		}
		return getSession().createQuery(hql).list().size();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TService400File> queryPageByParams(Map map) throws Exception{
		String sql="select * from (select t.*, rownum rn from (select * from t_service400_file t where status=10 ";
		if(map.get("sendperson") != null && !("").equals(map.get("sendperson").toString())){
			sql += " and sendperson like '" + map.get("sendperson").toString() + "'";
		}
		if(map.get("type") != null && !("").equals(map.get("type").toString())){
			sql += " and type='" + map.get("type").toString() + "'";
		}
		if(map.get("phonenum") != null && !("").equals(map.get("phonenum").toString())){
			sql += " and phonenum like '" + map.get("phonenum").toString() + "'";
		}
		if(map.get("sendstarttime") != null && !("").equals(map.get("sendstarttime").toString())){
			sql += " and sendtime>='" + map.get("sendstarttime").toString() + "'";
		}
		if(map.get("sendendtime") != null && !("").equals(map.get("sendendtime").toString())){
			sql += " and sendtime<='" + map.get("sendendtime").toString() + "'";
		}
		if(map.get("closestarttime") != null && !("").equals(map.get("closestarttime").toString())){
			sql += " and closelooptime>='" + map.get("closestarttime").toString() + "'";
		}
		if(map.get("closeendtime") != null && !("").equals(map.get("closeendtime").toString())){
			sql += " and closelooptime<='" + map.get("closeendtime").toString() + "'";
		}
		sql+=" order by t.id desc) t where rownum<="+ map.get("endRow")+ ") where rn>=" + map.get("startRow");
		return getSession().createSQLQuery(sql).addEntity(TService400File.class).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public int getCLTotalCountsByParams(Map map) throws Exception{
		String hql = "from TService400File where status!=10 and type!='A' and sendperson='"+map.get("sendperson")+"'";
		if(map.get("type") != null && !("").equals(map.get("type").toString())){
			hql += " and type='" + map.get("type").toString() + "'";
		}
		if(map.get("sendstarttime") != null && !("").equals(map.get("sendstarttime").toString())){
			hql += " and sendtime>='" + map.get("sendstarttime").toString() + "'";
		}
		if(map.get("sendendtime") != null && !("").equals(map.get("sendendtime").toString())){
			hql += " and sendtime<='" + map.get("sendendtime").toString() + "'";
		}
		return getSession().createQuery(hql).list().size();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TService400File> queryCLPageByParams(Map map) throws Exception{
		String sql="select * from (select t.*, rownum rn from (select * from  t_service400_file t where status!=10 and type!='A' and sendperson='"+map.get("sendperson")+"'";
		if(map.get("type") != null && !("").equals(map.get("type").toString())){
			sql += " and type='" + map.get("type").toString() + "'";
		}
		if(map.get("sendstarttime") != null && !("").equals(map.get("sendstarttime").toString())){
			sql += " and sendtime>='" + map.get("sendstarttime").toString() + "'";
		}
		if(map.get("sendendtime") != null && !("").equals(map.get("sendendtime").toString())){
			sql += " and sendtime<='" + map.get("sendendtime").toString() + "'";
		}
		sql+=" order by t.id desc) t where rownum<="+ map.get("endRow")+ ") where rn>=" + map.get("startRow");
		return getSession().createSQLQuery(sql).addEntity(TService400File.class).list();
	}

	@Override
	public TService400File getTSfByID(Long id) throws Exception {
		TService400File byId = this.getById(id);
		return byId;
	}
}
