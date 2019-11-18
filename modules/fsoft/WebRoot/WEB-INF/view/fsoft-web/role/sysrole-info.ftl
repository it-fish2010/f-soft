<!DOCTYPE html>
<html>
    <head>
        <title>角色详情</title>
 		<#-- 引入eleTree树控件的样式 -->
 		<link rel="stylesheet" href="${request.contextPath}/plugins/layui/extend/eleTree/eleTree.css">
 		<#include "fsoft-common.ftl" />
    </head>
    <body>
        <div class="layui-fluid">
	        <div class="layui-row">
	            <form class="layui-form layui-form-pane" action="" method="post">
	            	<input type="hidden" name="id" id="id" value="${role.id}"/>
	            	<input type="hidden" name="menus" id="menus" />
	            	
	                <div class="layui-form-item">
	                    <label for="name" class="layui-form-label">
	                        <span class="x-red">*</span>角色编码
	                    </label>
	                    <div class="layui-input-inline">
	                        <input type="text" id="code" name="code" value="${role.code}" class="layui-input" disabled>
	                    </div>
	                </div>
	                <div class="layui-form-item">
	                    <label for="name" class="layui-form-label">
	                        <span class="x-red">*</span>角色名称
	                    </label>
	                    <div class="layui-input-inline">
	                        <input type="text" id="name" name="name" value="${role.name}" class="layui-input" disabled>
	                    </div>
	                </div>
	                <div class="layui-form-item">
	                    <label for="desc" class="layui-form-label">权限设置</label>
	                    <div class="layui-input-block" style="width: 280px; height: 440px; overflow: auto;">
	                    	<ul class="dtree" name="menus" id="roleMenuTree" data-id="0"></ul>
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
	    	base:'${request.contextPath}'+'/plugins/layui/extend/',
	    	contentPath: '${request.contextPath}'
	    }).extend({
	    	eleTree:'eleTree/eleTree'
	    });
	</script>
	<script type="text/javascript" src="${request.contextPath}/static/fsoft-web/role/sysrole-info.js?v=201906"></script>
</html>
