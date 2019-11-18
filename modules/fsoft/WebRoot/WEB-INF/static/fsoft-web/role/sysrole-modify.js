layui.use([ 'form', 'layer' ], function() {
	var form = layui.form, layer = layui.layer;
	var id = $('#id').val();
	// 监听提交
	form.on('submit(save)', function(data) {
		var submitUrl = layui.cache['contentPath']+'/sys-role/save';
		if(id!=undefined && id!=""){
			submitUrl = layui.cache['contentPath']+'/sys-role/modify';
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