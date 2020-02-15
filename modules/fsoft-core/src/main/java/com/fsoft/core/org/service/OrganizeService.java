package com.fsoft.core.org.service;

import com.fsoft.core.common.service.BaseService;
import com.fsoft.core.org.entity.SysOrganize;

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
}
