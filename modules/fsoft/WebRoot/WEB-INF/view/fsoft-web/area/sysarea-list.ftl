<!DOCTYPE html>
<html>
    <head>
        <title>组织机构管理-列表查询</title>
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
            				<div id="fsoftAreaTree" lay-filter="fsoftAreaTree" class="fsoft-trees"></div>
            			</div>
            		</div>
            	</div>
                <div class="layui-col-md10">
                    <div class="layui-row">
                        <div class="layui-card">
	                        <div class="layui-card-body">
	                            <form class="layui-form" action="">
	                                <div class="layui-inline layui-show-xs-block">
	                                    <input type="text" name="code" placeholder="编码精确搜索" autocomplete="off" class="layui-input">
	                                </div>
	                                <div class="layui-inline layui-show-xs-block">
	                                    <input type="text" name="name" placeholder="名称模糊搜索" autocomplete="off" class="layui-input">
	                                </div>
	                                <div class="layui-inline layui-show-xs-block">
	                                    <input type="text" name="areaCode" placeholder="区号模糊搜索" autocomplete="off" class="layui-input">
	                                </div>
	                                <div class="layui-inline layui-show-xs-block">
	                                    <button class="layui-btn layui-btn-normal" lay-submit lay-filter="sreach">
	                                    	<i class="layui-icon layui-icon-search"></i>搜索
	                                    </button>
	                                </div>
	                            </form>
	                        </div>
	                        <div class="layui-card-body toolbar">
		                    	<@perms value="sys:area:save">
		                        	<button class="layui-btn layui-btn-normal" data-type="add">
		                        		<i class="layui-icon layui-icon-add-circle"></i>新增
		                    		</button>
		                		</@perms>
		                    	<@perms value="sys:area:remove">
								  	<button class="layui-btn layui-btn-danger" data-type="removeBatch">
								  		<i class="layui-icon layui-icon-delete"></i>删除
								  	</button>
		                		</@perms>
	                    		<table id="table-body" class="layui-table layui-form" lay-filter="sysorg-tbs" lay-size="sm"></table>
	                        </div>
	                    </div>
		            </div>
				</div>	
            </div>            
        </div> 
    </body>
    <script type="text/html" id="switchStatus">
    	<#-- 这里的 checked 的状态 -->
	  	<input type="checkbox" name="status" value="{{d.status}}" lay-skin="switch" lay-text="正常|禁用" lay-filter="status" disabled {{ d.status == 1 ? 'checked' : '' }}>
	</script>
	<#-- 每一行最后一列的“操作” -->
	<script type="text/html" id="tbRowBar">
		<@perms value="sys:area:modify">
		  	<a class="layui-btn layui-btn-xs layui-btn-normal" lay-event="edit">
		  		<i class="fa fa-edit" aria-hidden="true" ></i> 编辑
	  		</a>
		</@perms>
		<@perms value="sys:area:remove">
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
	<script type="text/javascript" src="${request.contextPath}/static/fsoft-web/area/sysarea-list.js?v=202002"></script>
</html>