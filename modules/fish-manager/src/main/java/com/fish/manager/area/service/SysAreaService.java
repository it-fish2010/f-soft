package com.fish.manager.area.service;

import java.util.List;

import com.fish.manager.area.entity.SysArea;
import com.fish.core.common.service.BaseService;

public interface SysAreaService extends BaseService<SysArea> {

	/***
	 * 获取下级地区(直接下级)
	 * 
	 * @param parentAreaId
	 * @param state
	 * @return
	 * @throws Exception
	 */
	List<SysArea> findAreaListByIsShow(String parentAreaId, Integer state) throws Exception;

	/***
	 * 获取全部下级
	 * 
	 * @param pId
	 * @return
	 * @throws Exception
	 */
	List<SysArea> findByParentId(String pId) throws Exception;

	void submitState(List<String> ids, Integer state) throws Exception;

	String getAreaNameStr(String area_id_str) throws Exception;
}
