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
	            	<input type="hidden" name="id" id="id" value="${role.id}"/>
	                <div class="layui-form-item">
	                    <label for="name" class="layui-form-label">
	                        <span class="x-red">*</span>角色编码
	                    </label>
	                    <div class="layui-input-inline">
	                        <input type="text" id="code" name="code" value="${role.code}"  required="" lay-verify="required" autocomplete="off" class="layui-input">
	                    </div>
	                </div>
	                <div class="layui-form-item">
	                    <label for="name" class="layui-form-label">
	                        <span class="x-red">*</span>角色名称
	                    </label>
	                    <div class="layui-input-inline">
	                        <input type="text" id="name" name="name" value="${role.name}" required="" lay-verify="required" autocomplete="off" class="layui-input">
	                    </div>
	                </div>
	                <div class="layui-form-item layui-form-text">
	                    <label for="desc" class="layui-form-label">描述</label>
	                    <div class="layui-input-block">
	                        <textarea placeholder="角色描述" id="remark" name="remark" autocomplete="off" class="layui-textarea">${role.remark}</textarea>
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
	<script type="text/javascript" src="${request.contextPath}/static/fish-manager-web/role/sysrole-modify.js?v=201906"></script>

</html>
