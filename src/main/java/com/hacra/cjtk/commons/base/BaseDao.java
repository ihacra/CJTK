package com.hacra.cjtk.commons.base;

import java.util.List;

/**
 * BaseDao
 * 
 * @author Hacra
 * @date 2020-11-10
 */
public interface BaseDao<T> {

	/**
	 * 获取单条数据
	 * @param id
	 * @return
	 */
	public T get(String id);
	
	/**
	 * 获取多条数据
	 * @param entity
	 * @return
	 */
	public List<T> findList(T entity);
	
	/**
	 * 分页查询
	 * @param entity
	 * @return
	 */
	public List<T> findPage(T entity);
	
	/**
	 * 获取总数量
	 * @param entity
	 * @return
	 */
	public int getCount(T entity);
	
	/**
	 * 插入数据
	 * @param entity
	 * @return
	 */
	public int insert(T entity);
	
	/**
	 * 更新数据
	 * @param entity
	 * @return
	 */
	public int update(T entity);
	
	/**
	 * 删除数据
	 * @param entity
	 * @return
	 */
	public int delete(T entity);
	
	/**
	 * 获取下一id
	 * @param entity
	 * @return
	 */
	public String getNextId();
}
