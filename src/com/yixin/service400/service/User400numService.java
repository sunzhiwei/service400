package com.yixin.service400.service;

import com.yixin.service400.base.BaseDao;
import com.yixin.service400.bean.TService400User400num;

public interface User400numService extends BaseDao<TService400User400num>{
   void deleteByUserid(Long userid) throws Exception;
}
