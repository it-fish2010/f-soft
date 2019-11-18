package com.fsoft.manager.menu.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.fsoft.core.common.base.BaseMapper;
import com.fsoft.manager.menu.entity.SysMenu;

/**
 * 菜单
 * @author Fish
 * @email it.fish2010@foxmail.com
 * @date 2019-03-22 22:52:55
 */
@Repository
public interface SysMenuMapper extends BaseMapper<SysMenu> {

	/**
	 * F-Soft 根据菜单标识，取消所有角色的菜单权限（菜单已不存在，授权记录应清理）
	 * @author Fish(it.fish2010@foxmail.com)
	 * @date 2019-11-02
	 * @param menuId
	 * @return
	 * @throws SQLException *
	 */
	int deleteMenuRole(@Param("menuId") String menuId) throws SQLException;

	/***
	 * F-Soft 批量删除角色与菜单的关联关系（批量删除菜单的时候调用）
	 * @author Fish(it.fish2010@foxmail.com)
	 * @date 2019-11-02
	 * @param list
	 * @return
	 * @throws SQLException
	 **/
	int deleteMenuRoleBatch(List<String> list) throws SQLException;

	/****
	 * F-Soft 插入角色与菜单的关联关系记录
	 * @author Fish(it.fish2010@foxmail.com)
	 * @date 2019-11-02
	 * @param id
	 * @param menuId
	 * @param roleId
	 * @return
	 * @throws SQLException
	 **/
	int insertMenuRole(@Param("id") String id, @Param("menuId") String menuId, @Param("roleId") String roleId) throws SQLException;

}
