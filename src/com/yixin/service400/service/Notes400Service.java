package com.yixin.service400.service;

import com.yixin.service400.bean.TService400Notes;


public interface Notes400Service {

	void addNotes(TService400Notes notes) throws Exception;

	String getFirstNotes() throws Exception;

}
