layui.use([ 'form', 'layer' ], function() {
	var form  = layui.form, 
		layer = layui.layer;
	var rwid = $('#id').val();
	var orgTrees ;
	//组织机构列表
	$.post(layui.cache['contentPath']+"/sys-org/findList", {}, function(data) {
		data = eval('('+data+')');
		if(data.code=="0"){
			orgTrees = xmSelect.render({
			    el: '#xm-select-orgTrees',
			    initValue:[$('#parentId').val()],
			    prop:{name:'name',value:'id'},
			    theme:{color:'#0081ff'},
			    data: data.data,
			    paging: true,
			    pageSize:5,
			    radio:true,
			    size:'medium',
			    name:"parentId",
			    on:function(data){
			    	var arr = data.arr ;
			    	if(data.isAdd){
			    		$('#parentId').val(arr[0].id);
			    	}else{
			    		$('#parentId').val("");
			    	}
			    }
			});
		}
	});
	
	// 监听提交
	form.on('submit(save)', function(data) {
		var submitUrl = layui.cache['contentPath']+'/sys-org/save';
		if(rwid!=undefined && rwid!=""){
			submitUrl = layui.cache['contentPath']+'/sys-org/modify';
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