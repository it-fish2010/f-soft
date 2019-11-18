package com.fsoft.manager.shiro;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fsoft.core.annotation.SystemLog;
import com.fsoft.core.common.base.Login;
import com.fsoft.core.common.exceptions.RRException;
import com.fsoft.core.shiro.ShiroContext;
import com.fsoft.core.utils.RetVo;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;

/**
 * @package com.fsoft.manager.shiro
 * @author fish
 * @email it.fish2010@foxmail.com
 * @create 2019-10-09
 * @copyright 2009-2019
 */
@Controller
public class SysLoginController {
	@Autowired
	private Producer producer;

	@RequestMapping("captcha.jpg")
	public void captcha(HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-store, no-cache");
		response.setContentType("image/jpeg");
		// 生成文字验证码
		String text = producer.createText();
		// 生成图片验证码
		BufferedImage image = producer.createImage(text);
		// 保存到shiro session
		ShiroContext.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, text);
		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(image, "jpg", out);
	}

	/*****
	 * F-Soft 登录认证系统
	 * @author Fish(it.fish2010@foxmail.com)
	 * @date 2019-11-05
	 * @param login
	 * @return
	 * @throws IOException
	 */
	@SystemLog("登录系统")
	@ResponseBody
	@RequestMapping(value = "/sys/login", method = RequestMethod.POST)
	public RetVo login(@RequestBody Login login) throws IOException {
		if (StringUtils.isEmpty(login.getCaptcha()) || StringUtils.isEmpty(login.getUsername()) || StringUtils.isEmpty(login.getPassword())) {
			throw new RRException("参数不能为空");
		}
		try {
			Subject subject = ShiroContext.getSubject();
			String passrowd = Base64.decodeToString(login.getPassword());
			login.setPassword(passrowd);
			// sha256加密
			String password = new Sha256Hash(login.getPassword()).toHex();
			UsernamePasswordToken token = new UsernamePasswordToken(login.getUsername(), password);
			subject.login(token);
		} catch (UnknownAccountException e) {
			return RetVo.error(e.getMessage());
		} catch (IncorrectCredentialsException e) {
			return RetVo.error(e.getMessage());
		} catch (LockedAccountException e) {
			return RetVo.error(e.getMessage());
		} catch (AuthenticationException e) {
			return RetVo.error("账户验证失败:" + e.getMessage());
		}
		return RetVo.ok();
	}

	/***
	 * 登录页面
	 * @user Fish
	 * @date 2019-05-05
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	/***
	 * 登录页面
	 * @user Fish
	 * @date 2019-05-05
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "index";
	}

	/**
	 * 退出
	 */
	@SystemLog("退出登录")
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout() {
		ShiroContext.logout();
		return "login";
	}

}
