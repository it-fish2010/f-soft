<!DOCTYPE html>
<html>
<head>
  <title>F-Soft-应用服务开发平台</title>
  <#include "fish-web-common.ftl" />
  <#-- 加载内置皮肤 -->
  <link rel="stylesheet" href="${request.contextPath}/plugins/x-admin/css/${currentUser.currTheme}.css">
</head>

<body class="index">
    <#-- 顶部开始 -->
    <#include "index-nav-head.ftl" />
    <#-- 顶部结束 -->
    <#-- 左侧菜单开始 -->
	<#include "index-nav-tree.ftl" />
    <#-- 左侧菜单结束 -->
    <#-- 右侧主体开始 -->
    <div class="page-content">
        <div class="layui-tab tab" lay-filter="xbs_tab" lay-allowclose="false">
            <ul class="layui-tab-title">
                <li class="home">
                    <i class="fa fa-home" aria-hidden="true"></i>我的桌面
                </li>
            </ul>
            <div class="layui-unselect layui-form-select layui-form-selected" id="tab_right">
                <dl>
                    <dd data-type="this">关闭当前</dd>
                    <dd data-type="other">关闭其它</dd>
                    <dd data-type="all">关闭全部</dd>
                </dl>
            </div>
            <div class="layui-tab-content">
                <div class="layui-tab-item layui-show">
                    <iframe src='${request.contextPath}/index-home/welcome' frameborder="0" scrolling="yes" class="x-iframe"></iframe>
                </div>
            </div>
            <div id="tab_show"></div>
        </div>
    </div>
    <div class="page-content-bg">
    </div>
    <#-- 右侧主体结束 -->
</body>
<script type="text/javascript">
    layui.config({
    	base: '${request.contextPath}'+'/plugins/build/js/',
    	contentPath: '${request.contextPath}'
    });
</script>
<script type="text/javascript" src="${request.contextPath}/static/fish-web/index/index-fish-web-navbar-tree.js?v=201911"></script>
</html>