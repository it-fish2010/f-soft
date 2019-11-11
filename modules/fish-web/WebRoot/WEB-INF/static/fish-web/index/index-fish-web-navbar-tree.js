/**
 * 左侧菜单JS加载 ${request.contextPath}
 */
layui.use([ 'layer', 'form','element'], function() {
	var layer = layui.layer, 
		form = layui.form,
		element = layui.element;
	/**加载菜单*/
	$.post(layui.cache['contentPath']+"/sys-menu/user", {}, function(data) {
		var rs = eval('('+data+')');
		var menulist = rs.menulist ;
		for(var k=0;k<menulist.length;k++){
			var menu = menulist[k];
			var $li = new Array();
			//目录 
			if(menu.menuType==0){
				$li.push('<li>');
				$li.push('<a href="javascript:;">');
				$li.push('	<i class="'+menu.icon+'" aria-hidden="true"></i>');
				$li.push('	<cite>'+menu.title+'</cite>');
				$li.push('	<i class="iconfont nav_right">&#xe697;</i>');
				$li.push('</a>');
				if(menu.children){
					$li.push('<ul class="sub-menu">');
					$li.push(buildTreeArray(menu.children).join(""));
					$li.push('</ul>');
				}
				$li.push('</li>');
			}
			$('#side-nav #nav').append($li.join(""));
		}
	});
});
/***
 * 递归方式，构建树形菜单
 * @param childArr
 * @returns
 */
function buildTreeArray(childArr){
	var $li = new Array();
	for(var i = 0;i<childArr.length;i++){
		var chd = childArr[i] ;
		//目录 
		$li.push('<li>');
		if(chd.menuType==0){
			$li.push('	<a href="javascript:;">');
			$li.push('		<i class="'+chd.icon+'" aria-hidden="true"></i>');
			$li.push('		<cite>'+chd.title+'</cite>');
			$li.push('		<i class="iconfont nav_right">&#xe697;</i>');
			$li.push('	</a>');
		}else{
			$li.push('	<a href="javascript:;"');
			if(chd.href!=undefined && chd.href!=""){
				$li.push(' onclick="xadmin.add_tab(\''+chd.title+'\',\''+layui.cache['contentPath']+chd.href+'\')"');	
			}
			$li.push('>'); //
			if(chd.icon!=undefined && chd.icon!="" && chd.icon!=null){
				$li.push('	<i class="'+chd.icon+'" aria-hidden="true"></i>');
			}else{
				$li.push('	<i class="fa fa-copyright" aria-hidden="true" ></i>'); //默认图标
			}
			$li.push('			<cite>'+chd.title+'</cite>');
			$li.push('	</a>');
		}
		if(chd.children){
			$li.push('<ul class="sub-menu">');
			$li.push(buildTreeArray(chd.children).join(""));
			$li.push('</ul>');
		}
		$li.push('</li>');
	}
	return $li;
}