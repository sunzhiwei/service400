package com.yixin.service400.service;

import com.yixin.service400.bean.TService400User;



public interface LoginService {
	TService400User findOneUser(String username,String password) throws Exception;
}
