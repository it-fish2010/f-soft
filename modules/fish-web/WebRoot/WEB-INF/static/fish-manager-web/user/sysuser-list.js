layui.use(['laydate','form','table'], function() {
	var laydate = layui.laydate;
	var form = layui.form;
	var table = layui.table;
	// 执行一个laydate实例
	laydate.render({
		elem : '#start', // 指定元素
		theme: 'grid',
		calendar: true
		
	});
	// 执行一个laydate实例
	laydate.render({
		elem : '#end', // 指定元素
		theme: 'grid',
		calendar: true
	});
	// 第一个实例
	table.render({
		elem : '#table',
		url : layui.cache['contentPath'] + '/sys-user/findList',
		cols : [[
			{type:'checkbox',fixed: 'left'},
			{type:'numbers',width:50,title:'序号'},
			{field : 'id',title: '#',hide:true,width : 80}, 
			{field : 'loginAcct',title : '用户名',width : 120,sort:true},
			{field : 'userName',title : '姓名',width : 120},
			{field : 'email',title : '电子邮箱',width : 200},
			{field : 'mobile',title : '手机号',width : 200},
			{field : 'status',title : '状态',width : 110,templet:'#switchStatus'},
			{field : 'isLock',title : '是否被锁',width : 110,align:'center',
				templet:function(d){
					if(d.isLock==1){
						return '<span class="layui-badge">加锁</span>'
					}else{
						return '<span class="layui-badge layui-bg-blue">正常</span>'
					}
				}
			},
			{field : 'createTime',title : '创建时间',width :180,sort:true,align:'center',},
			{fixed: 'right', width:230, align:'center',title:'操作', toolbar: '#tbRowBar',fixed:'right'}
			]],
		page : true,
		limit : 20,
		limits : [ 20, 30, 50, 100, 200, 500 ],
		height : 'full-167'
	});
	//搜索监听
	form.on('submit(sreach)', function(data){
		table.reload('table',{where:data.field});
		return false ;
	});
	//状态监听
	form.on('switch(status)', function(data){
		layer.tips(this.value + ' ' + this.name + '：'+ data.elem.checked, data.othis);
	});
	//行操作监听 
	table.on('tool(tbs)',function(obj){
		var data = obj.data;
		if(obj.event==='detail'){
			xadmin.open('编辑用户',layui.cache['contentPath']+'/sys-user/info/'+data.id,435,500);
		}else if (obj.event==='edit'){
			xadmin.open('编辑用户',layui.cache['contentPath']+'/sys-user/edit/'+data.id,435,500);
		}else if (obj.event==='del'){
			layer.confirm("确认要删除当前记录？", function(index) {
				$.ajax({
					type:'POST',
					url:layui.cache['contentPath']+'/sys-user/remove',
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
					url:layui.cache['contentPath']+'/sys-user/remove',
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
			xadmin.open('新增用户帐号',layui.cache['contentPath']+'/sys-user/add',435,500);
		},
		refpwd:function(){
			var checkStatus = table.checkStatus('table');
			var data = checkStatus.data;
			if(data==undefined || data.length==0){
				layer.msg("请至少选择一条记录",{icon:7,time:3000});
				return ;
			}
			layer.alert(JSON.stringify(data));
		},
		lock:function(){ //加锁帐号
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
			layer.confirm("确认要加锁已选中的（"+ids.length+"）个帐号？", function(index) {
				$.ajax({
					type:'POST',
					url:layui.cache['contentPath']+'/sys-user/lock',
					data:JSON.stringify(ids),
					contentType: "application/json",
					success:function(rs){
						rs = eval('('+rs+')');
						if(rs.code==0){
							layer.msg("加锁成功",{icon:1,time:3000});
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
		unlock:function(){
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
			layer.confirm("确认要解锁已选中的（"+ids.length+"）个帐号？", function(index) {
				$.ajax({
					type:'POST',
					url:layui.cache['contentPath']+'/sys-user/unlock',
					data:JSON.stringify(ids),
					contentType: "application/json",
					success:function(rs){
						rs = eval('('+rs+')');
						if(rs.code==0){
							layer.msg("解锁成功",{icon:1,time:3000});
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