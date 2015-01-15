package com.yixin.service400.base;

import java.util.List;


public interface BaseDao<T> {

	/**
	 * 保存实体
	 * 
	 * @param entity
	 * @throws Exception 
	 */
	Object save(Object entity) throws Exception;
	
	/**
	 * 删除实体
	 * 
	 * @param id
	 */
	void delete(Long id) throws Exception;
	void delete(Object obj) throws Exception;

	/**
	 * 更新实体
	 * 
	 * @param entity
	 */
	T update(T entity) throws Exception;

	/**
	 * 查询实体，如果id为null，则返回null，并不会抛异常。
	 * 
	 * @param id
	 * @return
	 */
	T getById(Long id);

	/**
	 * 查询实体
	 * 
	 * @param ids
	 * @return
	 */
	List<T> getByIds(Long[] ids);

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	List<T> findAll();

}
