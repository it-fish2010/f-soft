<!DOCTYPE html>
<html>
    <head>
        <title>欢迎页面-X-admin2.2</title>
 		<#include "fish-web-common.ftl" />
    </head>
    <body>
        <div class="layui-fluid">
            <div class="layui-row">
                <div class="layui-col-md12 layui-col-space3">
                    <div class="layui-card">
                        <div class="layui-card-body">
                            <form class="layui-form" action="">
                                <div class="layui-inline layui-show-xs-block">
                                    <input class="layui-input"  autocomplete="off" placeholder="开始日期" name="startDate" id="start">
                                </div>
                                <div class="layui-inline layui-show-xs-block">
                                    <input class="layui-input"  autocomplete="off" placeholder="截止日期" name="endDate" id="end">
                                </div>
                                <div class="layui-inline layui-show-xs-block">
                                    <input type="text" name="createUserAcct"  placeholder="用户名" autocomplete="off" class="layui-input">
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
	                        	<@perms value="sys:log:remove">
		                        	<button class="layui-btn layui-btn-danger" data-type="removeBatch">
		                        		<i class="layui-icon layui-icon-delete"></i>批量删除
		                    		</button>
	                    		</@perms>
	                        </div>
                            <table id="table-body" class="layui-table layui-form"></table>
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
	<script type="text/javascript" src="${request.contextPath}/static/fish-manager-web/syslog/syslog-list.js?v=20190508"></script>
</html>