package com.fsoft.core.dict.service;

import java.util.List;

import com.fsoft.core.common.QueryParam;
import com.fsoft.core.common.service.BaseService;
import com.fsoft.core.dict.entity.SysDict;
import com.fsoft.core.dict.entity.SysDictItem;

/**
 * 字典表
 * 
 * @author Fish
 * @email it.fish2010@foxmail.com
 * @date 2019-03-25 08:13:58
 */
public interface SysDictService extends BaseService<SysDict> {

	/***
	 * 根据字典编码，查询字典配置项明细
	 * 
	 * @author Fish 2019-03-25
	 * @param dictCode
	 * @return
	 * @throws Exception
	 */
	List<SysDictItem> findDictItems(String dictCode) throws Exception;

	/***
	 * 根据查询条件，查询字典配置项列表
	 * 
	 * @author Fish 2019-03-25
	 * @param params
	 * @return
	 * @throws Exception
	 */
	List<SysDictItem> findDictItems(QueryParam params) throws Exception;

	/***
	 * 根据查询条件，查询字典配置项列表(总数)
	 * 
	 * @author Fish 2019-03-25
	 * @param params
	 * @return
	 * @throws Exception
	 */
	int getDictItemTotal(QueryParam params) throws Exception;

	SysDictItem getDictItem(String id) throws Exception;

	/***
	 * 根据字典编码，字典配置项值，获取指定的一条记录
	 * 
	 * @author Fish 2019-03-25
	 * @param dictCode
	 * @param itemValue
	 * @return
	 * @throws Exception
	 */
	SysDictItem getDictItem(String dictCode, String itemValue) throws Exception;

	int saveItem(SysDictItem entity) throws Exception;

	int modifyItem(SysDictItem entity) throws Exception;

	int removeItemById(String id) throws Exception;

	int removeItemByDictId(String dictCode) throws Exception;
}
