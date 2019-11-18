package com.fsoft.manager.role.mapper;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.fsoft.core.common.base.BaseMapper;
import com.fsoft.manager.role.entity.SysRole;
import com.fsoft.manager.user.entity.SysUserRole;

/**
 * @package com.fsoft.manager.role.mapper
 * @author Fish
 * @email it.fish2010@foxmail.com
 * @create 2019-05-08
 * @copyright 2009-2019
 */
@Repository
public interface SysRoleMapper extends BaseMapper<SysRole> {

	/**
	 * @des 根据用户ID查询（获取用户的角色列表）
	 * @author Fish it.fish2010@foxmail.com
	 * @date 2019-11-01
	 * @param value
	 * @return
	 * @throws SQLException
	 */
	List<SysRole> selectRoleListByUserId(String value) throws SQLException;

	/***
	 * 根据角色编码查询
	 * @user Fish
	 * @date 2019-05-08
	 * @param rwid
	 * @return
	 * @throws SQLException
	 */
	SysRole selectByCode(String rwid) throws SQLException;

	/***
	 * @des 新增用户与角色关系
	 * @author Fish it.fish2010@foxmail.com
	 * @date 2019-11-01
	 * @param userRole
	 * @return
	 * @throws SQLException
	 */
	int insertUserRole(SysUserRole userRole) throws SQLException;

	/***
	 * @des 新增用户与角色关系（批量）
	 * @author Fish it.fish2010@foxmail.com
	 * @date 2019-11-01
	 * @param userRole
	 * @return
	 * @throws SQLException
	 */
	int batchInsertUserRole(List<SysUserRole> list) throws SQLException;

	/***
	 * @des 根据角色ID，删除用于与角色关系(一般在删除角色的时候调用)
	 * @author Fish it.fish2010@foxmail.com
	 * @date 2019-11-01
	 * @param value
	 * @return
	 * @throws SQLException
	 */
	int deleteUserRole(String value) throws SQLException;

	/***
	 * F-Soft 根据角色RWID标识，删除角色与菜单的依赖关系记录
	 * @author Fish(it.fish2010@foxmail.com)
	 * @date 2019-11-05
	 * @param rwid
	 * @return
	 * @throws SQLException
	 */
	int deleteRoleMenu(String rwid) throws SQLException;

	/**
	 * F-Soft 批量分配角色与菜单的关联关系
	 * @author Fish(it.fish2010@foxmail.com)
	 * @date 2019-11-05
	 * @param role
	 * @return
	 * @throws SQLException
	 */
	int insertRoleMenuBatch(SysRole role) throws SQLException;
}
