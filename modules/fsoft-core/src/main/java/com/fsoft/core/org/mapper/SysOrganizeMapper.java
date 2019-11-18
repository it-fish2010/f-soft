package com.fsoft.core.org.mapper;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.fsoft.core.common.base.BaseMapper;
import com.fsoft.core.org.entity.SysOrganize;

/**
 * F-Soft 组织机构
 * @package com.fsoft.core.org.mapper
 * @author Fish
 * @email it.fish2010@foxmail.com
 * @date 2019-11-15
 * @CopyRight © F-Soft
 **/
@Repository
public interface SysOrganizeMapper extends BaseMapper<SysOrganize> {
	/**
	 * F-Soft 根据组织机构代码，查询组织机构
	 * @author Fish(it.fish2010@foxmail.com)
	 * @date 2019-11-15
	 * @param orgCode
	 * @return
	 * @throws SQLException
	 */
	SysOrganize selectByCode(String orgCode) throws SQLException;
}
