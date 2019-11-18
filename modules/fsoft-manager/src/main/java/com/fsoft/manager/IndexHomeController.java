package com.fsoft.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fsoft.core.Global;
import com.fsoft.core.common.base.BaseController;
import com.fsoft.core.utils.CoreServerInfo;
import com.fsoft.manager.user.service.SysUserService;

/**
 * F-Soft
 * @package com.fsoft.manager
 * @author Fish
 * @email it.fish2010@foxmail.com
 * @date 2019-11-05
 * @CopyRight © F-Soft
 **/
@Controller
@RequestMapping("/index-home")
public class IndexHomeController extends BaseController {

	@Autowired
	private SysUserService userService;

	/***
	 * 加载首页
	 * @user Fish
	 * @date 2019-05-06
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String welcome(Model model) throws Exception {
		model.addAttribute("server", CoreServerInfo.getServerInfo());
		String orgId = getUser().getCurrentOrgId();
		if (Global.DEFAULT_ORG_ID.equalsIgnoreCase(orgId)) {
			orgId = null;
		}
		model.addAttribute("user", userService.getIndexHomeUser(orgId));
		return "/fsoft-web/welcome";
	}

	/**
	 * F-Soft 返回错误页面
	 * @author Fish(it.fish2010@foxmail.com)
	 * @date 2019-11-16
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/404", method = RequestMethod.GET)
	public String error404() throws Exception {
		return "/fsoft-web/404";
	}
}
