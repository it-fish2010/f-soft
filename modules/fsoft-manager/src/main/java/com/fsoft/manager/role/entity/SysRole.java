package com.fsoft.manager.role.entity;

import java.util.ArrayList;
import java.util.List;

import com.fsoft.core.common.base.BaseVo;

/**
 * @package com.fsoft.manager.role.entity
 * @author fish
 * @email it_Fish@aliyun.com
 * @create 2019-10-09
 * @copyright 2009-2019
 */
@SuppressWarnings("serial")
public class SysRole extends BaseVo {
	private String remark;
	private Integer status;
	private Integer isSystem;

	private List<String> menus = new ArrayList<String>();

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIsSystem() {
		return isSystem;
	}

	public void setIsSystem(Integer isSystem) {
		this.isSystem = isSystem;
	}

	public List<String> getMenus() {
		return menus;
	}

	public void setMenus(List<String> menus) {
		this.menus = menus;
	}
}
