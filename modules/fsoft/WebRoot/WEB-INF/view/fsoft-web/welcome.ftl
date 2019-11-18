<!DOCTYPE html>
<html>
    <head>
        <title>欢迎页面</title>
        <#include "fsoft-common.ftl" />
    </head>
    <body>
        <div class="layui-fluid">
            <div class="layui-row layui-col-space5">
                <div class="layui-col-sm6 layui-col-md3">
                    <div class="layui-card">
                        <div class="layui-card-header">
                        	用户总数 
                        	<span class="layui-badge layui-bg-blue layuiadmin-badge">全部</span>
                        </div>
                        <div class="layui-card-body">
                        	<span class="layui-badge">${user.userTotal}</span>
                            <p>启用:禁用
                                <span class="layuiadmin-span-color">${user.userStatusYes}:${user.userStatusNo}
                                    <i class="layui-inline layui-icon layui-icon-face-smile-b"></i>
                                </span>
                            </p>
                        </div>
                    </div>
                </div>
                <div class="layui-col-sm6 layui-col-md3">
                    <div class="layui-card">
                        <div class="layui-card-header">
                        	帐号状态情况 <span class="layui-badge layui-bg-cyan layuiadmin-badge">全部</span>
                        </div>
                        <div class="layui-card-body">
                            <p class="layuiadmin-big-font">${user.userLock}</p>
                            <p>新下载
                                <span class="layuiadmin-span-color">10%
                                    <i class="layui-inline layui-icon layui-icon-face-smile-b"></i>
                                </span>
                            </p>
                        </div>
                    </div>
                </div>
                <div class="layui-col-sm6 layui-col-md3">
                    <div class="layui-card">
                        <div class="layui-card-header">
                        	下载 <span class="layui-badge layui-bg-cyan layuiadmin-badge">月</span>
                        </div>
                        <div class="layui-card-body">
                            <p class="layuiadmin-big-font">33,555</p>
                            <p>新下载
                                <span class="layuiadmin-span-color">10%
                                    <i class="layui-inline layui-icon layui-icon-face-smile-b"></i>
                                </span>
                            </p>
                        </div>
                    </div>
                </div>
                <div class="layui-col-sm6 layui-col-md3">
                    <div class="layui-card">
                        <div class="layui-card-header">
                        	下载 <span class="layui-badge layui-bg-cyan layuiadmin-badge">月</span>
                        </div>
                        <div class="layui-card-body">
                            <p class="layuiadmin-big-font">33,555</p>
                            <p>新下载
                                <span class="layuiadmin-span-color">10%
                                    <i class="layui-inline layui-icon layui-icon-face-smile-b"></i>
                                </span>
                            </p>
                        </div>
                    </div>
                </div>
                <div class="layui-col-md6 layui-col-sm12">
                    <#include "/fsoft-web/welcome-server-info.ftl">
                </div>
                <div class="layui-col-md6 layui-col-sm12">
                    <div class="layui-card">
                        <div class="layui-card-header">开发团队</div>
                        <div class="layui-card-body ">
                            <table class="layui-table">
                                <tbody>
                                    <tr>
                                        <th>版权所有</th>
                                        <td>xuebingsi(xuebingsi)
                                            <a href="http://x.xuebingsi.com/" target="_blank">访问官网</a></td>
                                    </tr>
                                    <tr>
                                        <th>开发者</th>
                                        <td>马志斌(113664000@qq.com)</td></tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                 <div class="layui-col-md6 layui-col-sm12">
                    <div class="layui-card">
                        <div class="layui-card-header">开发团队</div>
                        <div class="layui-card-body ">
                            <table class="layui-table">
                                <tbody>
                                    <tr>
                                        <th>版权所有</th>
                                        <td>xuebingsi(xuebingsi)
                                            <a href="http://x.xuebingsi.com/" target="_blank">访问官网</a></td>
                                    </tr>
                                    <tr>
                                        <th>开发者</th>
                                        <td>马志斌(113664000@qq.com)</td></tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <script type="text/javascript">
	    layui.config({
	    	base: '${request.contextPath}'+'/plugins/build/js/',
	    	contentPath: '${request.contextPath}'
	    });
	</script>
</html>