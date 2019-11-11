<!DOCTYPE html>
<html>
    <head>
        <title>用户管理-列表</title>
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
                                    <input type="text" name="loginAcct"  placeholder="登录名精确搜索" autocomplete="off" class="layui-input">
                                </div>
                                <div class="layui-inline layui-show-xs-block">
                                    <input type="text" name="mobile"  placeholder="手机号模糊搜索" autocomplete="off" class="layui-input">
                                </div>
                                <div class="layui-inline layui-show-xs-block">
                                    <button class="layui-btn layui-btn-normal" lay-submit lay-filter="sreach">
                                    	<i class="layui-icon layui-icon-search"></i>搜索
                                    </button>
                                </div>
                            </form>
                        </div>
                        <div class="layui-card-body ">
                        	<div class="layui-btn-group toolbar">
	                        	<@perms value="sys:user:add">
		                        	<button class="layui-btn layui-btn-sm  layui-btn-normal" data-type="add">
		                        		<i class="fa fa-user-plus" aria-hidden="true" ></i> 新增
		                    		</button>
	                    		</@perms>
	                        	<@perms value="sys:user:remove">
								  	<button class="layui-btn layui-btn-sm  layui-btn-danger" data-type="removeBatch">
								  		<i class="layui-icon layui-icon-delete"></i>删除
								  	</button>
	                    		</@perms>
                    		</div>
                        	<div class="layui-btn-group toolbar">
	                        	<button class="layui-btn layui-btn-sm layui-btn-normal" data-type="refpwd">
	                        		<i class="fa fa-key" aria-hidden="true" ></i> 修改密码
	                    		</button>
                    		</div>
                        	<div class="layui-btn-group toolbar">
                        		<@perms value="sys:user:enable">
		                        	<button class="layui-btn layui-btn-sm  layui-btn-normal" data-type="enable">
	   									<i class="fa fa-play-circle" aria-hidden="true" ></i> 启用
		                    		</button>
		                        	<button class="layui-btn layui-btn-sm  layui-btn-normal" data-type="disable">
	   									<i class="fa fa-minus-circle" aria-hidden="true" ></i> 禁用
		                    		</button>
	                    		</@perms>
                        		<@perms value="sys:user:lock">
		                        	<button class="layui-btn layui-btn-sm  layui-btn-normal" data-type="lock">
										<i class="fa fa-lock" aria-hidden="true" ></i> 加锁
		                    		</button>
	                    		</@perms>
                        		<@perms value="sys:user:unlock">
		                        	<button class="layui-btn layui-btn-sm  layui-btn-normal" data-type="unlock">
	   									<i class="fa fa-unlock" aria-hidden="true" ></i> 解锁
		                    		</button>
	                    		</@perms>
                    		</div>
                            <table id="table" class="layui-table layui-form" lay-filter="tbs"></table>
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
	<script type="text/javascript" src="${request.contextPath}/static/fish-manager-web/user/sysuser-list.js?v=201910"></script>
	<#-- 每一行最后一列的“操作” -->
	<script type="text/html" id="tbRowBar">
		<@perms value="sys:user:info">
			<a class="layui-btn layui-btn-xs layui-btn-normal" lay-event="detail">
				<i class="fa fa-info-circle" aria-hidden="true" ></i> 查看
			</a>
		</@perms>
		<@perms value="sys:user:edit">
		  	<a class="layui-btn layui-btn-xs layui-btn-normal" lay-event="edit">
		  		<i class="fa fa-edit" aria-hidden="true" ></i> 编辑
	  		</a>
		</@perms>
		<@perms value="sys:user:remove">
		  	<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">
		  		<i class="fa fa-trash" aria-hidden="true" ></i> 删除
	  		</a>
		</@perms>
	</script>
	<script type="text/html" id="switchStatus">
	  <#-- 这里的 checked 的状态 -->
	  <input type="checkbox" name="status" value="{{d.status}}" lay-skin="switch" lay-text="正常|禁用" lay-filter="status" {{ d.loginAcct == 'admin' ? 'disabled' : '' }} {{ d.status == 1 ? 'checked' : '' }}>
	</script>
</html>