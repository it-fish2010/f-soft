package com.fish.core.org.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fish.core.org.entity.Organize;

@Repository
public interface OrganizeMapper {
	void save(Organize t);

	void save(Map<String, Object> map);

	void saveBatch(List<Organize> list);

	int update(Organize t);

	int update(Map<String, Object> map);

	int delete(Object id);

	int delete(Map<String, Object> map);

	int deleteBatch(Object[] id);

	Organize queryObject(Object id);

	Organize get(Object id);

	List<Organize> queryList(Map<String, Object> map);

	List<Organize> getList(Map<String, Object> map);

	List<Organize> queryList(Object id);

	int queryTotal(Map<String, Object> map);

	int getCount(Map<String, Object> map);

	int queryTotal();

	List<Organize> getList();

	List<Organize> queryByParentId(String orgId);

	Organize queryByOrgCode(String orgCode);
}
