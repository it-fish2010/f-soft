package com.fsoft.manager.area.entity;

import java.math.BigDecimal;

import org.springframework.stereotype.Repository;

import com.fsoft.core.common.base.BaseVo;

/**
 * @package com.fsoft.manager.area.entity
 * @author fish
 * @email it.fish2010@foxmail.com
 * @create 2019-10-09
 * @copyright 2009-2019
 */
@SuppressWarnings("serial")
@Repository
public class SysArea extends BaseVo {
	private String id;
	private String areaCode;
	private String areaName;
	private String areaNamePy;
	private String parentAreaId;
	private String parentAreaName;
	private String parentAreas;
	private BigDecimal sortNo;
	private Integer areaLevel;
	private Integer isCity;
	private String region;
	private Integer status;
	private String postCode;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaNamePy(String areaNamePy) {
		this.areaNamePy = areaNamePy;
	}

	public String getAreaNamePy() {
		return areaNamePy;
	}

	public void setParentAreaId(String parentAreaId) {
		this.parentAreaId = parentAreaId;
	}

	public String getParentAreaId() {
		return parentAreaId;
	}

	public String getParentAreaName() {
		return parentAreaName;
	}

	public void setParentAreaName(String parentAreaName) {
		this.parentAreaName = parentAreaName;
	}

	public void setParentAreas(String parentAreas) {
		this.parentAreas = parentAreas;
	}

	public String getParentAreas() {
		return parentAreas;
	}

	public void setSortNo(BigDecimal sortNo) {
		this.sortNo = sortNo;
	}

	public BigDecimal getSortNo() {
		return sortNo;
	}

	public void setAreaLevel(Integer areaLevel) {
		this.areaLevel = areaLevel;
	}

	public Integer getAreaLevel() {
		return areaLevel;
	}

	public void setIsCity(Integer isCity) {
		this.isCity = isCity;
	}

	public Integer getIsCity() {
		return isCity;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getRegion() {
		return region;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return status;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getPostCode() {
		return postCode;
	}
}
