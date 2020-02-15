layui.use(['form','table'], function() {
	var form  = layui.form ,
		table = layui.table;
	//字典列表刷新
	table.render({
		elem : '#table',
		url : layui.cache['contentPath'] + '/sys-dict/findItemList',
		where:form.val("fsoftForm"),
		cols : [[
			{type:'checkbox',fixed: 'left'},
			{field : 'id',title: '#',hide:true,width : 80}, 
			{field : 'code',title : '配置编码',width : 200,sort:true},
			{field : 'name',title : '配置名称',width : 200},
			{field : 'dictName',title : '所属字典',width :200},
			{field : 'status',title : '状态',width : 110,templet:'#switchStatus'},
			{field : 'createTime',title : '创建时间',width :200},
			{fixed: 'right', width:150, align:'center',title:'操作', toolbar: '#fsoftItemTable',fixed:'right'}
			]],
		page : true,
		limit : 10,
		limits : [10,20,50,100],
		height : 'full-180'
	});
	//搜索监听
	form.on('submit(sreach)', function(data){
		table.reload('table',{where:data.field});
		return false ;
	});
	//行操作监听 
	table.on('tool(fsoftItemTable)',function(obj){
		var data = obj.data;
		if (obj.event==='edit'){
			xadmin.open('编辑配置项',layui.cache['contentPath']+'/sys-dict/edititem/'+data.id,500,350);
		}else if (obj.event==='del'){
			layer.confirm("确认要删除当前记录？", function(index) {
				$.ajax({
					type:'POST',
					url:layui.cache['contentPath']+'/sys-dict/removeItem',
					data:JSON.stringify([data.id]),
					contentType: "application/json",
					success:function(rs){
						rs = eval('('+rs+')');
						if(rs.code==0){
							layer.msg("删除成功",{icon:1,time:3000});
							table.reload('table',{where:data.field});
						}else{
							layer.alert(rs.msg);
						}
					},
					error:function(status,errors){
						layer.alert('请求错误，错误码：'+JSON.stringify(status));
					}
				});
			});
		}
	});
	
	/* 工具条按钮事件监听 */
	var $ = layui.$, active = {
		removeBatch : function() { //批量删除
			var checkStatus = table.checkStatus('table');
			var data = checkStatus.data;
			if(data==undefined || data.length==0){
				layer.msg("请至少选择一条记录",{icon:7,time:3000});
				return ;
			}
			var ids = new Array();
			for(var k = 0;k<data.length;k++){
				ids.push(data[k].id);
			}
			layer.confirm("确认要删除已选中的（"+ids.length+"）条记录？", function(index) {
				$.ajax({
					type:'POST',
					url:layui.cache['contentPath']+'/sys-dict/removeItem',
					data:JSON.stringify(ids),
					contentType: "application/json",
					success:function(rs){
						rs = eval('('+rs+')');
						if(rs.code==0){
							layer.msg("删除成功",{icon:1,time:3000});
							table.reload('table',{where:data.field});
						}else{
							layer.alert(rs.msg);
						}
					},
					error:function(status,errors){
						layer.alert('请求错误，错误码：'+JSON.stringify(status));
					}
				});
			});
		},
		add:function(){
			xadmin.open('添加配置项',layui.cache['contentPath']+'/sys-dict/additem/'+$("#dictId").val(),500,350);
		},
		enable:function(){/*启用*/
			var checkStatus = table.checkStatus('table');
			var data = checkStatus.data;
			if(data==undefined || data.length==0){
				layer.msg("请至少选择一条记录",{icon:7,time:3000});
				return ;
			}
			var ids = new Array();
			for(var k = 0;k<data.length;k++){
				ids.push(data[k].id);
			}
			layer.confirm("确认要启用已选中的（"+ids.length+"）个配置项？", function(index) {
				$.ajax({
					type:'POST',
					url:layui.cache['contentPath']+'/sys-dict/enableItem',
					data:JSON.stringify(ids),
					contentType: "application/json",
					success:function(rs){
						rs = eval('('+rs+')');
						if(rs.code==0){
							layer.msg("启用成功",{icon:1,time:3000});
							table.reload('table',{where:data.field});
						}else{
							layer.alert(rs.msg);
						}
					},
					error:function(status,errors){
						layer.alert('请求错误，错误码：'+JSON.stringify(status));
					}
				});
			});
		},
		disable:function(){ /*禁用*/
			var checkStatus = table.checkStatus('table');
			var data = checkStatus.data;
			if(data==undefined || data.length==0){
				layer.msg("请至少选择一条记录",{icon:7,time:3000});
				return ;
			}
			var ids = new Array();
			for(var k = 0;k<data.length;k++){
				ids.push(data[k].id);
			}
			layer.confirm("确认要禁用已选中的（"+ids.length+"）个配置项？", function(index) {
				$.ajax({
					type:'POST',
					url:layui.cache['contentPath']+'/sys-dict/disableItem',
					data:JSON.stringify(ids),
					contentType: "application/json",
					success:function(rs){
						rs = eval('('+rs+')');
						if(rs.code==0){
							layer.msg("禁用成功",{icon:1,time:3000});
							table.reload('table',{where:data.field});
						}else{
							layer.alert(rs.msg);
						}
					},
					error:function(status,errors){
						layer.alert('请求错误，错误码：'+JSON.stringify(status));
					}
				});
			});
		}
	};
	$('.toolbar .layui-btn').on('click', function() {
		var type = $(this).data('type');
		active[type] ? active[type].call(this) : '';
	});
});