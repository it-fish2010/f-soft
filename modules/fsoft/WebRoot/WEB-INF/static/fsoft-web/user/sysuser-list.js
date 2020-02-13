layui.use(['form','table','eleTree'], function() {
	var form = layui.form;
	var table = layui.table;
	var eleTree = layui.eleTree;
	//用户管理-组织机构树形-20191116
	var orgTree = eleTree.render({
		elem : '#fsoft-orgTree',
		url: layui.cache['contentPath'] + '/sys-org/findOrgTrees',
		request: {name: "title",key: "id",children: "children"},
		highlightCurrent:true,
		defaultExpandAll:true,
		expandOnClickNode:false,
		showLine:true
	});
	// 节点点击事件
	eleTree.on("nodeClick(fsoft-orgTree)",function(d) {
	    var currentData = d.data.currentData;
	    table.reload('table',{where: {orgId:currentData.id}});
	});
	//用户列表初始化
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
			{field : 'mobile',title : '手机号',width : 130},
			{field : 'orgName',title : '所属单位',width : 200},
			{field : 'status',title : '状态',width : 110,templet:'#switchStatus'},
			{field : 'isLock',title : '已锁定',width : 100,align:'center',
				templet:function(d){
					if(d.isLock==1){
						return '<span class="layui-badge">是</span>'
					}else{
						return '<span class="layui-badge layui-bg-blue">否</span>'
					}
				}
			},
			{field : 'createTime',title : '创建时间',width :180,sort:true,align:'center'},
			{fixed: 'right', width:230, align:'center',title:'操作', toolbar: '#tbRowBar',fixed:'right'}
			]],
		page : true,
		limit : 20,
		limits : [ 20, 30, 50, 100, 200],
		height : 'full-172'
	});
	//搜索监听
	form.on('submit(sreach)', function(data){
		table.reload('table',{where:data.field});
		return false ;
	});
	//行操作监听 
	table.on('tool(tbs)',function(obj){
		var data = obj.data;
		if(obj.event==='detail'){
			xadmin.open(data.userName+'-详情',layui.cache['contentPath']+'/sys-user/info/'+data.id,500,550);
		}else if (obj.event==='edit'){
			xadmin.open('编辑用户',layui.cache['contentPath']+'/sys-user/edit/'+data.id,500,550);
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
			xadmin.open('新增帐号',layui.cache['contentPath']+'/sys-user/add',500,550);
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
			layer.confirm("确认要启用已选中的（"+ids.length+"）个帐号？", function(index) {
				$.ajax({
					type:'POST',
					url:layui.cache['contentPath']+'/sys-user/enable',
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
			layer.confirm("确认要禁用已选中的（"+ids.length+"）个帐号？", function(index) {
				$.ajax({
					type:'POST',
					url:layui.cache['contentPath']+'/sys-user/disable',
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