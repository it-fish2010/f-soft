<!DOCTYPE html>
<html>
    <head>
        <title>角色管理-编辑</title>
 		<#include "fish-web-common.ftl" />
    </head>
    <body>
        <div class="layui-fluid">
	        <div class="layui-row">
	            <form class="layui-form layui-form-pane" action="" method="post">
	            	<input type="hidden" name="id" id="id" value="${menu.id}"/>
	                <div class="layui-form-item">
	                    <label for="name" class="layui-form-label">
	                        <span class="x-red">*</span>菜单类型
	                    </label>
	                    <div class="layui-input-inline">
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
	                    <div class="layui-input-inline">
	                    	<input class="layui-input" type="hidden" id="parentId" name="parentId" value="${menu.parentId}">
	                        <input class="layui-input" type="text" id="parentName" name="parentName" value="${menu.parentName}">
	                    </div>
	                </div>
	                <div class="layui-form-item">
	                    <label for="name" class="layui-form-label">
	                        <span class="x-red">*</span>菜单名称
	                    </label>
	                    <div class="layui-input-inline">
	                        <input class="layui-input" type="text" id="name" name="name" value="${menu.name}" required="" lay-verify="required" autocomplete="off">
	                    </div>
	                </div>
	                <div class="layui-form-item">
	                    <label for="name" class="layui-form-label">
	                        <span class="x-red">*</span>排序
	                    </label>
	                    <div class="layui-input-inline">
	                        <input class="layui-input" type="text" id="sortNo" name="sortNo" value="${menu.sortNo}"  lay-verify="required|number">
	                    </div>
	                </div>
	                 <div class="layui-form-item">
	                    <label for="desc" class="layui-form-label">权限标识</label>
	                    <div class="layui-input-inline">
	                        <input class="layui-input" type="text" id="perms" name="perms" value="${menu.perms}">
	                    </div>
	                </div>
	                <div class="layui-form-item">
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
	    	contentPath: '${request.contextPath}'
	    });
	</script>
	<script type="text/javascript" src="${request.contextPath}/static/fish-manager-web/menu/sysmenu-modify.js?v=201906"></script>
</html>
