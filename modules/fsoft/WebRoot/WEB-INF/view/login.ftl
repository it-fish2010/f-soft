<!doctype html>
<html>
<head>
	<title>F-Soft-Platform 应用管理平台</title>
	<#include "fsoft-common.ftl" />
	<link rel="stylesheet" href="${request.contextPath}/plugins/x-admin/css/login.css">
</head>
<body>
    <div class="login layui-anim layui-anim-scale">
        <div class="message">F-Soft-Platform 应用管理平台</div>
        <div id="darkbannerwrap"></div>
        <form method="post" class="layui-form" >
            <input class="layui-input" name="username" placeholder="用户名"  type="text" lay-verify="required" autocomplete="off">
            <hr class="hr15">
            <input class="layui-input" id="password" name="password" placeholder="密码"  type="password" lay-verify="required" autocomplete="off">
            <hr class="hr15">
            <div class="layui-inline">
            	<div class="layui-input-inline">
            		<input class="layui-input" name="captcha" placeholder="验证码" type="text" lay-verify="required" autocomplete="off">
            	</div>
            	<div class="layui-input-inline">
	            	<img id="captcha" src="${request.contextPath}/captcha.jpg" onclick="refreshCode(this)">
            	</div>
            	<hr class="hr15">
            </div>
            <input value="登录" lay-submit lay-filter="login" style="width:100%;" type="submit">
            <hr class="hr20" >
        </form>
    </div>
    <#-- 加载Base64加密算法  -->
    <script type="text/javascript" src="${request.contextPath}/static/js/Base64.js?v=201911"></script>
    <script>
    	$(function() {
			layui.use('form', function() {
				var form = layui.form;
				form.on('submit(login)', function(data) {
					var password = $('#password').val();
					var base = new Base64();
					password = base.encode(password);
					var params = data.field;
					params["password"]=password;
					$.ajax({
			            type: "POST",
			            url: "${request.contextPath}/sys/login",
			            contentType: "application/json",
			            data: JSON.stringify(params),
			            dataType: "json",
			            success: function (result) {
			                if (result.code == 0) {
			                    parent.location.href = '${request.contextPath}/index';
			                } else {
			                    layer.msg(result.msg, {icon: 5});
			                    refreshCode();
			                }
			            }
			        });
					return false;
				});
			});
		});
        <#-- 刷新验证码 20190506  -->
        function refreshCode(){
		    var captcha = document.getElementById("captcha");
		    captcha.src = "${request.contextPath}/captcha.jpg?t=" + new Date().getTime();
		}
    </script>
    <#-- 底部结束 -->
</body>
</html>