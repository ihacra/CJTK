package com.hacra.cjtk.modules.question.dao;

import org.apache.ibatis.annotations.Mapper;

import com.hacra.cjtk.commons.base.BaseDao;
import com.hacra.cjtk.modules.question.entity.Question;

/**
 * dao
 * 
 * @author Hacra
 * @date 2020-11-10
 */
@Mapper
public interface QuestionDao extends BaseDao<Question> {

}
