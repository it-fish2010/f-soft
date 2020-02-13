package com.fsoft.core;

/**
 * F-Soft 静态变量工具类
 * 
 * @package com.fsoft.core
 * @author Fish
 * @email it.fish2010@foxmail.com
 * @date 2020-02-13
 * @CopyRight © F-Soft
 **/
public class Global {
	/**
	 * 超级管理员 角色ID
	 */
	public static final String SYS_ROLE_ADMIN_ID = "1D7EA23EA14B4ACDA0A86E36B7E60816";
	/**
	 * 超级管理员 角色编码 Administrator
	 */
	public static final String SYS_ROLE_ADMIN_CODE = "Administrator";
	/**
	 * 超级管理员 用户ID
	 */
	public static final String SYS_USER_ADMIN_ID = "B0095B89261A454E8C04A4F57CF9F75D";
	/**
	 * 超级管理员 用户登录名
	 */
	public static final String SYS_USER_ADMIN_ACCT = "admin";
	/**
	 * 默认密码
	 */
	public static final String SYS_USER_DEFAULE_PWD = "123456";
	/***
	 * 默认单位ID
	 */
	public static final String DEFAULT_ORG_ID = "2849FC29AB744B58A5F9BF5FFEBC2AB5";

	public static final String YES = "1";
	public static final String NO = "0";

	public static final Integer STATUS_YES = new Integer(YES);
	public static final Integer STATUS_NO = new Integer(NO);

	/**
	 * 菜单类型：目录
	 */
	public static final Integer MENU_TYPE_CATALOG = new Integer("0");
	/**
	 * 菜单类型： 菜单
	 */
	public static final Integer MENU_TYPE_MENU = new Integer("1");

	/**
	 * 菜单类型：按钮
	 */
	public static final Integer MENU_TYPE_BUTTON = new Integer("2");
}
