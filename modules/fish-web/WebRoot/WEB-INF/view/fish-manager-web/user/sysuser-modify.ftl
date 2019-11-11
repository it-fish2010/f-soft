<!DOCTYPE html>
<html>
    <head>
        <title>用户管理-编辑用户</title>
 		<#include "fish-web-common.ftl" />
    </head>
    <body>
        <div class="layui-fluid">
            <div class="layui-row">
            	<form class="layui-form layui-form-pane" action="" method="post">
	  				<#-- 隐藏域： ID，有ID，是编辑; 无ID，是新增 -->
				  	<input type="hidden" id="id" name="id" value="${user.id}">
				  	<#-- 隐藏域: 锁定标识 -->
				  	<input type="hidden" id="isLock" name="isLock" value="${user.isLock}">
				  	<input type="hidden" id="lockType" name="lockType" value="${user.lockType}">
				  	<input type="hidden" id="lockTime" name="lockTime" value="${user.lockTime}">
				  	<input type="hidden" id="currUserRoleId" value="<#list user.roleIdList as role>${role},</#list>">				  	
				  	<div class="layui-form-item">
				  		<label for="loginAcct" class="layui-form-label">
				  			<span class="x-red">*</span>登录名
			  			</label>
				      	<div class="layui-input-inline">
				      		<input type="text" id="loginAcct" name="loginAcct" value="${user.loginAcct}" <#if user.id??>disabled</#if>  required="" lay-verify="required" autocomplete="off" class="layui-input">
				      	</div>
				  	</div>
				  	<div class="layui-form-item">
				  		<label for="loginAcct" class="layui-form-label">
				  			<span class="x-red">*</span>姓名
			  			</label>
	                    <div class="layui-input-inline">
	                          <input type="text" id="userName" name="userName" value="${user.userName}" lay-verify="required" autocomplete="off" class="layui-input" lay-reqText="姓名必填">
	                    </div>
					</div>
					<div class="layui-form-item">
	              		<label for="L_email" class="layui-form-label">
	              			<span class="x-red">*</span>角色
              			</label>
	                    <div class="layui-input-inline">
	                    	<div id="roleIdList" class="xm-select-demo"></div>
	                	</div>
	              	</div>
	                <div class="layui-form-item">
	                	<label for="phone" class="layui-form-label">手机</label>
	                    <div class="layui-input-inline">
							<input type="text" id="mobile" name="mobile" value="${user.mobile}" lay-verify="phone" autocomplete="off" class="layui-input" lay-reqText="请输入正确的手机号">
	                  	</div>
	              	</div>
	              	<div class="layui-form-item">
	              		<label for="L_email" class="layui-form-label">电子邮箱</label>
	                    <div class="layui-input-inline">
	                    	<input type="text" id="email" name="email" value="${user.email}" lay-verify="email" autocomplete="off" class="layui-input" lay-reqText="请输入正确的邮箱">
	                	</div>
	              	</div>
	              	<div class="layui-form-item">
	                	<label for="L_pass" class="layui-form-label">密码</label>
	                    <div class="layui-input-inline">
	                    	<input type="password" id="password" name="loginPwd" value="" autocomplete="off" class="layui-input">
	                  	</div>
	                  	<div class="layui-input-inline">
	                  		<#if (user.id)??>
	                  			<font color="red" style="font-size:12px">密码如果不需要修改，可不填</font>
                  			<#else>
	                  			<font color="red" style="font-size:12px">默认密码123456，可修改</font>
              				</#if>
	                  	</div>
	              	</div>
	                <div class="layui-form-item">
	                	<button  class="layui-btn layui-btn-normal" lay-filter="save" lay-submit="">
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
	<#-- 引入下拉多选的支持(角色) -->
	<script type="text/javascript" src="${request.contextPath}/static/js/xm-select.js?v=201910"></script>
	<script type="text/javascript" src="${request.contextPath}/static/fish-manager-web/user/sysuser-modify.js?v=201910"></script>

</html>
