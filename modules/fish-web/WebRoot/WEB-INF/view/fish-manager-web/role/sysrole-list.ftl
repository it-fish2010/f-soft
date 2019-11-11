<!DOCTYPE html>
<html>
    <head>
        <title>角色管理-列表</title>
 		<#include "fish-web-common.ftl" />
    </head>
    <body>
        <div class="layui-fluid">
            <div class="layui-row layui-col-space5">
                <div class="layui-col-md12">
                    <div class="layui-card">
                        <div class="layui-card-body">
                            <form class="layui-form" action="">
                                <div class="layui-inline layui-show-xs-block">
                                    <input type="text" name="code"  placeholder="角色编号" autocomplete="off" class="layui-input">
                                </div>
                                <div class="layui-inline layui-show-xs-block">
                                    <input type="text" name="name"  placeholder="角色名称" autocomplete="off" class="layui-input">
                                </div>
                                <div class="layui-inline layui-show-xs-block">
                                    <button class="layui-btn layui-btn-normal" lay-submit lay-filter="sreach">
                                    	<i class="layui-icon layui-icon-search"></i>搜索
                                    </button>
                                </div>
                            </form>
                        </div>
                        <div class="layui-card-body">
                        	<div class="layui-btn-group toolbar">
	                        	<@perms value="sys:role:add">
		                        	<button class="layui-btn layui-btn-normal" data-type="add">
		                        		<i class="layui-icon layui-icon-add-circle"></i>新增
		                    		</button>
	                    		</@perms>
	                        	<@perms value="sys:role:remove">
								  	<button class="layui-btn layui-btn-danger" data-type="removeBatch">
								  		<i class="layui-icon layui-icon-delete"></i>删除
								  	</button>
	                    		</@perms>
                    		</div>
                            <table id="table" class="layui-table layui-form" lay-filter="sysrole-tabs"></table>
                        </div>
                    </div>
                </div>
            </div>
        </div> 
    </body>
    <script type="text/javascript">
	    layui.config({
	    	contentPath: '${request.contextPath}'
	    });
	</script>
	<script type="text/javascript" src="${request.contextPath}/static/fish-manager-web/role/sysrole-list.js?v=201911"></script>
	<#-- 每一行最后一列的“操作” -->
	<script type="text/html" id="sysrole-tabs">
		<@perms value="sys:role:info">
			<a class="layui-btn layui-btn-xs layui-btn-normal" lay-event="permission">
				<i class="fa fa-info-circle" aria-hidden="true" ></i> 设置权限
			</a>
		</@perms>
		<@perms value="sys:role:edit">
		  	<a class="layui-btn layui-btn-xs layui-btn-normal" lay-event="edit">
		  		<i class="fa fa-edit" aria-hidden="true" ></i>  编辑
	  		</a>
		</@perms>
		<@perms value="sys:role:remove">
		  	<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">
		  		<i class="fa fa-trash" aria-hidden="true" ></i> 删除
	  		</a>
		</@perms>
	</script>
	<script type="text/html" id="switchStatus">
	  	<#-- 这里的 checked 的状态 -->
	  	<input type="checkbox" name="status" value="{{d.id}}" lay-skin="switch" lay-text="正常|禁用" 
	  		lay-filter="status" {{ d.status == 1 ? 'checked' : '' }} {{ d.isSystem == 1 ? 'disabled' : '' }}>
	</script>
	
</html>