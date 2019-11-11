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
		elem : '#table-body',
		url : layui.cache['contentPath'] + '/sys/log/findList',
		cols : [[
			{type:'checkbox',fixed: 'left'},
			{field : 'id',title: '#',hide:true,width : 80}, 
			{field : 'operateName',title : '操作',width : 100,sort:true},
			{field : 'operation',title : '详细描述',width : 300},
			{field : 'params',title : '请求参数',width : 300},
			{field : 'remark',title : '备注',width : 300},
			{field : 'ip',title : '客户端IP',width : 140},
			{field : 'createTime',title : '操作时间',width :150,sort:true},
			{field : 'createUserName',title : '操作人',width :150}
			]],
		page : true,
		limit : 20,
		limits : [ 20, 30, 50, 100, 200, 500 ],
		height : 'full-167'
	});
	//搜索监听
	form.on('submit(sreach)', function(data){
		table.reload('table-body',{where:data.field});
		return false ;
	});
	
	var $ = layui.$, active = {
		removeBatch : function() { // 获取选中数据
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
			layer.confirm('确认需要删除已选日志记录?',function(index){
				$.ajax({
					type:'POST',
					url:layui.cache['contentPath']+'/sys/log/removeBatch',
					data:JSON.stringify(ids),
					contentType: "application/json",
					success:function(rs){
						rs = eval('('+rs+')');
						if(rs.code==0){
							layer.msg("删除成功",{icon:1,time:3000});
							table.reload('table-body',{where:data.field});
						}
					},
					error:function(status,errors){
						layer.alert('请求错误，错误码：'+JSON.stringify(status));
					}
				});
				layer.close(index);
			});
		}
	};
	$('.toolbar .layui-btn').on('click', function() {
		var type = $(this).data('type');
		active[type] ? active[type].call(this) : '';
	});
});