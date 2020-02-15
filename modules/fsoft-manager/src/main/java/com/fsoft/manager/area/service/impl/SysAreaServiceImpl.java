package com.fsoft.manager.area.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fsoft.core.common.QueryParam;
import com.fsoft.core.common.service.impl.BaseServiceImpl;
import com.fsoft.core.shiro.ShiroContext;
import com.fsoft.core.utils.DateTimeUtils;
import com.fsoft.core.utils.OgnlUtils;
import com.fsoft.core.utils.UUIDUtils;
import com.fsoft.core.utils.tree.BuildTree;
import com.fsoft.core.utils.tree.Tree;
import com.fsoft.manager.area.entity.SysArea;
import com.fsoft.manager.area.mapper.SysAreaMapper;
import com.fsoft.manager.area.service.SysAreaService;

/**
 * F-Soft 地区服务类
 * @package com.fsoft.manager.area.service.impl
 * @author Fish
 * @email it.fish2010@foxmail.com
 * @date 2020-02-16
 * @CopyRight © F-Soft
 **/
@Service("areaService")
@Transactional
public class SysAreaServiceImpl extends BaseServiceImpl<SysArea> implements SysAreaService {
	@Autowired
	private SysAreaMapper areaMapper;

	@Override
	public List<Tree> findTrees(QueryParam param) throws Exception {
		List<SysArea> list = findList(param);
		List<Tree> trees = new ArrayList<Tree>();
		for (SysArea area : list) {
			Tree node = new Tree();
			node.setId(area.getId());
			node.setCode(area.getCode());
			node.setTitle(area.getName());
			node.setParentId(area.getParentId());
			trees.add(node);
		}
		return BuildTree.buildJsonArray(trees);
	}

	@Override
	public int submitState(List<String> asList, Integer status) throws Exception {
		for (String id : asList) {
			SysArea entity = new SysArea();
			entity.setId(id);
			entity.setStatus(status);
			entity.setModifyTime(DateTimeUtils.getNowTime());
			entity.setModifyUserId(ShiroContext.getCurrUserId());
			areaMapper.update(entity);
		}
		return 0;
	}

	@Override
	public int save(SysArea param) throws Exception {
		if (StringUtils.isBlank(param.getId()))
			param.setId(UUIDUtils.randomUpperCaseId());
		if (OgnlUtils.isEmpty(param.getCreateTime()))
			param.setCreateTime(DateTimeUtils.getNowTime());
		// 维护Parents 字符串
		if (StringUtils.isBlank(param.getParentId())) {
			param.setParents(param.getId());
		} else {
			SysArea p_area = getEntity(param.getParentId());
			if (OgnlUtils.isNotEmpty(p_area) && StringUtils.isNotBlank(p_area.getParents()))
				param.setParents(p_area.getParents() + "_" + param.getId());
		}
		return super.save(param);
	}

	@Override
	public int modify(SysArea param) throws Exception {
		if (OgnlUtils.isEmpty(param.getModifyTime()))
			param.setModifyTime(DateTimeUtils.getNowTime());
		return super.modify(param);
	}

	@Override
	public int remove(String rwid) throws Exception {
		SysArea pm = new SysArea();
		pm.setParentId(rwid);
		List<SysArea> list = findList(new QueryParam(pm));
		if (OgnlUtils.isNotEmpty(list))
			throw new Exception("请不要删除非末级节点的地区");
		return super.remove(rwid);

	}

}
