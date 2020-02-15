layui.use(['laydate','form','table'], function() {
	var laydate = layui.laydate;
	var form = layui.form;
	var table = layui.table;
	// 第一个实例
	table.render({
		elem : '#table',
		url : layui.cache['contentPath'] + '/sys-role/findList',
		cols : [[
			{type:'checkbox',fixed: 'left'},
			{type:'numbers',width:50,title:'序号'},
			{field : 'id',title: '#',hide:true,width : 80}, 
			{field : 'code',title : '角色编码',width : 150,sort:true},
			{field : 'name',title : '角色名称',width : 200},
			{field : 'remark',title : '描述',width : 300},
			{field : 'createTime',title : '创建时间',width :170,sort:true},
			{field : 'status',title : '状态',width : 100,templet:'#switchStatus',fixed:'right'},
			{fixed: 'right', width:290, align:'center',title:'操作', toolbar: '#sysrole-tabs',fixed:'right'}
			]],
		page : true,
		limit : 10,
		limits : [10,20,30,50,100,200],
		height : 'full-172'
	});
	//搜索监听
	form.on('submit(sreach)', function(data){
		table.reload('table',{where:data.field});
		return false ;
	});
	//状态
	form.on('switch(status)', function(data){
		var state = 1 ;
		var msg = "启用成功";
		if(data.elem.checked==false){
			state = 0 ;
			msg = "禁用成功";
		}
		$.ajax({
			type:'POST',
			url:layui.cache['contentPath']+'/sys-role/enable',
			data:JSON.stringify({id:this.value,status:state}),
			contentType: "application/json",
			success:function(rs){
				rs = eval('('+rs+')');
				if(rs.code==0){
					layer.msg(msg,{icon:1,time:3000});
					table.reload('table');
				}else{
					layer.msg(rs.msg,{icon:2,time:3000});
				}
			},
			error:function(status,errors){
				layer.alert('请求错误，错误码：'+JSON.stringify(status));
			}
		});
	});
	//行操作监听 
	table.on('tool(sysrole-tabs)',function(obj){
		var data = obj.data;
		if(obj.event==='permission'){ //查看详情
			xadmin.open('角色管理-设置权限',layui.cache['contentPath']+'/sys-role/info/'+data.id,520,700);
		}else if (obj.event==='edit'){ //编辑
			xadmin.open('编辑角色',layui.cache['contentPath']+'/sys-role/edit/'+data.id,400,430);
		}else if (obj.event==='del'){ //删除
			layer.confirm("确认要删除当前记录？", function(index) {
				$.ajax({
					type:'POST',
					url:layui.cache['contentPath']+'/sys-role/remove',
					data:JSON.stringify([data.id]),
					contentType: "application/json",
					success:function(rs){
						rs = eval('('+rs+')');
						if(rs.code==0){
							layer.msg("删除成功",{icon:1,time:3000});
							table.reload('table');
						}else{
							layer.msg(rs.msg,{icon:2,time:3000});
						}
					},
					error:function(status,errors){
						layer.alert('请求错误，错误码：'+JSON.stringify(status));
					}
				});
			});
		}
	});
	//工具栏监听
	var $ = layui.$, active = {
		removeBatch : function() { // 删除
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
					url:layui.cache['contentPath']+'/sys-role/remove',
					data:JSON.stringify(ids),
					contentType: "application/json",
					success:function(rs){
						rs = eval('('+rs+')');
						if(rs.code==0){
							layer.msg("删除成功",{icon:1,time:3000});
							table.reload('table',{where:data.field});
						}else{
							layer.msg(rs.msg,{icon:2,time:3000});
						}
					},
					error:function(status,errors){
						layer.alert('请求错误，错误码：'+JSON.stringify(status));
					}
				});
			});
		},
		add:function(){
			xadmin.open('添加新角色',layui.cache['contentPath']+'/sys-role/add',400,450);
		}
	};
	//按钮组监听
	$('.toolbar .layui-btn').on('click', function() {
		var type = $(this).data('type');
		active[type] ? active[type].call(this) : '';
	});
	
});