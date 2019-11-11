package com.fish.core.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fish.core.common.QueryParam;
import com.fish.core.common.base.BaseMapper;
import com.fish.core.common.service.BaseService;

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
