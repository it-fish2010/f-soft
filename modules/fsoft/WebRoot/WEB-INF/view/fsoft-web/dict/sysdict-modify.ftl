<!DOCTYPE html>
<html>
    <head>
        <title>字典编辑-编辑</title>
 		<#include "fsoft-common.ftl" />
    </head>
    <body>
        <div class="layui-fluid">
	        <div class="layui-row">
	            <form class="layui-form layui-form-pane" action="" method="post">
	            	<input type="hidden" name="id" id="id" value="${model.id}"/>
	                <div class="layui-form-item">
	                    <label for="name" class="layui-form-label">
	                        <span class="x-red">*</span>编码
	                    </label>
	                    <div class="layui-input-inline">
	                        <input type="text" id="code" name="code" value="${model.code}"  required="" lay-verify="required" autocomplete="off" class="layui-input">
	                    </div>
	                </div>
	                <div class="layui-form-item">
	                    <label for="name" class="layui-form-label">
	                        <span class="x-red">*</span>名称
	                    </label>
	                    <div class="layui-input-inline">
	                        <input type="text" id="name" name="name" value="${model.name}" required="" lay-verify="required" autocomplete="off" class="layui-input">
	                    </div>
	                </div>
	                <div class="layui-form-item">
	                    <label for="desc" class="layui-form-label">字典类型</label>
	                    <div class="layui-input-inline">
	                        <select name="dictType" lay-filter="fsoft-dict-type">
	                    		<option value="1" <#if model.dictType==1>selected</#if> >列表</option>
	                    		<option value="2" <#if model.dictType==2>selected</#if> >树形</option>
	                    	</select>
	                    </div>
	                </div>
	                <div class="layui-col-md4 layui-col-md-offset4">
						<button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="save">
							<i class="fa fa-save" aria-hidden="true" ></i> 保存
						</button>
	                </div>
	            </form>
	        </div>
	    </div>
    </body>
    <script type="text/javascript">
	    layui.config({
	    	base: '${request.contextPath}/plugins/layui/extend/',
	    	contentPath: '${request.contextPath}'
	    });
	</script>
	<script type="text/javascript" src="${request.contextPath}/static/fsoft-web/dict/sysdict-modify.js?v=202002"></script>

</html>
