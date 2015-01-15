package com.yixin.service400.action;


import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.yixin.service400.base.ModelDrivenBaseAction;
import com.yixin.service400.bean.TService400Notes;
import com.yixin.service400.util.DateUtil;

@Controller
@Scope("prototype")
public class Notes400Action extends ModelDrivenBaseAction<TService400Notes> {
	static Logger log = Logger.getLogger(Notes400Action.class);
	private static final long serialVersionUID = 7966751478276556185L;
	private String path;
	private TService400Notes notes = new TService400Notes();
	private String content;

	public String editNotes() throws Exception{
		try {
			content = notes400Service.getFirstNotes();
			log.info(currentUser.getUsername() + "获取400号码申请公告告文！");
		} catch (Exception e) {
			log.error(currentUser.getUsername() + "获取400号码申请公告告文失败！");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return "editNotes";
	}

	public String addNotes() throws Exception{
		try {
			notes.setInserttime(DateUtil.getTime("yyyy-MM-dd"));
			notes.setOpentime(notes.getInserttime());
			notes.setUsermail(this.getCurrentUser().getUsername());
			notes400Service.addNotes(notes);
			log.info(currentUser.getUsername() + "添加400号码申请公告告文！");
		} catch (Exception e) {
			log.error(currentUser.getUsername() + "添加400号码申请公告告文失败！");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return "addNotes";
	}

	/**********************************************************************/
	
	public String getPath() {
		if (path == null || path.equals("")) {
			return request.getContextPath();
		}
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public TService400Notes getNotes() {
		return notes;
	}

	public void setNotes(TService400Notes notes) {
		this.notes = notes;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
