package com.yixin.service400.service;

import java.math.BigDecimal;
import java.util.List;

import com.yixin.service400.base.BaseDao;
import com.yixin.service400.bean.TService400User;



public interface UserService extends BaseDao<TService400User> {

	/**
	 * 查询用户
	 * 
	 * @param loginName
	 * @param password
	 *            明文密码
	 * @return
	 */
	TService400User getByLoginNameAndPassword(String username, String password) throws Exception;
	
    List<BigDecimal> userRoleidList(Integer userid) throws Exception;
    
    List<BigDecimal> selectPrivilegeidByRoleid(Integer roleid) throws Exception;
    
}