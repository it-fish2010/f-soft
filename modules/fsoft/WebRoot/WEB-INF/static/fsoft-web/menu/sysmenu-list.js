layui.use([ 'form', 'table', 'treeTable' ], function() {
	var form = layui.form, table = layui.table, treeTable = layui.treeTable;
	var $ = layui.jquery;
	var trees = null ;
	
	// 行操作监听(查看详情)
	treeTable.on('tree(detail)', function(obj) {
		var data = obj.item ;
		xadmin.open('菜单详情', layui.cache['contentPath'] + '/sys-menu/info/' + data.id,500,500);
	});
	// 行操作监听(编辑)
	treeTable.on('tree(edit)', function(obj) {
		var data = obj.item ;
		xadmin.open('编辑菜单', layui.cache['contentPath'] + '/sys-menu/edit/' + data.id,500,500);
		obj.update({

		});
	});
	// 行操作监听(删除)
	treeTable.on('tree(del)', function(data) {
		layer.confirm("确认要删除当前记录？", function(index) {
			$.ajax({
				type : 'POST',
				url : layui.cache['contentPath'] + '/sys-menu/remove',
				data : JSON.stringify([ data.id ]),
				contentType : "application/json",
				success : function(rs) {
					rs = eval('(' + rs + ')');
					if (rs.code == 0) {
						layer.msg("删除成功", {icon : 1,time : 3000});
						renderTreeTable();
					} else {
						layer.msg(rs.msg, {icon : 2,time : 3000});
					}
				},
				error : function(status, errors) {
					layer.alert('请求错误，错误码：' + JSON.stringify(status));
				}
			});
		});
	});
	// 搜索监听
	form.on('submit(sreach)', function(data) {
		renderTreeTable(data.field);
	});
	// 工具栏监听 （批量操作）
	var $ = layui.$, active = {
		//批量删除	
		removeBatch : function() {
			var checkStatus = treeTable.checked(trees);
			if (checkStatus == undefined || checkStatus.length == 0) {
				layer.msg("请至少选择一条记录", {icon : 7,time : 3000 });
				return;
			}
			layer.confirm('确认需要删除已选中的（'+checkStatus.length+'）条菜单记录?', function(index) {
				$.ajax({
					type : 'POST',
					url : layui.cache['contentPath']+ '/sys-menu/remove',
					data : JSON.stringify(checkStatus),
					contentType : "application/json",
					success : function(rs) {
						rs = eval('(' + rs + ')');
						if (rs.code == 0) {
							layer.msg("删除成功", {icon : 1,time : 3000});
							renderTreeTable();
						}
					},
					error : function(status, errors) {
						layer.alert('请求错误，错误码：' + JSON.stringify(status));
					}
				});
			});
		},
		// 添加菜单/目录/按钮
		addMenu : function() {
			xadmin.open('添加菜单', layui.cache['contentPath']+ '/sys-menu/add',500,500);
		}
	};
	$('.layui-btn-group .layui-btn').on('click', function() {
		var type = $(this).data('type');
		active[type] ? active[type].call(this) : '';
	});
	
	/**
	 * 刷新菜单列表
	 */
	var renderTreeTable = function(params){
		$.post(layui.cache['contentPath'] + '/sys-menu/findList',params ,function(data) {
			data = eval('('+data+')');
			// 表格数据初始化
			trees = treeTable.render({
				elem : '#table-body',
				data:data,
				icon_key : 'name',// 必须
				primary_key : 'id',
				parent_key : 'parentId',
				top_value : '0',
				is_click_icon: true,
				is_checkbox: true,
				cols : [ 
					{key : 'name',title : '名称',width : '150px',align : 'center'}, 
					{key : 'parentName',title : '上级名称',width : '120px',align : 'center'}, 
					{key : 'menuType',title : '类型',width : '80px',align:'center'
						,template:function(item){
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
					{key : 'icon',title : '图标',width :'50px',align:'center'
						,template:function(item){
							if(item.icon!=undefined && item.icon!=null && item.icon!=""){
								return '<i class="'+item.icon+'" aria-hidden="true" ></i>';
							}
							return "";
						}
					},
					{key : 'sortNo',title : '排序',width :'50px',align:'center'},
					{key : 'perms',title : '权限标识',width : '200px'},
					{key : 'actionUrl',title : '功能URL'},
					{fixed: 'right', width:'200px', align:'center',title:'操作', fixed:'right'
						,template:function(item){
							var html = new Array();
							html.push('	<a class="layui-btn layui-btn-xs layui-btn-normal" lay-filter="detail">');
							html.push('		<i class="fa fa-info-circle" aria-hidden="true" ></i> 查看');
							html.push('	</a>');
							html.push('	<a class="layui-btn layui-btn-xs layui-btn-normal" lay-filter="edit">');
							html.push('		<i class="fa fa-edit" aria-hidden="true" ></i> 编辑');
							html.push('	</a>');
							html.push('	<a class="layui-btn layui-btn-xs layui-btn-danger" lay-filter="del">');
							html.push('		<i class="fa fa-trash" aria-hidden="true" ></i> 删除 ');
							html.push('	</a>');
							return html.join("");
						}
					}
				]
			});
		});
	};
	renderTreeTable(); //初始化菜单列表
});