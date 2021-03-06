package com.fsoft.core.org.service.impl;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fsoft.core.Global;
import com.fsoft.core.common.service.impl.BaseServiceImpl;
import com.fsoft.core.org.entity.SysOrganize;
import com.fsoft.core.org.mapper.SysOrganizeMapper;
import com.fsoft.core.org.service.OrganizeService;
import com.fsoft.core.utils.OgnlUtils;
import com.fsoft.core.utils.UUIDUtils;

/**
 * F-Soft
 * 
 * @package com.fsoft.core.org.service.impl
 * @author Fish
 * @email it.fish2010@foxmail.com
 * @date 2019-11-16
 * @CopyRight © F-Soft
 **/
@Service("organizeService")
@Transactional
public class OrganizeServiceImpl extends BaseServiceImpl<SysOrganize> implements OrganizeService {
	@Autowired
	private SysOrganizeMapper orgMapper;

	@Override
	public SysOrganize getOrgByCode(String orgCode) throws Exception {
		return orgMapper.selectByCode(orgCode);
	}

	@Override
	public int save(SysOrganize param) throws Exception {
		if (StringUtils.isBlank(param.getId()))
			param.setId(UUIDUtils.randomUpperCaseId());
		if (OgnlUtils.isEmpty(param.getSortNo()))
			param.setSortNo(BigDecimal.ONE);
		param.setStatus(Global.STATUS_YES);
		param.setParents(param.getId());
		if (StringUtils.isNotBlank(param.getParentId())) {
			SysOrganize pOrg = getEntity(param.getParentId());
			if (OgnlUtils.isNotEmpty(pOrg)) {
				param.setParents(pOrg.getParents() + "_" + param.getId());
			}
		}
		return orgMapper.insert(param);
	}

	@Override
	public int modify(SysOrganize param) throws Exception {
		SysOrganize entity = getEntity(param.getId());
		if (OgnlUtils.isEmpty(entity))
			throw new Exception("无法识别的单位标识[" + param.getId() + "]");
		// 如果上级节点有调整
		if (!StringUtils.equals(param.getParentId(), entity.getParentId())) {
			param.setParents(param.getId());
			if (StringUtils.isNotBlank(param.getParentId())) {
				SysOrganize pOrg = getEntity(param.getParentId());
				if (OgnlUtils.isNotEmpty(pOrg)) {
					param.setParents(pOrg.getParents() + "_" + param.getId());
				}
			}
		}
		return orgMapper.update(param);
	}

}
