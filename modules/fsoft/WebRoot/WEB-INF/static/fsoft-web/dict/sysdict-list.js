layui.use(['form','table'], function() {
	var form  = layui.form ,
		table = layui.table;
	//字典列表刷新
	table.render({
		elem : '#table',
		url : layui.cache['contentPath'] + '/sys-dict/findList',
		cols : [[
			{type:'checkbox',fixed: 'left'},
			{field : 'id',title: '#',hide:true,width : 80}, 
			{field : 'code',title : '编码',width : 150,sort:true},
			{field : 'name',title : '名称',width : 300},
			{field : 'dictType',title : '类型',width :80,align:'center'
				,templet:function(item){
					if(item.dictType==1){
						return '<span class="layui-badge layui-bg-blue">列表</span>'
					}else if(item.dictType==2){
						return '<span class="layui-badge layui-bg-gray">树形</span>'
					}else{
						return '<span class="layui-badge">其他</span>'
					}
				} 
			},
			{field : 'status',title : '状态',width : 110,templet:'#switchStatus'},
			{fixed: 'right', width:220, align:'center',title:'操作', toolbar: '#sysdict-tbs',fixed:'right'}
			]],
		page : true,
		limit : 20,
		limits : [ 20, 30, 50,100],
		height : 'full-180'
	});
	//搜索监听
	form.on('submit(sreach)', function(data){
		table.reload('table',{where:data.field});
		return false ;
	});
	//行操作监听 
	table.on('tool(tbs)',function(obj){
		var data = obj.data;
		if(obj.event==='setting'){
			xadmin.open(data.name+'-配置',layui.cache['contentPath']+'/sys-dict/items/'+data.id);
		}else if (obj.event==='edit'){
			xadmin.open('编辑字典',layui.cache['contentPath']+'/sys-dict/edit/'+data.id,500,350);
		}else if (obj.event==='del'){
			layer.confirm("确认要删除当前记录？", function(index) {
				$.ajax({
					type:'POST',
					url:layui.cache['contentPath']+'/sys-dict/remove',
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
					url:layui.cache['contentPath']+'/sys-dict/remove',
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
			xadmin.open('新增字典',layui.cache['contentPath']+'/sys-dict/add',500,350);
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
			layer.confirm("确认要启用已选中的（"+ids.length+"）个字典？", function(index) {
				$.ajax({
					type:'POST',
					url:layui.cache['contentPath']+'/sys-dict/enable',
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
			layer.confirm("确认要禁用已选中的（"+ids.length+"）个字典？", function(index) {
				$.ajax({
					type:'POST',
					url:layui.cache['contentPath']+'/sys-dict/disable',
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