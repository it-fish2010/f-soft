<#-- 加载显示 -->
<div class="layui-card">
    <div class="layui-card-header">系统信息</div>
    <div class="layui-card-body ">
        <table class="layui-table">
            <tbody>
                <tr>
                    <th>操作系统</th>
                    <td>${server.os}</td></tr>
                <tr>
                    <th>虚拟机名称</th>
                    <td>${server.javaVmName}</td>
                </tr>
                <tr>
                    <th>虚拟机版本号</th>
                    <td>${server.specificationVersion} (${server.javaVmVersion})</td>
                </tr>
                <tr>
                    <th>虚拟机供应商</th>
                    <td>${server.javaVmVendor}</td>
                </tr>
                <tr>
                    <th>运行环境</th>
                    <td>${server.specificationName}</td>
                </tr>
                <tr>
                    <th>服务器名称</th>
                    <td>${server.hostName}</td>
                </tr>
            </tbody>
        </table>
    </div>
</div>