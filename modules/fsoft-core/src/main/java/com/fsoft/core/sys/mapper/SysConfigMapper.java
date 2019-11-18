package com.fsoft.core.sys.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.fsoft.core.common.base.BaseMapper;
import com.fsoft.core.sys.entity.SysConfig;

/**
 * 系统配置信息
 * 
 * @author chenyi
 * @email 228112142@qq.com
 * @date 2016年12月4日 下午6:46:16
 */
@Repository
public interface SysConfigMapper extends BaseMapper<com.fsoft.core.sys.entity.SysConfig> {

	/**
	 * 根据key，查询value
	 */
	String queryByKey(String paramKey);

	/**
	 * 根据key，更新value
	 */
	int updateValueByKey(@Param("key") String key, @Param("value") String value);

	List<SysConfig> findRule(Map<String, Object> params);

	void setRule(SysConfig config);

	List<SysConfig> findByCode(String code);
}
