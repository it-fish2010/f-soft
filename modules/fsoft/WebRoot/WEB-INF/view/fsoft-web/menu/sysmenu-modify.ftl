<!DOCTYPE html>
<html>
    <head>
        <title>菜单管理-编辑</title>
 		<#include "fsoft-common.ftl" />
    </head>
    <body>
        <div class="layui-fluid">
	        <div class="layui-row">
	            <form class="layui-form layui-form-pane" action="" method="post" lay-filter="form-menu">
	            	<input type="hidden" name="id" id="id" value="${menu.id}"/>
	            	<input type="hidden" name="parentId" id="parentId" value="${menu.parentId}"/>
	                <div class="layui-form-item">
	                    <label for="name" class="layui-form-label">
	                        <span class="x-red">*</span>菜单类型
	                    </label>
	                    <div class="layui-input-block">
	                    	<select name="menuType" lay-filter="menu-menuType">
	                    		<option value="0" <#if menu.menuType==0>selected</#if> >目录</option>
	                    		<option value="1" <#if menu.menuType==1>selected</#if> >菜单</option>
	                    		<option value="2" <#if menu.menuType==2>selected</#if> >按钮</option>
	                    		<option value="3" <#if menu.menuType==3>selected</#if> >其他</option>
	                    	</select>
	                    </div>
	                </div>
	                <div class="layui-form-item">
	                    <label for="name" class="layui-form-label">
	                    	上级目录
	                    </label>
	                    <div class="layui-input-block">
	                    	<div id="xm-select-menuTrees" class="xm-select-demo"></div>
	                    </div>
	                </div>
	                <div class="layui-form-item">
	                    <label for="name" class="layui-form-label">
	                        <span class="x-red">*</span>菜单名称
	                    </label>
	                    <div class="layui-input-block">
	                        <input class="layui-input" type="text" id="name" name="name" value="${menu.name}" required="" lay-verify="required" autocomplete="off">
	                    </div>
	                </div>
	                <div class="layui-form-item">
	                    <label for="name" class="layui-form-label">
	                        <span class="x-red">*</span>排序
	                    </label>
	                    <div class="layui-input-block">
	                        <input class="layui-input" type="text" id="sortNo" name="sortNo" value="${menu.sortNo}"  lay-verify="required|number">
	                    </div>
	                </div>
                 	<div class="layui-form-item">
	                    <label for="desc" class="layui-form-label">权限标识</label>
	                    <div class="layui-input-block">
	                        <input class="layui-input" type="text" id="perms" name="perms" value="${menu.perms}">
	                    </div>
	                </div>
                 	<div class="layui-form-item">
	                    <label for="desc" class="layui-form-label">图标</label>
	                    <div class="layui-input-block">
	                        <input class="layui-input" type="text" id="iconFonts" name="icon" lay-filter="iconFonts" value="${menu.icon}">
	                    </div>
	                </div>
	                <div class="layui-col-md4 layui-col-md-offset4">
						<button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="save">
							<i class="fa fa-save" aria-hidden="true" ></i> 保存
						</button>
	                </div>
	            </form>
	        </div>
	    </div>
    </body>
    <script type="text/javascript">
	    layui.config({
	    	base: '${request.contextPath}/plugins/layui/extend/',
	    	contentPath: '${request.contextPath}'
	    }).extend({
	        IconFonts: 'iconFonts/iconFonts',
	    });
	</script>
	<#-- 引入下拉多选的支持 -->
	<script type="text/javascript" src="${request.contextPath}/static/js/xm-select.js?v=202002"></script>
	<script type="text/javascript" src="${request.contextPath}/static/fsoft-web/menu/sysmenu-modify.js?v=202002"></script>
</html>
