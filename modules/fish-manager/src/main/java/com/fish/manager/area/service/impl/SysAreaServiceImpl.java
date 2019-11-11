package com.fish.manager.area.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fish.manager.area.entity.SysArea;
import com.fish.manager.area.mapper.SysAreaMapper;
import com.fish.manager.area.service.SysAreaService;
import com.fish.core.common.QueryParam;
import com.fish.core.common.service.impl.BaseServiceImpl;
import com.fish.core.utils.OgnlUtils;

@Service("areaService")
@Transactional
public class SysAreaServiceImpl extends BaseServiceImpl<SysArea> implements SysAreaService {
	@Autowired
	private SysAreaMapper areaMapper;

	@Override
	public int save(SysArea area) throws Exception {
		return areaMapper.insert(area);
	}

	@Override
	public int modify(SysArea area) throws Exception {
		return areaMapper.update(area);
	}

	/**
	 * @author chenjiabin
	 * @Description 根据分类ID中转成分类名称串
	 * @param
	 *
	 */
	@Override
	public String getAreaNameStr(String area_id_str) throws Exception {
		String area_name_str = "";
		if (StringUtils.isBlank(area_id_str)) {
			return area_name_str;
		}
		if (area_id_str.indexOf(",") > -1) {
			String[] menu_id = area_id_str.split(",");
			for (int i = 0; i < menu_id.length; i++) {
				area_name_str += getAreaName(menu_id[i]);
				if (i != menu_id.length - 1) {
					area_name_str += ",";
				}
			}
		} else {
			area_name_str += getAreaName(area_id_str);
		}
		return area_name_str;
	}

	/**
	 * @author chenjiabin
	 * @Description 根据菜单ID获取菜单名称
	 * @param
	 *
	 */
	private String getAreaName(String areaId) throws Exception {
		String areaName = "";
		SysArea area = getEntity(areaId);
		areaName = area.getName();
		return areaName;
	}

	@Override
	public int removeBatch(List<String> areaIds) throws Exception {
		for (String id : areaIds) {
			remove(id);
			// 删除下级地区
			removeChildrens(id);
		}
		return 0;
	}

	/***
	 * 删除下级
	 * 
	 * @param pId
	 * @throws Exception
	 */
	private void removeChildrens(String pId) throws Exception {
		List<SysArea> areaList = findByParentId(pId);
		if (OgnlUtils.isEmpty(areaList))
			return;
		List<String> ids = new ArrayList<String>();
		for (SysArea area : areaList) {
			ids.add(area.getId());
		}
		removeBatch(ids);
	}

	// 禁用或启用
	@Override
	public void submitState(List<String> ids, Integer stateValue) throws Exception {
		for (String id : ids) {
			SysArea area = getEntity(id);
			if (OgnlUtils.isEmpty(area))
				continue;
			if (area.getStatus().compareTo(stateValue) != 0) {
				SysArea modifyEntity = new SysArea();
				modifyEntity.setId(area.getId());
				modifyEntity.setCode(area.getCode());
				modifyEntity.setStatus(stateValue);
				modify(modifyEntity);
			}
			// 禁用或启用下级
			submitStateChildrens(id, stateValue);

		}
	}

	/***
	 * 禁用或启用下级
	 * 
	 * @param pId
	 * @param stateValue
	 * @throws Exception
	 */
	private void submitStateChildrens(String pId, Integer stateValue) throws Exception {
		List<SysArea> areaList = findByParentId(pId);
		if (OgnlUtils.isEmpty(areaList))
			return;
		List<String> ids = new ArrayList<String>();
		for (SysArea entity : areaList) {
			ids.add(entity.getId());
		}
		submitState(ids, stateValue);
	}

	@Override
	public List<SysArea> findByParentId(String pId) throws Exception {
		SysArea areaParam = new SysArea();
		areaParam.setParentAreaId(pId);
		QueryParam param = new QueryParam(areaParam);
		return findList(param);
	}

	@Override
	public List<SysArea> findAreaListByIsShow(String parentAreaId, Integer state) throws Exception {
		SysArea param = new SysArea();
		param.setParentAreaId(parentAreaId);
		param.setStatus(state);
		QueryParam query = new QueryParam(param);
		return findList(query);

	}
}
