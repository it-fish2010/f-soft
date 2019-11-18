layui.use([ 'form', 'layer' ], function() {
	var form = layui.form, layer = layui.layer;
	var id = $('#id').val();
	var roleIds = $('#currUserRoleId').val();
	// 自定义验证规则
	form.verify({
		userName : function(value) {
			if (value.length < 2) {
				return '姓名至少2个字符';
			}
		},
		pass : [ /(.+){6,20}$/, '密码必须6到12位' ],
		repass : function(value) {
			if ($('#L_pass').val() != $('#L_repass').val()) {
				return '两次密码不一致';
			}
		}
	});
	//查询当前登录用户已有的角色列表,加载到角色的下拉列表中
	$.post(layui.cache['contentPath']+"/sys-role/findRoleListByUser", {}, function(data) {
		data = eval('('+data+')');
		if(data.code=="0"){
			var demo2 = xmSelect.render({
			    el: '#roleIdList',
			    initValue:roleIds.split(","),
			    prop:{name:'name',value:'id'},
			    theme:{color:'#0081ff'},
			    data: data.data,
			    paging: true,
			    pageSize:5,
			    size:'small',
			    name:"roleIdList"
			});
		}
	});

	// 监听提交
	form.on('submit(save)', function(data) {
		var submitUrl = layui.cache['contentPath']+'/sys-user/save';
		if(id!=undefined && id!=""){
			submitUrl = layui.cache['contentPath']+'/sys-user/modify';
		}
		$.ajax({
			type:'POST',
			url:submitUrl,
			data:JSON.stringify(data.field),
			contentType: "application/json",
			success:function(rs){
				rs = eval('('+rs+')');
				if(rs.code==0){
					layer.msg("操作成功",{icon:1,time:3000});
					// 关闭当前frame
					xadmin.close();
					// 可以对父窗口进行刷新
					xadmin.father_reload();
				}else{
					layer.msg(rs.msg,{icon:2,time:3000});
				}
			},
			error:function(status,errors){
				layer.alert('请求错误，错误码：'+JSON.stringify(status));
			}
		});
		return false;
	});
});