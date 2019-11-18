package com.fsoft.manager.user.mapper;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.fsoft.core.common.base.BaseMapper;
import com.fsoft.manager.user.entity.SysUser;

/**
 * @author Fish
 * @email it.fish2010@foxmail.com
 * @create 2019-03-25
 * @copyright 佳乐软件股份有限公司© 2019-2019
 */
@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {

	/***
	 * 删除用户关联的角色信息
	 * @user Fish
	 * @date 2019-05-09
	 * @param userids
	 * @return
	 * @throws SQLException
	 */
	int deleteUserRoleBatch(List<String> userids) throws SQLException;

	/**
	 * @des 根据用户的ID标识，删除用户的角色关系
	 * @author Fish it.fish2010@foxmail.com
	 * @date 2019-11-01
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
	int deleteUserRole(String userId) throws SQLException;

	/***
	 * @des 添加用户关联的角色,由Service层控制，如果SysUser.roleIdList属性不为空，可调用当前方法
	 * @user fish(it.fish2010@foxmail.com)
	 * @date 2019-10-09
	 * @param user
	 * @return
	 * @throws SQLException
	 */
	int insertBatchUserRole(SysUser user) throws SQLException;

	/***
	 * 根据用户ID，获取用户已有的权限（目录、菜单标识）
	 * @author Fish 2019-03-25
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
	List<String> selectUserMenuList(String userId) throws SQLException;

	/***
	 * 根据登录名查找账号信息
	 * @user Fish
	 * @date 2019-05-08
	 * @param loginAcct
	 * @return
	 * @throws SQLException
	 */
	SysUser selectByLoginAcct(String loginAcct) throws SQLException;

	/**
	 * 用户加锁/解锁
	 * @user fish(it.fish2010@foxmail.com)
	 * @date 2019-10-09
	 * @param user
	 * @return
	 * @throws SQLException
	 */
	int updateUserLock(SysUser user) throws SQLException;

	/****
	 * F-Soft 按单位统计用户数据，账号总数，启用账号数量,
	 * @author Fish(it.fish2010@foxmail.com)
	 * @date 2019-11-16
	 * @param orgid
	 * @return
	 * @throws SQLException
	 */
	Map<String, BigDecimal> selectSum(@Param("orgId") String orgId) throws SQLException;
}
