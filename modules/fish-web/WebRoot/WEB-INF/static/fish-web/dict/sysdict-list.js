layui.use(['form','table'], function() {
	var form  = layui.form ,
		table = layui.table;
	// 第一个实例
	table.render({
		elem : '#table',
		url : layui.cache['contentPath'] + '/sys-dict/findList',
		cols : [[
			{type:'checkbox',fixed: 'left'},
			{field : 'id',title: '#',hide:true,width : 80}, 
			{field : 'code',title : '编码',width : 100,sort:true},
			{field : 'name',title : '名称',width : 300},
			{field : 'status',title : '状态',width : 110,templet:'#switchStatus'},
			{field : 'createUserName',title : '创建人',width :150}
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
});