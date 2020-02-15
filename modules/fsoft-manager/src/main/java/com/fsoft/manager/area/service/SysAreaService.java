package com.fsoft.manager.area.service;

import java.util.List;

import com.fsoft.core.common.QueryParam;
import com.fsoft.core.common.service.BaseService;
import com.fsoft.core.utils.tree.Tree;
import com.fsoft.manager.area.entity.SysArea;

public interface SysAreaService extends BaseService<SysArea> {

	/***
	 * 
	 * F-Soft 地区查询，返回树形结构
	 * @author it_software(it.fish2010@foxmail.com)
	 * @date 2020-02-15
	 * @param param
	 * @return
	 * @throws Exception
	 */
	List<Tree> findTrees(QueryParam param) throws Exception;

	/***
	 * F-Soft 编辑地区状态
	 * @author it_software(it.fish2010@foxmail.com)
	 * @date 2020-02-15
	 * @param params
	 * @param status 状态，1：启用，0：禁用
	 * @return
	 */
	int submitState(List<String> params, Integer status) throws Exception;
}
