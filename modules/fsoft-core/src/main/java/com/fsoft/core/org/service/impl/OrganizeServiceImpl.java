package com.fsoft.core.org.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fsoft.core.common.QueryParam;
import com.fsoft.core.common.service.impl.BaseServiceImpl;
import com.fsoft.core.org.entity.SysOrganize;
import com.fsoft.core.org.mapper.SysOrganizeMapper;
import com.fsoft.core.org.service.OrganizeService;
import com.fsoft.core.utils.tree.Tree;

/**
 * F-Soft
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
	public List<Tree> findOrgTree(String orgId) throws Exception {
		QueryParam param = new QueryParam();
		param.put("parentOrgs", orgId);
		List<SysOrganize> list = findList(param);
		List<Tree> trees = new ArrayList<Tree>();
		for (SysOrganize org : list) {
			Tree tree = new Tree();
			tree.setId(org.getRwid());
			tree.setField(org.getOrgCode());
			tree.setTitle(org.getOrgName());
			tree.setCode(org.getOrgCode());
			tree.setParentId(org.getParentOrgId());
			trees.add(tree);
		}
		return trees;
	}

}
