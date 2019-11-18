package com.fsoft.core.utils.tree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;

/**
 * @package com.fsoft.core.utils.tree
 * @author Fish
 * @email it.fish2010@foxmail.com
 * @create 2019-05-09
 * @copyright 2009-2019
 */
public class Tree implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String code;
	private String title;
	private String field;
	private boolean spread = true;
	private boolean checked = false;
	private boolean disabled = false;
	private String href;
	/**
	 * 节点的子节点
	 */
	private List<Tree> children = new ArrayList<Tree>();
	/**
	 * 父ID
	 */
	private String parentId;
	/***
	 * 菜单扩展信息
	 */
	private String perms;
	/**
	 * 菜单类型，0：目录，1：菜单，2：按钮，3：其他
	 */
	private Integer menuType;
	/**
	 * 图标
	 */
	private String icon;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public boolean isSpread() {
		return spread;
	}

	public void setSpread(boolean spread) {
		this.spread = spread;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public List<Tree> getChildren() {
		return children;
	}

	public void setChildren(List<Tree> children) {
		this.children = children;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
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

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
}
