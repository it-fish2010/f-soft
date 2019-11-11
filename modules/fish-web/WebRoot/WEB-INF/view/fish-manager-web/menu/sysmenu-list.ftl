<!DOCTYPE html>
<html>
    <head>
        <title>菜单管理-列表</title>
 		<#include "fish-web-common.ftl" />
    </head>
    <body>
        <div>
            <div class="layui-row">
                <div class="layui-col-md12">
                    <div class="layui-card">
                        <div class="layui-card-body">
                            <form class="layui-form" action="">
                                <div class="layui-inline layui-show-xs-block">
                                    <input type="text" name="perms"  placeholder="权限标识模糊搜索" autocomplete="off" class="layui-input">
                                </div>
                                <div class="layui-inline layui-show-xs-block">
                                    <input type="text" name="name"  placeholder="菜单名称模糊搜索" autocomplete="off" class="layui-input">
                                </div>
                                <div class="layui-inline layui-show-xs-block">
                                    <button class="layui-btn layui-btn-normal" lay-submit lay-filter="sreach">
                                    	<i class="layui-icon layui-icon-search"></i>搜索
                                    </button>
                                </div>
                            </form>
						</div>                            
					</div>	
				</div>	
            </div>            
            <div class="layui-row">
            	<div class="layui-col-md12">
                    <div class="layui-card">
                        <div class="layui-card-body">
                    		<div class="layui-btn-group">
		                    	<@perms value="sys:menu:add">
		                        	<button class="layui-btn layui-btn-normal" data-type="addMenu">
		                        		<i class="layui-icon layui-icon-add-circle"></i>新增
		                    		</button>
		                		</@perms>
		                    	<@perms value="sys:menu:remove">
								  	<button class="layui-btn layui-btn-danger" data-type="removeBatch">
								  		<i class="layui-icon layui-icon-delete"></i>删除
								  	</button>
		                		</@perms>
		            		</div>
                    		<table id="table-body" class="layui-table layui-form" lay-filter="sysmenu-tbs" lay-size="sm"></table>
                        </div>
                    </div>
                </div>
            </div>
        </div> 
    </body>
    <script type="text/javascript">
	    layui.config({
	    	base: '${request.contextPath}'+'/plugins/layui/extend/',
	    	contentPath: '${request.contextPath}'
	    });
	</script>
	<script type="text/javascript" src="${request.contextPath}/static/fish-manager-web/menu/sysmenu-list.js?v=201906"></script>
</html>