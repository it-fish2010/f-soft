layui.use([ 'form', 'table', 'eleTree' ], function() {
	var form  = layui.form,
		table = layui.table,
		eleTree = layui.eleTree;
	var $ = layui.jquery;
	var menuTrees = eleTree.render({
		elem : '#fsoft-menuTrees',
		url: layui.cache['contentPath'] + '/sys-menu/findMenuTrees',
		where:{menuTypeNotIn:2},
		request: {name: "title",key: "id",children: "children"},
		highlightCurrent:true,
		defaultExpandAll:true,
		expandOnClickNode:false,
		showLine:true
	});
	// 节点点击事件
	eleTree.on("nodeClick(fsoft-menuTrees)",function(d) {
	    var currentData = d.data.currentData;
	    table.reload('table-body',{where: {parents:currentData.id}});
	});
	// 搜索监听
	form.on('submit(sreach)', function(data) {
		table.reload('table-body',{where:data.field});
		return false ;
	});
	// 工具栏监听 （批量操作）
	var $ = layui.$, active = {
		//批量删除	
		removeBatch : function() {
			var checkStatus = table.checkStatus('table-body');
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
					url:layui.cache['contentPath']+'/sys-menu/remove',
					data:JSON.stringify(ids),
					contentType: "application/json",
					success:function(rs){
						rs = eval('('+rs+')');
						if(rs.code==0){
							layer.msg("删除成功",{icon:1,time:3000});
							table.reload('table-body',{where:data.field});
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
		// 添加菜单/目录/按钮
		addMenu : function() {
			xadmin.open('添加菜单', layui.cache['contentPath']+ '/sys-menu/add',500,500);
		}
	};
	//声明按钮监听 
	$('.toolbar .layui-btn').on('click', function() {
		var type = $(this).data('type');
		active[type] ? active[type].call(this) : '';
	});
	//单位列表初始化
	table.render({
		elem : '#table-body',
		url : layui.cache['contentPath'] + '/sys-menu/findList',
		cols : [[
			{type:'checkbox',fixed: 'center'},
			{type:'numbers',width:30,title:'序号',align:'center'},
			{field : 'id',title:'#',hide:true}, 
			{field : 'name',title : '菜单名称',width : 120,sort:true},
			{field : 'parentName',title : '上级菜单',width :240},
			{field : 'menuType',title : '类型',width : '80px',align:'center'
				,templet:function(item){
					if(item.menuType==0){
						return '<span class="layui-badge layui-bg-cyan">目录</span>'
					}else if (item.menuType==1){
						return '<span class="layui-badge layui-bg-blue">菜单</span>'
					}else if (item.menuType==2){
						return '<span class="layui-badge">按钮</span>'
					}else{
						return '<span class="layui-badge layui-bg-primary">其他</span>'
					}
				} 
			},
			{field : 'icon',title : '图标',width :'50px',align:'center'
				,templet:function(item){
					if(item.icon!=undefined && item.icon!=null && item.icon!=""){
						return '<i class="'+item.icon+'" aria-hidden="true" ></i>';
					}
					return "";
				}
			},
			{field : 'perms',title : '权限标识',width :200},
			{field : 'actionUrl',title : '功能URL',width:200},
			{field : 'createTime',title : '创建时间',width :180,sort:true,align:'center'},
			{fixed: 'right', width:230, align:'center',title:'操作', toolbar: '#tbRowBar',fixed:'right'}
			]],
		page : true,
		limit : 20,
		limits : [ 20, 30, 50,80,100],
		height : 'full-175'
	});
	
	//行操作监听 
	table.on('tool(sysmenu-tbs)',function(obj){
		var data = obj.data;
		if(obj.event==='detail'){
			xadmin.open(data.userName+'-详情',layui.cache['contentPath']+'/sys-menu/info/'+data.id,500,510);
		}else if (obj.event==='edit'){
			xadmin.open('编辑菜单-'+data.name,layui.cache['contentPath']+'/sys-menu/edit/'+data.id,500,510);
		}else if (obj.event==='del'){
			layer.confirm("确认要删除当前记录？", function(index) {
				$.ajax({
					type:'POST',
					url:layui.cache['contentPath']+'/sys-menu/remove',
					data:JSON.stringify([data.id]),
					contentType: "application/json",
					success:function(rs){
						rs = eval('('+rs+')');
						if(rs.code==0){
							layer.msg("删除成功",{icon:1,time:3000});
							table.reload('table-body',{where:data.field});
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
});