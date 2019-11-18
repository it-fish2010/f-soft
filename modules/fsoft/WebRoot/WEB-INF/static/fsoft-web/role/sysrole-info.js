layui.use([ 'form', 'layer', 'eleTree' ], function() {
	var layer = layui.layer, 
		eleTree = layui.eleTree, 
		form  = layui.form;
	var roleId = $("#id").val();
	var roleTree;
	// 角色权限树形
	$.post(layui.cache['contentPath'] + '/sys-role/findRoleMenuTrees', {"roleId" : roleId}, function(data) {
		// 加载角色的权限树形-20191104
		data = eval('('+data+')');
		var fullTreeData = data.menutree;
		roleTree = eleTree.render({
			elem : '#roleMenuTree',
			data : fullTreeData,
			request: {
			    name: "title",
			    key: "id",
			    children: "children",
			    checked: "checked",
			    disabled: "disabled",
			    isLeaf: "isLeaf"
			},
			defaultExpandAll:true, 
			showCheckbox:true
		});
		var rolemenu = data.rolemenu ;
		var arrs = new Array();
		for(idx in rolemenu){
			arrs.push(rolemenu[idx].id);
		}
		roleTree.setChecked(arrs); //设置已有权限 
	});
	// 监听提交
	form.on('submit(save)', function(data) {
		var checkData  = roleTree.getChecked(false,true);
		var chids = new Array();
		for(chk in checkData){
			chids.push(checkData[chk].id);
		}
		var params = data.field;
		params["menus"] = chids;
		$.ajax({
			type:'POST',
			url:layui.cache['contentPath']+'/sys-role/modify',
			data:JSON.stringify(params),
			contentType: "application/json",
			success:function(rs){
				rs = eval('('+rs+')');
				if(rs.code==0){
					layer.msg("操作成功",{icon:1,time:3000});
					// 关闭当前frame
					xadmin.close();
					// 可以对父窗口进行刷新
					xadmin.father_reload();
				}else{
					layer.msg(rs.msg,{icon:2,time:3000});
				}
			},
			error:function(status,errors){
				layer.alert('请求错误，错误码：'+JSON.stringify(status));
			}
		});
		return false ;
	});
	
});