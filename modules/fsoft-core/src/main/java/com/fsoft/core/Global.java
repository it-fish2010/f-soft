package com.fsoft.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.alibaba.fastjson.JSON;

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

	public static void main(String[] args) throws Exception {
		String[] nums = new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18",
				"19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33" };
		int k = 0;
		List<String> rs = new ArrayList<String>();
		List<String> ls = Arrays.asList(nums);
		while (k <= 100) {
			int idx = new Random().nextInt(ls.size() - 1);
			String ch = ls.get(idx);
			if (rs.size() > 0 && JSON.toJSONString(rs).indexOf(ch) > -1)
				continue;
			rs.add(ch);
			if (rs.size() % 6 == 0) {
				k++;
				Collections.sort(rs);
				System.out.println(JSON.toJSONString(rs));
				rs = new ArrayList<String>();
			}
		}
	}

}
