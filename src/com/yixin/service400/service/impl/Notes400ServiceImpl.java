package com.yixin.service400.service.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.yixin.service400.base.BaseDaoImpl;
import com.yixin.service400.bean.TService400Notes;
import com.yixin.service400.service.Notes400Service;

@Service("notes400Service")
public class Notes400ServiceImpl extends BaseDaoImpl<TService400Notes>
		implements Notes400Service {

	@Override
	public void addNotes(TService400Notes notes) throws Exception {
		/*Query qu = this.getSession().createQuery("select max(id) from TService400Notes");
		Object obj = qu.list().get(0);
		if(obj==null){
			notes.setId(0l);
		}else{
			notes.setId((Long)obj+1l);
		}*/
		this.save(notes);
	}

	@Override
	public String getFirstNotes() throws Exception {
		Query query = this.getSession().createQuery("from TService400Notes order by id desc");
		TService400Notes obj = (TService400Notes) query.list().get(0);
		return obj.getContent().toString();
	}

}
