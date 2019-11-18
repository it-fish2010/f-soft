package com.fsoft.core.sys.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fsoft.core.annotation.SystemLog;
import com.fsoft.core.common.QueryParam;
import com.fsoft.core.sys.entity.SysLog;
import com.fsoft.core.sys.service.SysLogService;
import com.fsoft.core.utils.DateTimeUtils;
import com.fsoft.core.utils.OgnlUtils;
import com.fsoft.core.utils.RetVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * @author Fish
 * @email it.fish2010@foxmail.com
 * @create 2019-03-23
 * @copyright 佳乐软件股份有限公司© 2019-2019
 *
 */
@Controller
@RequestMapping("/sys/log")
public class SysLogController {
	@Autowired
	private SysLogService sysLogService;

	/***
	 * 跳转到日志列表界面
	 * @user Fish
	 * @date 2019-05-06
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list() throws Exception {
		return "/fsoft-web/syslog/syslog-list";
	}

	/**
	 * 列表
	 * 
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/findList")
	@RequiresPermissions("sys:log:list")
	public RetVo findList(@RequestParam Map<String, Object> params) throws Exception {
		// 查询列表数据
		QueryParam query = new QueryParam(params);
		if (OgnlUtils.isNotEmpty(query.get("startDate")))
			query.put("startDate", DateTimeUtils.parseDate((String) query.get("startDate")));
		if (OgnlUtils.isNotEmpty(query.get("endDate")))
			query.put("endDate", DateTimeUtils.parseDate((String) query.get("endDate")));
		Page<SysLog> page = PageHelper.startPage(query.getPage(), query.getLimit());
		List<SysLog> sysLogList = sysLogService.findList(query);
		return RetVo.ok(page.getTotal(), sysLogList);
	}

	/***
	 * 
	 * @author Fish 2019-03-24
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@SystemLog("删除日志")
	@ResponseBody
	@RequestMapping("/removeBatch")
	public RetVo removeBatch(@RequestBody String[] ids) throws Exception {
		sysLogService.removeBatch(Arrays.asList(ids));
		return RetVo.ok();
	}
}
