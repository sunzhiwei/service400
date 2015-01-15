package com.yixin.service400.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yixin.service400.base.BaseDaoImpl;
import com.yixin.service400.bean.TService400Workgroup;
import com.yixin.service400.service.WorkGroupService;
import com.yixin.service400.util.DateUtil;

@SuppressWarnings("unchecked")
@Service
public class WorkGroupServiceImpl extends BaseDaoImpl<TService400Workgroup>
		implements WorkGroupService {

	@Override
	public List<TService400Workgroup> queryPageByParams(Map map) throws Exception{
		String sql="select * from (select t.*, rownum rn from (select * from  t_service400_workgroup t where t.status not in(6,11) ";
		if(map.get("workgroupname") != null && !("").equals(map.get("workgroupname").toString())){
			sql += " and name like '%" + map.get("workgroupname").toString() + "%'";
		}
		if(map.get("starttime") != null && !("").equals(map.get("starttime").toString())){
			sql += " and starttime>='" + map.get("starttime").toString() + "'";
		}
		if(map.get("endtime") != null && !("").equals(map.get("endtime").toString())){
			sql += " and endtime<='" + map.get("endtime").toString() + "'";
		}
		if(map.get("workoverflowgroup") != null && !("").equals(map.get("workoverflowgroup").toString())){
			sql += " and workoverflow_group like '%" + map.get("workoverflowgroup").toString() + "%'";
		}
		if(map.get("noworkoverflowgroup") != null && !("").equals(map.get("noworkoverflowgroup").toString())){
			sql += " and noworkoverflow_group like '%" + map.get("noworkoverflowgroup").toString() + "%'";
		}
		sql+=" order by t.id desc) t where rownum<="+ map.get("endRow")+ ") where rn>=" + map.get("startRow");
		return getSession().createSQLQuery(sql).addEntity(TService400Workgroup.class).list();
	}

	@Override
	public int getTotalCountsByParams(Map map) throws Exception{
		String hql="FROM TService400Workgroup where status not in(6,11)";
		if(map.get("workgroupname") != null && !("").equals(map.get("workgroupname").toString())){
			hql += " and name like '%" + map.get("workgroupname").toString() + "%'";
		}
		if(map.get("starttime") != null && !("").equals(map.get("starttime").toString())){
			hql += " and starttime>='" + map.get("starttime").toString() + "'";
		}
		if(map.get("endtime") != null && !("").equals(map.get("endtime").toString())){
			hql += " and endtime<='" + map.get("endtime").toString() + "'";
		}
		if(map.get("workoverflowgroup") != null && !("").equals(map.get("workoverflowgroup").toString())){
			hql += " and workoverflow_group like '%" + map.get("workoverflowgroup").toString() + "%'";
		}
		if(map.get("noworkoverflowgroup") != null && !("").equals(map.get("noworkoverflowgroup").toString())){
			hql += " and noworkoverflow_group like '%" + map.get("noworkoverflowgroup").toString() + "%'";
		}
		return getSession().createQuery(hql).list().size();
	}

	@Override
	public List getWorkOverFlow(Long groupid, int workoverflowisphone) throws Exception{
		if (workoverflowisphone == 8)
			return getSession()
					.createSQLQuery(
							"select wg2.name from t_service400_workgroup  wg1, t_service400_overflow_group og, t_service400_workgroup  wg2 where wg1.id = :groupid and wg1.id = og.workgroup_id and wg2.id = og.overflow_id and og.flag = 1")
					.setLong("groupid", groupid).list();
		else
			return getSession()
					.createSQLQuery(
							"select op.phonenum from t_service400_workgroup wg,t_service400_overflow_phone op where wg.id=op.overflow_id and op.flag=1 and wg.id=:groupid")
					.setLong("groupid", groupid).list();

	}

	@Override
	public List getNoWorkOverFlow(Long groupid, int noworkoverflowisphone) throws Exception{
		if (noworkoverflowisphone == 8)
			return getSession()
					.createSQLQuery(
							"select wg2.name from t_service400_workgroup  wg1, t_service400_overflow_group og, t_service400_workgroup  wg2 where wg1.id = :groupid and wg1.id = og.workgroup_id and wg2.id = og.overflow_id and og.flag = 0")
					.setLong("groupid", groupid).list();
		else
			return getSession()
					.createSQLQuery(
							"select op.phonenum from t_service400_workgroup wg,t_service400_overflow_phone op where wg.id=op.overflow_id and op.flag=0 and wg.id=:groupid")
					.setLong("groupid", groupid).list();
	}

	@Override
	public List getNoWorkOverFlow_new(Long groupid) throws Exception{
		return getSession()
				.createSQLQuery(
						"select wg2.name from t_service400_workgroup  wg1, t_service400_overflow_group og, t_service400_workgroup  wg2 where wg1.id = :groupid and wg1.id = og.workgroup_id and wg2.id = og.overflow_id and og.flag = 0"
								+ "                         union"
								+ "select op.phonenum from t_service400_workgroup wg,t_service400_overflow_phone op where wg.id=op.overflow_id and op.flag=0 and wg.id=:groupid")
				.setLong("groupid", groupid).list();
	}

	@Override
	public List getWorkOverFlow_new(Long groupid) throws Exception{
		return getSession()
				.createSQLQuery(
						"select wg2.name from t_service400_workgroup  wg1, t_service400_overflow_group og, t_service400_workgroup  wg2 where wg1.id = :groupid and wg1.id = og.workgroup_id and wg2.id = og.overflow_id and og.flag = 1"
								+ "                          union "
								+ "                          select op.phonenum from t_service400_workgroup wg,t_service400_overflow_phone op where wg.id=op.overflow_id and op.flag=1 and wg.id=:groupid")
				.setLong("groupid", groupid).list();
	}

	@Override
	public List getAllWorkGroup() throws Exception {
		return getSession()
				.createSQLQuery(
						"select name from t_service400_workgroup where status not in(6,11) order by id desc")
				.list();
	}

	@Override
	public List<TService400Workgroup> getWorkgroupByName(String name) throws Exception{
		return getSession()
				.createQuery("FROM TService400Workgroup t where t.name=:name")
				.setString("name", name).list();
	}

	@Override
	public int getCountoverflowgroup(TService400Workgroup workgroup) throws Exception{
		String sql = "select wg.* from t_service400_workgroup wg " +
				"where wg.workoverflow_group='"+workgroup.getName()+"'"+
				" or noworkoverflow_group= '"+workgroup.getName()+"'"+
				"union " +
				"select wg.* from t_service400_workgroup wg,t_service400_key_workgroup kwg " +
				"where wg.id=kwg.workgroup_id and wg.id="+workgroup.getId();
		return getSession().createSQLQuery(sql).addEntity(TService400Workgroup.class).list().size();
	}

	@Override
	public List getAllWorkGroupExceptMe(Long id) throws Exception{
		return getSession()
				.createSQLQuery(
						"select name from t_service400_workgroup where status not in(6,11) and id!=:id order by id desc")
				.setLong("id", id).list();
	}

	@Override
	public List getNoWorkFlowGroupExceptMe(Long id) throws Exception{
		return getSession()
				.createSQLQuery(
						"select distinct groupname from ("
								+ "select t.name groupname"
								+ " from t_service400_workgroup t"
								+ " where t.status not in(6,11)"
								+ " and t.id !=:id"
								+ " union"
								+ " select distinct t1.noworkoverflow_group groupname"
								+ " from t_service400_workgroup t1"
								+ " where t1.status not in(6,11) and t1.noworkoverflow_group is not null"
								+ " and t1.noworkoverflow_group !="
								+ " (select t2.name name from t_service400_workgroup t2 where t2.id =:id))")
				.setLong("id", id).list();
	}

	@Override
	public List getNoWorkFlowGroup() throws Exception{
		return getSession()
				.createSQLQuery(
						"select distinct groupname"
								+ " from (select t.name groupname"
								+ " from t_service400_workgroup t where t.status not in(6,11) "
								+ " union"
								+ " select distinct t1.noworkoverflow_group groupname"
								+ " from t_service400_workgroup t1"
								+ " where t1.status not in(6,11) and t1.noworkoverflow_group is not null)")
				.list();
	}

	@Override
	public List<TService400Workgroup> getWorkGroupByUsername(String username) throws Exception{
		String sql = "select * from t_service400_workgroup where status in(4,5,6) and username='"
				+ username + "'";
		return getSession().createSQLQuery(sql)
				.addEntity(TService400Workgroup.class).list();
	}

	@Override
	public List getStatus(Long workgroupid) throws Exception{
		String sql="select status from t_service400_workgroup where id="+workgroupid;
		return getSession()
				.createSQLQuery(sql).list();
	}

	@Override
	public List<TService400Workgroup> getWorkGroupList() throws Exception {
		String hql = "from TService400Workgroup where status!=6 and status!=11 order by id";
		return this.getSession().createQuery(hql).list();
	}

	@Override
	public void updateStatusByIds(String ids,String date) throws Exception{
		String sql="update t_service400_workgroup set status=9,mailtime='"+date+"'"+" where id in "+ids;
		getSession().createSQLQuery(sql).executeUpdate();
	}

	@Override
	public void updateStatusByDeleteIds(String deleteids,String date) throws Exception{
		String sql="update t_service400_workgroup set status=11,mailtime='"+date+"'"+" where id in "+deleteids;
		getSession().createSQLQuery(sql).executeUpdate();
	}

	@Override
	public void updateCloseLoop(String sendperson) throws Exception {
		String sql="update T_Service400_Workgroup set status=10,closelooptime='"+DateUtil.getTime("yyyy-MM-dd")+"' where username='"+sendperson+"' and status=9";
		getSession().createSQLQuery(sql).executeUpdate();
	}

	@Override
	public int checkWGExist(String workgroupname, Long workgroupid) throws Exception {
		if(workgroupid!=-1)
		    return getSession().createQuery("FROM TService400Workgroup u where u.name=:workgroupname and u.id!=:workgroupid").setString("workgroupname", workgroupname).setLong("workgroupid", workgroupid).list().size();
		return getSession().createQuery("FROM TService400Workgroup u where u.name=:workgroupname").setString("workgroupname", workgroupname).list().size();
	}
	
}
