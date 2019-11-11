package com.fish.core.utils;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author Fish
 * @email it.fish2010@foxmail.com
 * @date 2019-10-31
 * @des 获取应用服务器的工具类
 */
public class CoreServerInfo {
	private static InetAddress address;
	private static Properties props;
	static {
		try {
			address = InetAddress.getLocalHost();
			props = System.getProperties();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @des 获取应用服务器的IP地址
	 * 
	 * @author Fish it.fish2010@foxmail.com
	 * @date 2019-10-31
	 * @return
	 **
	 */
	public static final String getIpAddress() {
		return address.getHostAddress();
	}

	/**
	 * @des 获取应用服务器的计算机名
	 * 
	 * @author Fish it.fish2010@foxmail.com
	 * @date 2019-10-31
	 * @return
	 **
	 */
	public static final String getHostName() {
		return address.getHostName().toString();
	}

	/**
	 * @des 获取操作系统的相关信息
	 * 
	 * @author Fish it.fish2010@foxmail.com
	 * @date 2019-10-31
	 * @return
	 **
	 */
	public static final Map<String, Object> getServerInfo() {
		Map<String, Object> infoMap = new HashMap<String, Object>();
		infoMap.put("javaVersion", props.getProperty("java.version"));
		infoMap.put("javaVendor", props.getProperty("java.vendor"));
		// java虚拟机信息
		infoMap.put("javaVmName", props.getProperty("java.vm.name"));
		infoMap.put("javaVmVersion", props.getProperty("java.vm.version"));
		infoMap.put("javaVmVendor", props.getProperty("java.vm.vendor"));
		infoMap.put("specificationVersion", props.getProperty("java.vm.specification.version"));
		infoMap.put("specificationName", props.getProperty("java.specification.name"));
		infoMap.put("os", props.getProperty("os.name") + "(" + props.getProperty("os.arch") + "-" + props.getProperty("os.version") + ")");
		infoMap.put("hostName", getHostName());
		infoMap.put("ip", getIpAddress());
		return infoMap;
	}
}
