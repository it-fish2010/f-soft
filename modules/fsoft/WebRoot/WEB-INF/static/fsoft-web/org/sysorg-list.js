layui.use(['form','table','eleTree'], function() {
	var form = layui.form
	   ,table = layui.table
	   ,eleTree = layui.eleTree ;
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
	    table.reload('table-body',{where: {parents:currentData.id}});
	});
	//单位列表初始化
	table.render({
		elem : '#table-body',
		url : layui.cache['contentPath'] + '/sys-org/findList',
		cols : [[
			{type:'checkbox',fixed: 'center'},
			{type:'numbers',width:30,title:'序号',align:'center'},
			{field : 'id',title:'#',hide:true}, 
			{field : 'code',title : '组织编码',width : 120,sort:true},
			{field : 'name',title : '组织名称',width :240},
			{field : 'parentName',title : '上级机构',width : 240},
			{field : 'status',title : '状态',width : 110,templet:'#switchStatus'},
			{field : 'createTime',title : '创建时间',width :180,sort:true,align:'center'},
			{fixed: 'right', width:230, align:'center',title:'操作', toolbar: '#tbRowBar',fixed:'right'}
			]],
		page : true,
		limit : 20,
		limits : [ 20, 30, 50,80,100],
		height : 'full-175'
	});
	
	//搜索监听
	form.on('submit(sreach)', function(data){
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
				ids.push(data[k].rwid);
			}
			layer.confirm("确认要删除已选中的（"+ids.length+"）条记录？", function(index) {
				$.ajax({
					type:'POST',
					url:layui.cache['contentPath']+'/sys-org/remove',
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
		// 添加
		add : function() {
			xadmin.open('添加菜单', layui.cache['contentPath']+ '/sys-org/add',500,500);
		}
	};
	$('.toolbar .layui-btn').on('click', function() {
		var type = $(this).data('type');
		active[type] ? active[type].call(this) : '';
	});
	//行操作监听 
	table.on('tool(sysorg-tbs)',function(obj){
		var data = obj.data;
		if (obj.event==='edit'){
			xadmin.open('编辑单位信息',layui.cache['contentPath']+'/sys-org/edit/'+data.id,400,400);
		}else if (obj.event==='del'){
			layer.confirm("确认要删除当前记录？", function(index) {
				$.ajax({
					type:'POST',
					url:layui.cache['contentPath']+'/sys-org/remove',
					data:JSON.stringify([data.rwid]),
					contentType: "application/json",
					success:function(rs){
						rs = eval('('+rs+')');
						if(rs.code==0){
							layer.msg("删除成功",{icon:1,time:3000});
							orgTree.reload();
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
	}); // End of 行监听 table.on
});