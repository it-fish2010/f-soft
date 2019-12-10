package com.fsoft.core.common.base;

import java.sql.SQLException;
import java.util.List;

import com.fsoft.core.common.QueryParam;

/*********
 * F-Soft 基础实体操作方法
 * @package com.fsoft.core.common.base
 * @author Fish
 * @email it.fish2010@foxmail.com
 * @date 2019-11-29
 * @CopyRight © F-Soft
 * @param <T>
 **********/
public interface BaseMapper<T> {
	/**
	 * F-Soft 插入一条数据记录
	 * @author Fish(it.fish2010@foxmail.com)
	 * @date 2019-11-29
	 * @param entity
	 * @return
	 * @throws SQLException
	 */
	int insert(T entity) throws SQLException;

	/**
	 * F-Soft 更新一条数据记录
	 * @author Fish(it.fish2010@foxmail.com)
	 * @date 2019-11-29
	 * @param entity
	 * @return
	 * @throws SQLException
	 */
	int update(T entity) throws SQLException;

	/**
	 * F-Soft 根据主键ID，删除实体
	 * @author Fish(it.fish2010@foxmail.com)
	 * @date 2019-11-29
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	int delete(String id) throws SQLException;

	/**
	 * F-Soft 批量新增
	 * @author Fish(it.fish2010@foxmail.com)
	 * @date 2019-11-29
	 * @param list
	 * @return
	 * @throws SQLException
	 */
	int insertBatch(List<T> list) throws SQLException;

	/**
	 * F-Soft 批量更新
	 * @author Fish(it.fish2010@foxmail.com)
	 * @date 2019-11-29
	 * @param list
	 * @return
	 * @throws SQLException
	 */
	int updateBatch(List<T> list) throws SQLException;

	/**
	 * F-Soft 批量删除
	 * @author Fish(it.fish2010@foxmail.com)
	 * @date 2019-11-29
	 * @param list
	 * @return
	 * @throws SQLException
	 */
	int deleteBatch(List<String> list) throws SQLException;

	/**
	 * F-Soft 根据ID查询实体Bean
	 * @author Fish(it.fish2010@foxmail.com)
	 * @date 2019-11-29
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	T selectByKey(String id) throws SQLException;

	/**
	 * F-Soft 列表查询
	 * @author Fish(it.fish2010@foxmail.com)
	 * @date 2019-11-29
	 * @param param
	 * @return
	 * @throws SQLException
	 */
	List<T> selectList(QueryParam param) throws SQLException;
}
