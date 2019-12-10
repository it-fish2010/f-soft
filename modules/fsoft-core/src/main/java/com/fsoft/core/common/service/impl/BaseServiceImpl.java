package com.fsoft.core.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fsoft.core.common.QueryParam;
import com.fsoft.core.common.base.BaseMapper;
import com.fsoft.core.common.service.BaseService;

/*********
 * F-Soft
 * @package com.fsoft.core.common.service.impl
 * @author Fish
 * @email it.fish2010@foxmail.com
 * @date 2019-11-29
 * @CopyRight © F-Soft
 * @param <T>
 **********/
public class BaseServiceImpl<T> implements BaseService<T> {
	@Autowired
	private BaseMapper<T> mapper;

	@Override
	public T getEntity(String rwid) throws Exception {
		return mapper.selectByKey(rwid);
	}

	@Override
	public List<T> findList(QueryParam query) throws Exception {
		return mapper.selectList(query);
	}

	@Override
	public int save(T entity) throws Exception {
		return mapper.insert(entity);
	}

	@Override
	public int saveBatch(List<T> list) throws Exception {
		return mapper.insertBatch(list);
	}

	@Override
	public int modifyBatch(List<T> list) throws Exception {
		return mapper.updateBatch(list);
	}

	@Override
	public int modify(T entity) throws Exception {
		return mapper.update(entity);
	}

	@Override
	public int remove(String rwid) throws Exception {
		return mapper.delete(rwid);
	}

	@Override
	public int removeBatch(List<String> ids) throws Exception {
		return mapper.deleteBatch(ids);
	}

}
