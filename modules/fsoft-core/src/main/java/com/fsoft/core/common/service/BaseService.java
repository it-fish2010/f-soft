package com.fsoft.core.common.service;

import java.util.List;

import com.fsoft.core.common.QueryParam;

public interface BaseService<T> {
	/**
	 * 保存/新增
	 * @user Fish
	 * @date 2019-05-08
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	int save(T entity) throws Exception;

	/***
	 * 编辑/修改
	 * @user Fish
	 * @date 2019-05-08
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	int modify(T entity) throws Exception;

	/**
	 * 删除
	 * @user Fish
	 * @date 2019-05-08
	 * @param id
	 * @return
	 * @throws Exception
	 */
	int remove(String id) throws Exception;

	/**
	 * 批量删除
	 * @user Fish
	 * @date 2019-05-08
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	int removeBatch(List<String> ids) throws Exception;

	/***
	 * 根据主键ID获取实体
	 * 
	 * @param rwid
	 * @return
	 * @throws Exception
	 */
	T getEntity(String id) throws Exception;

	/**
	 * 列表查询，默认可以不需要传入分页
	 * 
	 * @param query
	 * @return
	 * @throws Exception
	 */
	List<T> findList(QueryParam query) throws Exception;
}
