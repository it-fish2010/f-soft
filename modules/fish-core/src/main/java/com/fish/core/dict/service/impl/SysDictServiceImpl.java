package com.fish.core.dict.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fish.core.common.QueryParam;
import com.fish.core.common.exceptions.RRException;
import com.fish.core.common.service.impl.BaseServiceImpl;
import com.fish.core.dict.entity.SysDict;
import com.fish.core.dict.entity.SysDictItem;
import com.fish.core.dict.mapper.SysDictMapper;
import com.fish.core.dict.service.SysDictService;
import com.fish.core.utils.DateTimeUtils;
import com.fish.core.utils.OgnlUtils;
import com.fish.core.utils.UUIDUtils;

/**
 * 数据字典管理服务
 * 
 * @author Fish
 * @email it.fish2010@foxmail.com
 * @create 2019-03-25
 * @copyright 佳乐软件股份有限公司© 2019-2019
 *
 */
@Transactional
@Service("sysDictService")
public class SysDictServiceImpl extends BaseServiceImpl<SysDict> implements SysDictService {
	@Autowired
	private SysDictMapper sysDictMapper;
	private static final Map<String, List<SysDictItem>> itemMap = new HashMap<String, List<SysDictItem>>();
	private static final Map<String, SysDict> dictMap = new HashMap<String, SysDict>();

	@Override
	public SysDict getEntity(String dictCode) throws Exception {
		if (!dictMap.containsKey(dictCode)) {
			SysDict dict = sysDictMapper.selectByKey(dictCode);
			dictMap.put(dict.getCode(), dict);
		}
		return dictMap.get(dictCode);
	}

	@Override
	public List<SysDictItem> findDictItems(String dictCode) throws Exception {
		if (!itemMap.containsKey(dictCode)) {
			SysDictItem item = new SysDictItem();
			item.setDictCode(dictCode);
			QueryParam params = new QueryParam(item);
			List<SysDictItem> items = findDictItems(params);
			if (OgnlUtils.isNotEmpty(items))
				itemMap.put(dictCode, items);
		}
		return itemMap.get(dictCode);
	}

	@Override
	public List<SysDictItem> findDictItems(QueryParam params) throws Exception {
		return sysDictMapper.selectItemList(params);
	}

	@Override
	public SysDictItem getDictItem(String id) throws Exception {
		return sysDictMapper.selectItemByKey(id);
	}

	@Override
	public SysDictItem getDictItem(String dictCode, String itemCode) throws Exception {
		List<SysDictItem> items = findDictItems(dictCode);
		if (OgnlUtils.isEmpty(items))
			return null;
		for (SysDictItem item : items) {
			if (StringUtils.equals(item.getCode(), itemCode))
				return item;
		}
		return sysDictMapper.selectItem(dictCode, itemCode);
	}

	@Override
	public int saveItem(SysDictItem entity) throws Exception {
		if (StringUtils.isBlank(entity.getId()))
			entity.setId(UUIDUtils.randomUpperCaseId());
		if (OgnlUtils.isEmpty(entity.getCreateTime()))
			entity.setCreateTime(DateTimeUtils.getNowTime());
		return sysDictMapper.insertItem(entity);
	}

	@Override
	public int modifyItem(SysDictItem entity) throws Exception {
		if (StringUtils.isBlank(entity.getId()))
			throw new RRException("字典配置项标识为空!");
		if (StringUtils.isBlank(entity.getDictId()))
			throw new RRException("字典标识为空!");
		if (OgnlUtils.isEmpty(entity.getModifyTime()))
			entity.setModifyTime(DateTimeUtils.getNowTime());
		return sysDictMapper.updateItem(entity);
	}

	@Override
	public int removeItemById(String id) throws Exception {
		return sysDictMapper.deleteItem(id);
	}

	@Override
	public int removeItemByDictId(String dictId) throws Exception {
		return sysDictMapper.deleteItemByDictId(dictId);
	}

	@Override
	public int getDictItemTotal(QueryParam params) throws Exception {
		return sysDictMapper.selectItemListCount(params);
	}

}
