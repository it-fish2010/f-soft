<!DOCTYPE html>
<html>
    <head>
        <title>用户管理-编辑用户</title>
 		<#include "fsoft-common.ftl" />
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
				  	<input type="hidden" id="orgId" name="orgId" value="${user.orgId}">
				  	<input type="hidden" id="currUserRoleId" value="<#list user.roleIdList as role>${role},</#list>">				  	
				  	<div class="layui-form-item">
				  		<label for="loginAcct" class="layui-form-label">
				  			<span class="x-red">*</span>登录名
			  			</label>
				      	<div class="layui-input-block">
				      		<input type="text" id="loginAcct" name="loginAcct" value="${user.loginAcct}" <#if user.id??>disabled</#if>  lay-verify="required" autocomplete="off" class="layui-input">
				      	</div>
				  	</div>
				  	<div class="layui-form-item">
				  		<label for="loginAcct" class="layui-form-label">
				  			<span class="x-red">*</span>姓名
			  			</label>
	                    <div class="layui-input-block">
	                          <input type="text" id="userName" name="userName" value="${user.userName}" lay-verify="required" autocomplete="off" class="layui-input" lay-reqText="姓名必填">
	                    </div>
					</div>
					<div class="layui-form-item">
	              		<label for="L_role" class="layui-form-label">
	              			<span class="x-red">*</span>角色
              			</label>
	                    <div class="layui-input-block">
	                    	<div id="roleIdList" class="xm-select-demo"></div>
	                	</div>
	              	</div>
					<div class="layui-form-item">
	              		<label for="L_org" class="layui-form-label">
	              			<span class="x-red">*</span>单位
              			</label>
	                    <div class="layui-input-block">
	                    	<div id="xm-select-orgTrees" class="xm-select-demo"></div>
	                	</div>
	              	</div>
	                <div class="layui-form-item">
	                	<label for="phone" class="layui-form-label">手机</label>
	                    <div class="layui-input-block">
							<input type="text" id="mobile" name="mobile" value="${user.mobile}" lay-verify="phone" autocomplete="off" class="layui-input" lay-reqText="请输入正确的手机号">
	                  	</div>
	              	</div>
	              	<div class="layui-form-item">
	              		<label for="L_email" class="layui-form-label">电子邮箱</label>
	                    <div class="layui-input-block">
	                    	<input type="text" id="email" name="email" value="${user.email}" lay-verify="email" autocomplete="off" class="layui-input" lay-reqText="请输入正确的邮箱">
	                	</div>
	              	</div>
	              	<div class="layui-form-item">
	              		<label for="L_email" class="layui-form-label">皮肤</label>
	                    <div class="layui-input-block">
	                    	<select name="theme" lay-filter="user-theme">
	                    		<option value="theme1" <#if user.theme=='theme1'>selected</#if> >简约黑</option>
	                    		<option value="theme2" <#if user.theme=='theme2'>selected</#if> >商务风</option>
	                    		<option value="theme3" <#if user.theme=='theme3'>selected</#if> >天空蓝</option>
	                    	</select>
	                	</div>
	              	</div>
	              	<div class="layui-form-item">
	                	<label for="L_pass" class="layui-form-label">密码</label>
	                    <div class="layui-input-inline">
	                    	<input type="password" id="password" name="loginPwd" value="" autocomplete="off" class="layui-input">
	                  	</div>
	                  	<div class="layui-form-mid layui-word-aux">
	                  		<#if (user.id)??>
	                  			<font color="red" style="font-size:12px">如果不需要修改，可不填</font>
                  			<#else>
	                  			<font color="red" style="font-size:12px">默认密码123456</font>
              				</#if>
	                  	</div>
	              	</div>
	                <div class="layui-form-item layui-col-md4 layui-col-md-offset4">
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
	    	base:'${request.contextPath}'+'/plugins/layui/extend/',
	    	contentPath: '${request.contextPath}'
	    });
	</script>
	<#-- 引入下拉多选的支持 -->
	<script type="text/javascript" src="${request.contextPath}/static/js/xm-select.js?v=201910"></script>
	<script type="text/javascript" src="${request.contextPath}/static/fsoft-web/user/sysuser-modify.js?v=201910"></script>

</html>
