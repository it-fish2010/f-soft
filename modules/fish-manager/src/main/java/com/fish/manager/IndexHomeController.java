package com.fish.manager;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fish.core.common.base.BaseController;
import com.fish.core.utils.CoreServerInfo;

/**
 * F-Soft
 * @package com.fish.manager
 * @author Fish
 * @email it.fish2010@foxmail.com
 * @date 2019-11-05
 * @CopyRight © F-Soft
 **/
@Controller
@RequestMapping("/index-home")
public class IndexHomeController extends BaseController {
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
		return "/fish-manager-web/welcome";
	}

	@RequestMapping(value = "/404", method = RequestMethod.GET)
	public String error404() throws Exception {
		return "/fish-manager-web/404";
	}
}
