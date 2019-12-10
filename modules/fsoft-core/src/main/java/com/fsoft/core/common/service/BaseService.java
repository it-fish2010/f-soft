package com.fsoft.core.common.service;

import java.util.List;

import com.fsoft.core.common.QueryParam;

/*********
 * F-Soft 基础服务接口
 * @package com.fsoft.core.common.service
 * @author Fish
 * @email it.fish2010@foxmail.com
 * @date 2019-11-29
 * @CopyRight © F-Soft
 * @param <T>
 **********/
public interface BaseService<T> {
	/**
	 * F-Soft 保存/新增
	 * @author Fish(it.fish2010@foxmail.com)
	 * @date 2019-11-29
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	int save(T entity) throws Exception;

	/**
	 * F-Soft 批量新增/保存
	 * @author Fish(it.fish2010@foxmail.com)
	 * @date 2019-11-29
	 * @param list
	 * @return
	 * @throws Exception
	 */
	int saveBatch(List<T> list) throws Exception;

	/**
	 * F-Soft 编辑/修改
	 * @author Fish(it.fish2010@foxmail.com)
	 * @date 2019-11-29
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	int modify(T entity) throws Exception;

	/**
	 * F-Soft 批量编辑/修改
	 * @author Fish(it.fish2010@foxmail.com)
	 * @date 2019-11-29
	 * @param list
	 * @return
	 * @throws Exception
	 */
	int modifyBatch(List<T> list) throws Exception;

	/**
	 * F-Soft 根据id标识删除
	 * @author Fish(it.fish2010@foxmail.com)
	 * @date 2019-11-29
	 * @param id
	 * @return
	 * @throws Exception
	 */
	int remove(String id) throws Exception;

	/**
	 * F-Soft 批量删除
	 * @author Fish(it.fish2010@foxmail.com)
	 * @date 2019-11-29
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	int removeBatch(List<String> ids) throws Exception;

	/**
	 * F-Soft 根据主键ID获取实体
	 * @author Fish(it.fish2010@foxmail.com)
	 * @date 2019-11-29
	 * @param id
	 * @return
	 * @throws Exception
	 */
	T getEntity(String id) throws Exception;

	/**
	 * F-Soft 列表查询，默认可以不需要传入分页
	 * @author Fish(it.fish2010@foxmail.com)
	 * @date 2019-11-29
	 * @param query
	 * @return
	 * @throws Exception
	 */
	List<T> findList(QueryParam query) throws Exception;
}
