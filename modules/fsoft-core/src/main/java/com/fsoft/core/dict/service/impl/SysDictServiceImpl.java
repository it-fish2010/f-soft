
package com.fsoft.core.dict.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fsoft.core.common.QueryParam;
import com.fsoft.core.common.exceptions.RRException;
import com.fsoft.core.common.service.impl.BaseServiceImpl;
import com.fsoft.core.dict.entity.SysDict;
import com.fsoft.core.dict.entity.SysDictItem;
import com.fsoft.core.dict.mapper.SysDictMapper;
import com.fsoft.core.dict.service.SysDictService;
import com.fsoft.core.utils.DateTimeUtils;
import com.fsoft.core.utils.OgnlUtils;
import com.fsoft.core.utils.UUIDUtils;
import com.fsoft.core.utils.tree.BuildTree;
import com.fsoft.core.utils.tree.Tree;

/**
 * F-Soft 数据字典管理服务<br>
 * TODO 考虑引入缓存，避免每次查询数据库
 * @package com.fsoft.core.dict.service.impl
 * @author Fish
 * @email it.fish2010@foxmail.com
 * @date 2020-02-15
 * @CopyRight © F-Soft
 **/
@Transactional
@Service("sysDictService")
public class SysDictServiceImpl extends BaseServiceImpl<SysDict> implements SysDictService {
	@Autowired
	private SysDictMapper dictMapper;

	@Override
	public SysDict getEntityByCode(String code) throws Exception {
		SysDict dict = new SysDict();
		dict.setCode(code);
		QueryParam query = new QueryParam(dict);
		List<SysDict> dicts = findList(query);
		return OgnlUtils.isEmpty(dicts) ? null : dicts.get(0);
	}

	@Override
	public int save(SysDict param) throws Exception {
		if (StringUtils.isBlank(param.getId()))
			param.setId(UUIDUtils.randomUpperCaseId());
		SysDict entity = getEntityByCode(param.getCode());
		if (OgnlUtils.isNotEmpty(entity))
			throw new Exception("字典编码[" + param.getCode() + "]已存在！");
		return super.save(param);
	}

	@Override
	public int modify(SysDict param) throws Exception {
		if (OgnlUtils.isEmpty(param.getModifyTime()))
			param.setModifyTime(DateTimeUtils.getNowTime());
		SysDict entity = getEntityByCode(param.getCode());
		if (OgnlUtils.isNotEmpty(entity) && !StringUtils.equals(entity.getId(), param.getId()))
			throw new Exception("字典编码[" + param.getCode() + "]已被字典[" + entity.getName() + "]使用!");
		return super.modify(param);
	}

	/**
	 * 删除字典操作，需要级联删除字典配置项
	 */
	@Override
	public int remove(String rwid) throws Exception {
		removeItemByDictId(rwid);
		return super.remove(rwid);
	}

	@Override
	public int removeBatch(List<String> ids) throws Exception {
		if (OgnlUtils.isNotEmpty(ids) && ids.size() == 1)
			return remove(ids.get(0));
		// 批量删除
		dictMapper.deleteItemBatchByDictId(ids);
		return super.removeBatch(ids);
	}

	@Override
	public SysDict getEntity(String id) throws Exception {
		return dictMapper.selectByKey(id);
	}

	@Override
	public List<SysDictItem> findDictItems(String dictCode) throws Exception {
		SysDictItem item = new SysDictItem();
		item.setDictCode(dictCode);
		QueryParam params = new QueryParam(item);
		return findDictItems(params);
	}

	@Override
	public List<Tree> findDictItemTree(QueryParam param) throws Exception {
		List<SysDictItem> list = findDictItems(param);
		return BuildTree.buildTree(list);
	}

	@Override
	public List<SysDictItem> findDictItems(QueryParam params) throws Exception {
		return dictMapper.selectItemList(params);
	}

	@Override
	public SysDictItem getDictItem(String id) throws Exception {
		return dictMapper.selectItemByKey(id);
	}

	@Override
	public SysDictItem getDictItem(String dictCode, String itemCode) throws Exception {
		SysDictItem item = new SysDictItem();
		item.setDictCode(dictCode);
		item.setCode(itemCode);
		QueryParam params = new QueryParam(item);
		List<SysDictItem> items = findDictItems(params);
		return OgnlUtils.isEmpty(items) ? null : items.get(0);
	}

	@Override
	public int saveItem(SysDictItem entity) throws Exception {
		if (StringUtils.isBlank(entity.getId()))
			entity.setId(UUIDUtils.randomUpperCaseId());
		if (OgnlUtils.isEmpty(entity.getCreateTime()))
			entity.setCreateTime(DateTimeUtils.getNowTime());
		if (StringUtils.isBlank(entity.getParentId()))
			entity.setParents(entity.getId());
		else {
			SysDictItem p_item = getDictItem(entity.getParentId());
			entity.setParents(p_item.getParents() + "_" + entity.getId());
		}
		return dictMapper.insertItem(entity);
	}

	@Override
	public int modifyItem(SysDictItem entity) throws Exception {
		if (StringUtils.isBlank(entity.getId()))
			throw new RRException("字典配置项标识为空!");
		if (StringUtils.isBlank(entity.getDictId()))
			throw new RRException("字典标识为空!");
		if (OgnlUtils.isEmpty(entity.getModifyTime()))
			entity.setModifyTime(DateTimeUtils.getNowTime());
		return dictMapper.updateItem(entity);
	}

	@Override
	public int removeItemById(String id) throws Exception {
		return dictMapper.deleteItem(id);
	}

	@Override
	public int removeItemByDictId(String dictId) throws Exception {
		return dictMapper.deleteItemByDictId(dictId);
	}
}
