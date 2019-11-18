package com.fsoft.core.common;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fsoft.core.utils.OgnlUtils;
import com.fsoft.core.xss.SQLFilter;

/**
 * F-Soft 查询条件的统一封装
 * @package com.fsoft.core.common
 * @author Fish
 * @email it.fish2010@foxmail.com
 * @date 2019-11-16
 * @CopyRight © F-Soft
 **/
@SuppressWarnings({ "unchecked", "serial" })
public class QueryParam extends HashMap<String, Object> {
	// 当前页码
	private int page;
	// 每页条数
	private int limit;

	public QueryParam() {
		this.page = 1;
		this.limit = Integer.MAX_VALUE;
	}

	public QueryParam(Object queryObj) {
		Map<String, Object> params = new HashMap<String, Object>();
		if (OgnlUtils.isEmpty(queryObj))
			queryObj = new HashMap<String, Object>();
		if (queryObj instanceof Map || queryObj instanceof HashMap)
			params = (Map<String, Object>) queryObj;
		else {
			String queryJsonStr = JSON.toJSONString(queryObj);
			params = JSONObject.parseObject(queryJsonStr, Map.class);
		}
		this.putAll(params);
		if (!params.containsKey("page") || OgnlUtils.isEmpty(params.get("page")))
			this.page = 1;
		else {
			String pageStr = (String) params.get("page");
			this.page = Integer.parseInt(pageStr);
		}
		if (!params.containsKey("limit") || OgnlUtils.isEmpty(params.get("limit")))
			this.limit = Integer.MAX_VALUE;
		else {
			String limitStr = (String) params.get("limit");
			this.limit = Integer.parseInt(limitStr);
		}
		if (params.containsKey("sidx") && OgnlUtils.isNotEmpty(params.get("sidx"))) {
			// 防止SQL注入（因为sidx、order是通过拼接SQL实现排序的，会有SQL注入风险）
			String sidx = params.get("sidx").toString();
			this.put("sidx", SQLFilter.sqlInject(sidx));
		}
		if (params.containsKey("order") && OgnlUtils.isNotEmpty(params.get("order"))) {
			String order = params.get("order").toString();
			this.put("order", SQLFilter.sqlInject(order));
		}
	}

	public void setOrgId(String orgId) {
		this.put("orgId", orgId);
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}
}
