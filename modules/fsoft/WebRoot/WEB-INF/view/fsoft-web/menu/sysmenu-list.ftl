<!DOCTYPE html>
<html>
    <head>
        <title>菜单管理-列表</title>
        <#-- 引入eleTree树控件的样式 -->
 		<link rel="stylesheet" href="${request.contextPath}/plugins/layui/extend/eleTree/eleTree.css">
 		<#include "fsoft-common.ftl" />
    </head>
    <body>
        <div class="layui-fluid">
            <div class="layui-row layui-col-space3">
                <div class="layui-col-md2">
                	<div class="layui-card">
            			<div class="layui-card-body">
            				<div id="fsoft-menuTrees" lay-filter="fsoft-menuTrees" class="fsoft-trees"> </div>
            			</div>
            		</div>
                </div>
                <div class="layui-col-md10">
                	<div class="layui-row">
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
	                        <div class="layui-card-body toolbar">
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
	                    		<table id="table-body" class="layui-table layui-form" lay-filter="sysmenu-tbs" lay-size="sm"></table>
	                        </div>
	                    </div>
					</div>
				</div>	
            </div>            
        </div> 
    </body>
    <#-- 每一行最后一列的“操作” -->
	<script type="text/html" id="tbRowBar">
		<@perms value="sys:menu:edit">
		  	<a class="layui-btn layui-btn-xs layui-btn-normal" lay-event="edit">
		  		<i class="fa fa-edit" aria-hidden="true" ></i> 编辑
	  		</a>
		</@perms>
		<@perms value="sys:menu:remove">
		  	<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">
		  		<i class="fa fa-trash" aria-hidden="true" ></i> 删除
	  		</a>
		</@perms>
	</script>
    <script type="text/javascript">
	    layui.config({
	    	base: '${request.contextPath}/plugins/layui/extend/',
	    	contentPath: '${request.contextPath}'
	    }).extend({
	    	eleTree:'eleTree/eleTree'
	    });
	</script>
	<script type="text/javascript" src="${request.contextPath}/static/fsoft-web/menu/sysmenu-list.js?v=202002"></script>
</html>