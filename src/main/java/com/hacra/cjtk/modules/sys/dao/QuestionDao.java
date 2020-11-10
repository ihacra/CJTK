package com.hacra.cjtk.modules.sys.dao;

import org.apache.ibatis.annotations.Mapper;

import com.hacra.cjtk.commons.base.BaseDao;
import com.hacra.cjtk.modules.sys.entity.Question;

/**
 * dao
 * 
 * @author Hacra
 * @date 2020-11-10
 */
@Mapper
public interface QuestionDao extends BaseDao<Question> {

}
