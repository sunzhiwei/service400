package com.yixin.service400.service.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.yixin.service400.base.BaseDaoImpl;
import com.yixin.service400.bean.TService400KeyWorkgroup;
import com.yixin.service400.bean.TService400User;
import com.yixin.service400.service.KeyGroup400Service;

@Service("keyGroup400Service")
public class KeyGroupServiceImpl extends BaseDaoImpl<TService400KeyWorkgroup>
		implements KeyGroup400Service {
	@SuppressWarnings("unchecked")
	@Override
	public void deleteGroup(Long groupid, Long id,TService400User currentUser) throws Exception {
		String hql = "from TService400KeyWorkgroup where ivr_workgroup_status!=11 and keyId="
				+ id + " and workgroupId=" + groupid;
		Query qu = this.getSession().createQuery(hql);
		List list = qu.list();
		for (Object obj : list) {
			TService400KeyWorkgroup wkg = (TService400KeyWorkgroup) obj;
			Integer iws = wkg.getIvr_workgroup_status();
			if (iws.intValue() == TService400KeyWorkgroup.STATUS_ADD) {
				this.delete(obj);
			} else if (iws.intValue() == TService400KeyWorkgroup.STATUS_CLOSELOOP
					|| iws.intValue() == TService400KeyWorkgroup.STATUS_SENDMAIL) {
				wkg.setIvr_workgroup_status(TService400KeyWorkgroup.STATUS_DELETE);
				wkg.setUsername(currentUser.getUsername());
				this.update(wkg);
			}
		}
	}

	@Override
	public void updateStatusByDeleteIds(String deleteids, String date) throws Exception{
		String sql = "update t_service400_key_workgroup set ivr_workgroup_status=11,mailtime='" + date
				+ "'" + " where key_workgroup_id in " + deleteids;
           getSession().createSQLQuery(sql).executeUpdate();
	}

	@Override
	public void updateStatusByIds(String ids, String date) throws Exception{
		String sql = "update t_service400_key_workgroup set ivr_workgroup_status=9,mailtime='" + date
		+ "'" + " where key_workgroup_id in " + ids;
   getSession().createSQLQuery(sql).executeUpdate();	}
}
