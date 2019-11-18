package com.fsoft.core.dict.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.fsoft.core.common.QueryParam;
import com.fsoft.core.common.base.BaseMapper;
import com.fsoft.core.dict.entity.SysDict;
import com.fsoft.core.dict.entity.SysDictItem;

/**
 * 字典表
 * 
 * @author Fish
 * @email it.fish2010@foxmail.com
 * @date 2019-03-25 08:13:58
 */
@Repository
public interface SysDictMapper extends BaseMapper<SysDict> {
	/***
	 * 根据配置项ID，获取自定明细配置项信息
	 * @user Fish
	 * @date 2019-06-21
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	SysDictItem selectItemByKey(String id) throws SQLException;

	/**
	 * 根据字典编码和配置项编码，获取指定的字典配置项
	 * @user Fish
	 * @date 2019-06-21
	 * @param dictCode
	 * @param itemValue
	 * @return
	 * @throws SQLException
	 */
	SysDictItem selectItem(@Param("dictCode") String dictCode, @Param("itemCode") String itemCode) throws SQLException;

	List<SysDictItem> selectItemList(QueryParam queryParam) throws SQLException;

	int selectItemListCount(QueryParam queryParamp) throws SQLException;

	int insertItem(SysDictItem itemEntity) throws SQLException;

	int updateItem(SysDictItem itemEntity) throws SQLException;

	/***
	 * 根据字典ID，删除字典明细配置记录
	 * @user Fish
	 * @date 2019-06-21
	 * @param dictId
	 * @return
	 * @throws SQLException
	 */
	int deleteItemByDictId(@Param("value") String dictId) throws SQLException;

	/***
	 * 根据字典编码，删除指定自定明细配置记录
	 * @user Fish
	 * @date 2019-06-21
	 * @param dictCode
	 * @return
	 * @throws SQLException
	 */
	int deleteItemByDictCode(@Param("value") String dictCode) throws SQLException;

	/***
	 * 删除具体的字典配置记录
	 * @user Fish
	 * @date 2019-06-21
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	int deleteItem(@Param("value") String id) throws SQLException;

	/***
	 * 批量字典配置记录
	 * @user Fish
	 * @date 2019-06-21
	 * @param list
	 * @return
	 * @throws SQLException
	 */
	int deleteItemBatch(List<String> list) throws SQLException;

}
