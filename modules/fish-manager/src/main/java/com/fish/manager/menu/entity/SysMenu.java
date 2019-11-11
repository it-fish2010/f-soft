package com.fish.manager.menu.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.fish.core.common.base.BaseVo;

/**
 * F-Soft 菜单目录实体Bean
 * @package com.fish.manager.menu.entity
 * @author Fish
 * @email it.fish2010@foxmail.com
 * @date 2019-11-02
 * @CopyRight © F-Soft
 **/
@SuppressWarnings("serial")
public class SysMenu extends BaseVo {
	private String parentId;
	private String parentName;
	/**
	 * F-Soft 如果是”菜单“，actionUrl不允许为空
	 */
	private String actionUrl;
	/**
	 * F-Soft 权限标识
	 */
	private String perms;
	/**
	 * F-Soft 菜单类型，0：目录，1：菜单，2：按钮，3：其他
	 */
	private Integer menuType;
	/**
	 * F-Soft 排序字段
	 */
	private String icon;
	/**
	 * F-Soft 排序字段
	 */
	private BigDecimal sortNo;
	/***
	 * F-Soft 下级目录/菜单
	 */
	private List<SysMenu> childrens = new ArrayList<SysMenu>();

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getActionUrl() {
		return actionUrl;
	}

	public void setActionUrl(String actionUrl) {
		this.actionUrl = actionUrl;
	}

	public String getPerms() {
		return perms;
	}

	public void setPerms(String perms) {
		this.perms = perms;
	}

	public Integer getMenuType() {
		return menuType;
	}

	public void setMenuType(Integer menuType) {
		this.menuType = menuType;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public BigDecimal getSortNo() {
		return sortNo;
	}

	public void setSortNo(BigDecimal sortNo) {
		this.sortNo = sortNo;
	}

	public List<SysMenu> getChildrens() {
		return childrens;
	}

	public void setChildrens(List<SysMenu> childrens) {
		for (SysMenu m : childrens) {
			if (StringUtils.isNotBlank(m.getParentId()) && m.getParentId().equals(getId()))
				this.childrens.add(m);
		}
	}
}
