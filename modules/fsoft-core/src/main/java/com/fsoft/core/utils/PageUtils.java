package com.fsoft.core.utils;

import java.io.Serializable;
import java.util.List;

/**
 * 分页工具类
 * 
 * @package com.fsoft.core.utils
 * @author fish
 * @email it_Fish@aliyun.com
 * @create 2019-10-09
 * @copyright 2009-2019
 */
public class PageUtils implements Serializable {
	private static final long serialVersionUID = 1L;
	// 总记录数
	private int totalCount;
	// 每页记录数
	private int pageSize;
	// 总页数
	private int totalPage;
	// 当前页数
	private int currPage;
	// 列表数据
	private List<?> data;

	/**
	 * 分页
	 * @param list 列表数据
	 * @param totalCount 总记录数
	 * @param pageSize 每页记录数
	 * @param currPage 当前页数
	 */
	public PageUtils(List<?> data, int totalCount, int pageSize, int currPage) {
		this.data = data;
		this.totalCount = totalCount;
		this.pageSize = pageSize;
		this.currPage = currPage;
		this.totalPage = (int) Math.ceil((double) totalCount / pageSize);
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurrPage() {
		return currPage;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}

}
