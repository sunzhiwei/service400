package com.yixin.service400.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.yixin.service400.base.BaseDaoImpl;
import com.yixin.service400.bean.TService400Applicationform;
import com.yixin.service400.bean.TService400Key;
import com.yixin.service400.bean.TService400KeyWorkgroup;
import com.yixin.service400.bean.TService400User;
import com.yixin.service400.bean.TService400Workgroup;
import com.yixin.service400.service.IVR400Service;
import com.yixin.service400.util.DateUtil;

@Service("ivr400Service")
public class IVR400ServiceImpl extends BaseDaoImpl<TService400Key> implements
		IVR400Service {

	@SuppressWarnings("unchecked")
	@Override
	public List<TService400Key> getTService400KeyListByPath(String path)
			throws Exception {
		String hql = "from TService400Key where path like '" + path
				+ "%' and path!='" + path
				+ "' and status !=6 and status!=11 order by name";
		Query query = this.getSession().createQuery(hql);
		return query.list();
	}

	@Override
	public void addTService400Key(TService400Key key) throws Exception {
		this.save(key);
	}

	@Override
	public String getNoHasName(Long id) throws Exception {
		String hql = "select name from TService400Key where pid=" + id
				+ " and status!=6 and status!=11 group by name";
		Query query = this.getSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<String> list = query.list();
		List<String> names = new ArrayList<String>(TService400Key.list);
		String values = "";
		for (int i = 0; i < names.size(); i++) {
			for (String n : list) {
				if (n.equals(names.get(i))) {
					names.remove(i);
					i--;
					break;
				}
			}
		}
		for (String n : names) {
			values += n + ";";
		}
		return values;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TService400Workgroup> getWorkGroupByIVRid(Long pid)
			throws Exception {
		String sql = "select wg.id,wg.name,kwg.ivr_workgroup_status,kwg.path from t_service400_workgroup wg right join t_service400_key_workgroup kwg on wg.id=kwg.workgroup_id and ivr_workgroup_status!=6 and ivr_workgroup_status!=11 and kwg.key_id="
				+ pid;
		Query qu = getSession().createSQLQuery(sql);
		List list = qu.list();
		List<TService400Workgroup> arrayList = new ArrayList<TService400Workgroup>();
		for (Object obj : list) {
			Object[] ob = (Object[]) obj;
			TService400Workgroup tsw = new TService400Workgroup();
			if (ob[0] == null) {
				continue;
			}
			tsw.setId(Long.parseLong(ob[0].toString()));
			tsw.setName(ob[1] == null ? "" : ob[1].toString());
			tsw.setIvrStatus(ob[2] == null ? null : Integer.parseInt(ob[2]
					.toString()));
			tsw.setPath(ob[3] == null ? null : ob[3].toString());
			arrayList.add(tsw);
		}
		return arrayList;
	}

	@Override
	public TService400Key getTService400Key(Long pid) throws Exception {
		return this.getById(pid);
	}

	@Override
	public void updateIVR400Service(TService400Key key) throws Exception {
		TService400Key byId = this.getById(key.getId());
		byId.setContent(key.getContent());
		byId.setName(key.getName());
		byId.setPath(byId.getPath().substring(0,byId.getPath().length()-1)+key.getName());
		if (byId.getStatus().intValue() != TService400Key.STATUS_ADD.intValue()) {
			byId.setStatus(TService400Key.STATUS_UPDATE);
		}
		this.update(byId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void deleteTService400Key(Long id) throws Exception {
		TService400Key tsk = this.getById(id);
		String hql = "from TService400Key where path like '" + tsk.getPath()
				+ "%' and status!=6";
		Query query = this.getSession().createQuery(hql);
		List<TService400Key> list = query.list();
		if (tsk.getStatus().intValue() == TService400Key.STATUS_ADD.intValue()) {
			for (TService400Key key : list) {
				this.delete(key.getId());
			}
		} else {
			for (TService400Key key : list) {
				key.setStatus(TService400Key.STATUS_DELETE);
				this.update(key);
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean isHasGroup(Long id) throws Exception {
		String hql = "from TService400Key where pid =" + id + " and status!=6";
		Query query = this.getSession().createQuery(hql);
		List<TService400Key> list = query.list();
		boolean empty = list.isEmpty();
		hql = "from TService400KeyWorkgroup where keyId=" + id;
		query = this.getSession().createQuery(hql);
		List<TService400KeyWorkgroup> list2 = query.list();
		boolean empty2 = list2.isEmpty();
		return empty && empty2;
	}

	@Override
	public void addKeyGroup(Long id, TService400Workgroup wg,TService400User currentUser) throws Exception {
		TService400KeyWorkgroup tkw = new TService400KeyWorkgroup();
		TService400Key key = this.getById(id);
		tkw.setKeyId(id);
		tkw.setWorkgroupId(wg.getId());
		tkw.setIvr_workgroup_status(TService400KeyWorkgroup.STATUS_ADD);
		tkw.setPath(key.getPath() + "-" + wg.getName());
		tkw.setUsername(currentUser.getUsername());
		this.save(tkw);
	}

	@Override
	public void sendIVRMail() throws Exception {
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TService400Key> getTService400Key(TService400User user) throws Exception {
		return getSession().createQuery(
				"From TService400Key where status in(4,5,6) and username=:username order by path").setString("username", user.getUsername()).list();
	}

	@Override
	public void updateStatusByDeleteIds(String deleteids, String date) throws Exception{
		String sql = "update t_service400_key set status=11,mailtime='" + date
				+ "'" + " where id in " + deleteids;
		getSession().createSQLQuery(sql).executeUpdate();
	}

	@Override
	public void updateStatusByIds(String ids, String date) throws Exception{
		String sql = "update t_service400_key set status=9,mailtime='" + date
				+ "'" + " where id in " + ids;
		getSession().createSQLQuery(sql).executeUpdate();
	}

	@Override
	public void updateCloseLoop(String phonenum) throws Exception {
		String sql = "update t_service400_key set status=10,closelooptime='" + DateUtil.getTime("yyyy-MM-dd")+ "'" + " where path like '" +phonenum+"%' and status=9";
		getSession().createSQLQuery(sql).executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TService400KeyWorkgroup> getTService400KeyWorkgroup(
			TService400User user) throws Exception {
		return getSession().createQuery("From TService400KeyWorkgroup where ivr_workgroup_status in(4,5,6) and username=:username order by path").setString("username", user.getUsername()).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TService400Key> getTService400KeyByCondition(
			TService400User user, String phonenum) throws Exception {
		return getSession().createQuery(
		"From TService400Key where status in(4,5,6) and username=:username and phonenum=:phonenum order by path").setString("username", user.getUsername()).setString("phonenum", phonenum).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TService400Applicationform> getApplicationformByNum(
			String phonenum) throws Exception {
		return getSession().createQuery(
		"From TService400Applicationform where phonenum=:phonenum").setString("phonenum", phonenum).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TService400KeyWorkgroup> getTService400KeyWorkgroupByCon(
			TService400User user, String phonenum) throws Exception {
		return getSession().createSQLQuery("select kw.* From T_SERVICE400_KEY_WORKGROUP kw,T_SERVICE400_KEY k where kw.key_id=k.id and kw.ivr_workgroup_status in(4,5,6) and kw.username=:username and k.phonenum=:phonenum order by kw.path").addEntity(TService400KeyWorkgroup.class).setString("username", user.getUsername()).setString("phonenum", phonenum).list();
	}
}
