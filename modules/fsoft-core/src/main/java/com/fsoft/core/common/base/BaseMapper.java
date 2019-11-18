package com.fsoft.core.common.base;

import java.sql.SQLException;
import java.util.List;

import com.fsoft.core.common.QueryParam;

/**
 * 基础实体操作方法
 * @author Fish
 * @email it.fish2010@foxmail.com
 * @create 2019-03-24
 * @copyright 佳乐软件股份有限公司© 2019-2019
 *
 * @param <T>
 */
public interface BaseMapper<T> {
	/**
	 * 新增
	 * 
	 * @param T
	 * @return
	 * @throws SQLException
	 */
	int insert(T entity) throws SQLException;

	/**
	 * 修改实体
	 * 
	 * @author Fish 2019-03-24
	 * @param entity
	 * @return
	 * @throws SQLException
	 */
	int update(T entity) throws SQLException;

	/**
	 * 根据主键ID，删除实体
	 * 
	 * @author Fish 2019-03-24
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	int delete(String id) throws SQLException;

	/***
	 * 批量新增
	 * 
	 * @author Fish 2019-03-24
	 * @param list
	 * @return
	 * @throws SQLException
	 */
	int insertBatch(List<T> list) throws SQLException;

	/**
	 * 批量更新
	 * 
	 * @author Fish 2019-03-24
	 * @param list
	 * @return
	 * @throws SQLException
	 */
	int updateBatch(List<T> list) throws SQLException;

	/**
	 * 批量删除
	 * 
	 * @author Fish 2019-03-24
	 * @param list
	 * @return
	 * @throws SQLException
	 */
	int deleteBatch(List<String> list) throws SQLException;

	/***
	 * 根据ID查询实体Bean
	 * 
	 * @author Fish 2019-03-24
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	T selectByKey(String id) throws SQLException;

	/***
	 * 列表查询
	 * 
	 * @author Fish 2019-03-24
	 * @param param
	 * @return
	 * @throws SQLException
	 */
	List<T> selectList(QueryParam param) throws SQLException;
}
