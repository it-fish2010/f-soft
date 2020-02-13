package com.fsoft.manager.role.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fsoft.core.Global;
import com.fsoft.core.common.service.impl.BaseServiceImpl;
import com.fsoft.core.shiro.ShiroContext;
import com.fsoft.core.utils.DateTimeUtils;
import com.fsoft.core.utils.OgnlUtils;
import com.fsoft.core.utils.UUIDUtils;
import com.fsoft.manager.role.entity.SysRole;
import com.fsoft.manager.role.mapper.SysRoleMapper;
import com.fsoft.manager.role.service.SysRoleService;
import com.fsoft.manager.user.entity.SysUserRole;

@Transactional
@Service("sysRoleService")
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole> implements SysRoleService {

	@Autowired
	private SysRoleMapper roleMapper;

	@Override
	public SysRole getEntity(String rwid) throws Exception {
		return roleMapper.selectByKey(rwid);
	}

	@Override
	public int save(SysRole entity) throws Exception {
		if (StringUtils.isBlank(entity.getId()))
			entity.setId(UUIDUtils.randomUpperCaseId());
		if (OgnlUtils.isEmpty(entity.getCreateTime()))
			entity.setCreateTime(DateTimeUtils.getNowTime());
		if (OgnlUtils.isEmpty(entity.getIsSystem()))
			entity.setIsSystem(Global.STATUS_NO);
		if (OgnlUtils.isEmpty(entity.getStatus()))
			entity.setStatus(Global.STATUS_YES);
		// 默认为超级管理员分配所有角色(业务规则：只有当自己有对应的角色的时候，才允许把角色分配给其他人)
		SysUserRole ur = new SysUserRole();
		ur.setId(UUIDUtils.randomUpperCaseId());
		ur.setUserId(Global.SYS_USER_ADMIN_ID); // 超级管理员的用户ID
		ur.setRoleId(entity.getId());
		roleMapper.insertUserRole(ur);
		return roleMapper.insert(entity);
	}

	@Override
	public int modify(SysRole entity) throws Exception {
		if (OgnlUtils.isEmpty(entity.getModifyUserId()))
			entity.setModifyUserId(ShiroContext.getCurrUserId());
		if (OgnlUtils.isEmpty(entity.getModifyTime()))
			entity.setModifyTime(DateTimeUtils.getNowTime());
		//如果菜单标识不为空,重新授权
		if (OgnlUtils.isNotEmpty(entity.getMenus())) {
			roleMapper.deleteRoleMenu(entity.getId());
			roleMapper.insertRoleMenuBatch(entity); //批量分配角色ID与菜单ID的关联关系记录
		}
		return roleMapper.update(entity);
	}

	@Override
	public List<SysRole> findRoleListByUserId(String userid) throws Exception {
		return roleMapper.selectRoleListByUserId(userid);
	}

	@Override
	public int remove(String rwid) throws Exception {
		SysRole sr = getEntity(rwid);
		if (OgnlUtils.isEmpty(sr))
			throw new Exception("角色信息不存在，请刷新后重试");
		if (Global.STATUS_YES.compareTo(sr.getIsSystem()) == 0)
			throw new Exception("内置角色(" + sr.getName() + ")不允许删除!");
		roleMapper.deleteUserRole(rwid); // 删除角色与用户关系
		return super.remove(rwid);
	}

	@Override
	public int removeBatch(List<String> ids) throws Exception {
		int m = 0;
		for (String id : ids) {
			int k = remove(id);
			m += k;
		}
		return m;
	}

	@Override
	public SysRole getRoleByCode(String roleCode) throws Exception {
		return roleMapper.selectByCode(roleCode);
	}
}
