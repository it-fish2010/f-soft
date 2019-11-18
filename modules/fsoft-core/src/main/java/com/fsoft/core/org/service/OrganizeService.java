package com.fsoft.core.org.service;

import java.util.List;

import com.fsoft.core.common.service.BaseService;
import com.fsoft.core.org.entity.SysOrganize;
import com.fsoft.core.utils.tree.Tree;

public interface OrganizeService extends BaseService<SysOrganize> {

	/**
	 * F-Soft 根据组织机构代码，查询组织机构信息
	 * @author Fish(it.fish2010@foxmail.com)
	 * @date 2019-11-15
	 * @param orgCode
	 * @return
	 * @throws Exception
	 */
	SysOrganize getOrgByCode(String orgCode) throws Exception;

	/****
	 * F-Soft 根据单位ID标识，查询以orgId作为树形根节点的所有单位信息
	 * @author Fish(it.fish2010@foxmail.com)
	 * @date 2019-11-16
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	List<Tree> findOrgTree(String orgId) throws Exception;
}
