<!DOCTYPE html>
<html>
    <head>
        <title>字典管理-列表</title>
 		<#include "fsoft-common.ftl" />
    </head>
    <body>
        <div class="layui-fluid">
            <div class="layui-row layui-col-space3">
                <div class="layui-col-md12">
                    <div class="layui-card">
                        <div class="layui-card-body">
                            <form class="layui-form" action="" lay-filter="fsoftForm">
                                <div class="layui-inline layui-show-xs-block">
                                    <input class="layui-input" type="text" name="code" placeholder="字典编号精确搜索" autocomplete="off">
                                </div>
                                <div class="layui-inline layui-show-xs-block">
                                    <input class="layui-input" type="text" name="name" placeholder="字典名称模糊搜索" autocomplete="off">
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
	                        	<@perms value="sys:dict:save">
		                        	<button class="layui-btn layui-btn-normal" data-type="add">
		                        		<i class="fa fa-plus-circle" aria-hidden="true" ></i> 新增
		                    		</button>
	                    		</@perms>
	                        	<@perms value="sys:dict:remove">
								  	<button class="layui-btn layui-btn-danger" data-type="removeBatch">
								  		<i class="layui-icon layui-icon-delete"></i>删除
								  	</button>
	                    		</@perms>
                    		</div>
                        	<div class="layui-btn-group toolbar">
                        		<@perms value="sys:dict:enable">
		                        	<button class="layui-btn layui-btn-normal" data-type="enable">
	   									<i class="fa fa-play-circle" aria-hidden="true" ></i> 启用
		                    		</button>
	                    		</@perms>
                        		<@perms value="sys:dict:disable">
		                        	<button class="layui-btn layui-btn-normal" data-type="disable">
	   									<i class="fa fa-minus-circle" aria-hidden="true" ></i> 禁用
		                    		</button>
	                    		</@perms>
                    		</div>
                        </div>
						<table id="table" class="layui-table" lay-filter="tbs"></table>
                    </div>
                </div>
            </div>
        </div> 
    </body>
    <script type="text/javascript">
	    layui.config({
		    base: '${request.contextPath}/plugins/layui/extend/',
	    	contentPath: '${request.contextPath}'
	    });
	</script>
	<script type="text/html" id="sysdict-tbs">
		<@perms value="sys:dict:setting">
		  	<a class="layui-btn layui-btn-xs layui-btn-normal" lay-event="setting">
		  		<i class="fa fa-info-circle" aria-hidden="true" ></i> 设置
	  		</a>
		</@perms>
		<@perms value="sys:dict:edit">
		  	<a class="layui-btn layui-btn-xs layui-btn-normal" lay-event="edit">
		  		<i class="fa fa-edit" aria-hidden="true" ></i> 编辑
	  		</a>
		</@perms>
		<@perms value="sys:dict:remove">
		  	<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">
		  		<i class="fa fa-trash" aria-hidden="true" ></i> 删除
	  		</a>
		</@perms>
	</script>
	<script type="text/html" id="switchStatus">
	  	<input type="checkbox" name="status" value="{{d.status}}" lay-skin="switch" lay-text="正常|禁用" lay-filter="status" {{ d.loginAcct == 'admin' ? 'disabled' : '' }} {{ d.status == 1 ? 'checked' : '' }}>
	</script>
	<script type="text/javascript" src="${request.contextPath}/static/fsoft-web/dict/sysdict-list.js?v=201911"></script>
</html>