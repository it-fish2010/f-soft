<ul class="layui-nav right" lay-filter="">
	<li class="layui-nav-item">
		<a href="javascript:;">${currentUser.userName}</a>
		<dl class="layui-nav-child">
			<dd>
				<a onclick="xadmin.open('个人信息','${request.contextPath}/sys-user/info/${currentUser.id}')">
					<i class="fa fa-user-circle-o" aria-hidden="true" ></i> 
					<span>基本资料</span> 
				</a>
			</dd>
			<dd>
				<a onclick="xadmin.open('修改密码','${request.contextPath}/sys-user/info/${currentUser.id}')">
					<i class="fa fa-address-card-o" aria-hidden="true" ></i> 
					<span>修改密码</span>
				</a>
			</dd>
			<dd>
				<a href="${request.contextPath}/logout">
		        	<i class="fa fa-sign-out" aria-hidden="true"></i>
		        	<span>退出</span>
		    	</a>
			</dd>
		</dl>
	</li>
</ul>
