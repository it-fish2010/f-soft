<!DOCTYPE html>
<html>
    <head>
        <title>角色管理-编辑</title>
 		<#include "fsoft-common.ftl" />
    </head>
    <body>
        <div class="layui-fluid">
	        <div class="layui-row">
	            <form class="layui-form layui-form-pane" action="" method="post">
	            	<input class="layui-input" type="hidden" name="id" id="id" value="${model.id}"/>
	            	<input class="layui-input" type="hidden" id="parentId" name="parentId" value="${model.parentId}">
	                <div class="layui-form-item">
	                    <label for="name" class="layui-form-label">上级</label>
	                    <div class="layui-input-block">
	                    	<div id="xm-select-orgTrees" class="xm-select-demo"></div>
	                    </div>
	                </div>
	                <div class="layui-form-item">
	                    <label for="name" class="layui-form-label">
	                    	<span class="x-red">*</span>组织编码
	                    </label>
	                    <div class="layui-input-block">
	                    	<input class="layui-input" type="text" id="code" name="code" value="${model.code}" lay-verify="required" autocomplete="off">
	                    </div>
	                </div>
	                <div class="layui-form-item">
	                    <label for="name" class="layui-form-label">
	                        <span class="x-red">*</span>组织名称
	                    </label>
	                    <div class="layui-input-block">
	                        <input class="layui-input" type="text" id="name" name="name" value="${model.name}" lay-verify="required" autocomplete="off">
	                    </div>
	                </div>
	                <div class="layui-form-item">
	                    <label for="name" class="layui-form-label">
	                        <span class="x-red">*</span>排序
	                    </label>
	                    <div class="layui-input-block">
	                        <input class="layui-input" type="text" id="sortNo" name="sortNo" value="${model.sortNo}"  lay-verify="required|number">
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
	    	base: '${request.contextPath}/plugins/layui/extend/',
	    	contentPath: '${request.contextPath}'
	    });
	</script>
	<#-- 引入下拉多选的支持 -->
	<script type="text/javascript" src="${request.contextPath}/static/js/xm-select.js?v=202002"></script>
	<script type="text/javascript" src="${request.contextPath}/static/fsoft-web/org/sysorg-modify.js?v=202002"></script>
</html>
