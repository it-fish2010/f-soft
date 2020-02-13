package com.fsoft.core.common.base;

/**
 * F-Soft 基础的数据Bean(树形结构)
 * 
 * @package com.fsoft.core.common.base
 * @author Fish
 * @email it.fish2010@foxmail.com
 * @date 2019-11-02
 * @CopyRight © F-Soft
 **/
@SuppressWarnings("serial")
public class BaseTreeVo extends BaseVo {
	private String parentId;
	private String parents;

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getParents() {
		return parents;
	}

	public void setParents(String parents) {
		this.parents = parents;
	}

}
