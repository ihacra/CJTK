package com.hacra.cjtk.commons.base;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.hacra.cjtk.commons.util.StringUtils;

/**
 * BaseService
 * 
 * @author Hacra
 * @date 2020-11-10
 */
@Transactional(readOnly = true)
public abstract class BaseService<D extends BaseDao<T>, T extends BaseEntity> {

	@Autowired
	private D dao;
	
	/**
	 * 获取单条数据
	 * @param id
	 * @return
	 */
	public T get(String id) {
		return dao.get(id);
	}
	
	/**
	 * 获取多条数据
	 * @param entity
	 * @return
	 */
	public List<T> findList(T entity) {
		return dao.findList(entity);
	}
	
	/**
	 * 保存数据
	 * @param entity
	 */
	@Transactional(readOnly = false)
	public void save(T entity) {
		if (StringUtils.isBlank(entity.getId())) {
			entity.setCreateDate(new Date());
			entity.setUpdateDate(entity.getCreateDate());
			dao.insert(entity);
		} else {
			entity.setUpdateDate(new Date());
			dao.update(entity);
		}
	}
	
	/**
	 * 删除数据
	 * @param entity
	 */
	@Transactional(readOnly = false)
	public void delete(T entity) {
		entity.setUpdateDate(new Date());
		dao.delete(entity);
	}
}
