package com.fish.core.org.service;

import java.util.List;
import java.util.Map;

import com.fish.core.org.entity.Organize;

public interface OrganizeService {

	Organize queryObject(String orgId);

	List<Organize> queryList(Map<String, Object> map);

	int queryTotal(Map<String, Object> map);

	void save(Organize organize);

	void update(Organize organize);

	void delete(String orgId);

	void deleteBatch(String[] orgIds);

	void updateState(String[] ids, String stateValue);

	List<Organize> getList();

	Organize queryByOrgCode(String orgCode);
}
