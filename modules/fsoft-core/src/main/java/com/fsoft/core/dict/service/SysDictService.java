package com.fsoft.core.dict.service;

import java.util.List;

import com.fsoft.core.common.QueryParam;
import com.fsoft.core.common.service.BaseService;
import com.fsoft.core.dict.entity.SysDict;
import com.fsoft.core.dict.entity.SysDictItem;
import com.fsoft.core.utils.tree.Tree;

/***
 * F-Soft 字典服务接口
 * @package com.fsoft.core.dict.service
 * @author Fish
 * @email it.fish2010@foxmail.com
 * @date 2020-02-15
 * @CopyRight © F-Soft
 *
 */
public interface SysDictService extends BaseService<SysDict> {
	/**
	 * 
	 * F-Soft
	 * @author it_software(it.fish2010@foxmail.com)
	 * @date 2020-02-15
	 * @param code
	 * @return
	 * @throws Exception
	 */
	SysDict getEntityByCode(String code) throws Exception;

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
	 * F-Soft 针对树形结构的字典，返回树形节点
	 * @author it_software(it.fish2010@foxmail.com)
	 * @date 2020-02-15
	 * @param param
	 * @return
	 * @throws Exception
	 */
	List<Tree> findDictItemTree(QueryParam param) throws Exception;

	SysDictItem getDictItem(String id) throws Exception;

	/***
	 * 根据字典编码(ID)，字典配置项值，获取指定的一条记录
	 * 
	 * @author Fish 2019-03-25
	 * @param dictVal
	 * @param itemCode
	 * @return
	 * @throws Exception
	 */
	SysDictItem getDictItem(String dictVal, String itemCode) throws Exception;

	int saveItem(SysDictItem entity) throws Exception;

	int modifyItem(SysDictItem entity) throws Exception;

	int removeItemById(String id) throws Exception;

	int removeItemByDictId(String dictCode) throws Exception;
}
