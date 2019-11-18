package com.fsoft.core;

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

	public static final Integer STATE_YES = new Integer(YES);
	public static final Integer STATE_NO = new Integer(NO);

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

	/**
	 * 云服务商
	 */
	public enum CloudService {
		/**
		 * 七牛云
		 */
		QINIU(1),
		/**
		 * 阿里云
		 */
		ALIYUN(2),
		/**
		 * 腾讯云
		 */
		QCLOUD(3);

		private int value;

		private CloudService(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}

}
