<!--邮件预览页面ftl-->
<!DOCTYPE html>
<html class="x-admin-sm">
    
    <head>
        <meta charset="UTF-8">
        <title>欢迎页面-X-admin2.2</title>
        <meta name="renderer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
        <link rel="stylesheet" href="/admin/X-admin-2.2/css/font.css">
        <link rel="stylesheet" href="/admin/X-admin-2.2/css/xadmin.css">
        <script type="text/javascript" src="/admin/X-admin-2.2/lib/layui/layui.js" charset="utf-8"></script>
        <script type="text/javascript" src="/admin/X-admin-2.2/js/xadmin.js"></script>
        <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
        <!--[if lt IE 9]>
            <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
            <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
        <![endif]--></head>
    
    <body>
        <div class="layui-fluid">
            <div class="layui-row">
                <form class="layui-form">
                
                    <div class="layui-form-item">
                        <label for="senderId" class="layui-form-label">
                            	发件人：</label>
                        <div class="layui-input-block">
                            <table class="layui-table">
                            	<tr>
                            		<#if allAdmins?size gt 0>
                            			<#list allAdmins as admin>
                            				<#if admin.id == previewMail.senderId>
                            					<td>${admin.name!""}</td>
                            				</#if>
                            			</#list>
                            		</#if>
                            	</tr>
				        	</table>
				        </div>
                    </div>
                    
                     <div class="layui-form-item">
                        <label for="senderId" class="layui-form-label">
                            	收件人：</label>
                        <div class="layui-input-block">
                            <table class="layui-table">
                            	<tr>
                            		<#if allAdmins?size gt 0>
                            			<#list allAdmins as admin>
                            				<#if admin.id == previewMail.receiverId>
                            					<td>${admin.name!""}</td>
                            				</#if>
                            			</#list>
                            		</#if>
                            	</tr>
				        	</table>
				        </div>
                    </div>
                    
                    <div class="layui-form-item">
                        <label for="title" class="layui-form-label">
                            	邮件主题：</label>
                        <div class="layui-input-block">
                            <table class="layui-table">
                            	<tr>
                            		<td>${previewMail.title!""}</td>
                            	</tr>
				        	</table>
				        </div>
                    </div>
                    
                    <div class="layui-form-item">
                        <label for="title" class="layui-form-label">
                            	时间：</label>
                        <div class="layui-input-block">
                            <table class="layui-table">
                            	<tr>
                            		<td>${previewMail.createTime?string('yyyy-MM-dd HH:mm:ss')!""}</td>
                            	</tr>
				        	</table>
				        </div>
                    </div>
                    
                    <div class="layui-form-item">
                        <label for="title" class="layui-form-label">
                            	附件：</label>
                         <div class="layui-input-block">
                            <table class="layui-table" id="attachment_table">
                            	<thead>
			                		<th style="text-align:center">附件名称</th>
							        <th style="text-align:center;width:150px;">操作</th>
							    </thead>
							    <#if previewMail.attachmentOne??>
								    <tr>
								    	<#if allAttachments?size gt 0>
								    		<#list allAttachments as attachment>
								    			<#if attachment.id == previewMail.attachmentOne>
								    				<td>${attachment.name!""}(${attachment.size!""}KB)</td>
								    				<td style="text-align:center">
				                            			<a class="layui-btn" href="/upload/download_file?id=${attachment.id!""}">
											               <i class="icon iconfont">&#xe714;</i>&nbsp;下载
											            </a>
				                            		</td>
								    			</#if>
								    		</#list>
								    	</#if>
	                            	</tr>
                            	</#if>
                            	<#if previewMail.attachmentTwo??>
                            		<tr>
								    	<#if allAttachments?size gt 0>
								    		<#list allAttachments as attachment>
								    			<#if attachment.id == previewMail.attachmentTwo>
								    				<td>${attachment.name!""}(${attachment.size!""}KB)</td>
								    				<td style="text-align:center">
				                            			<a class="layui-btn" href="/upload/download_file?id=${attachment.id!""}">
											               <i class="icon iconfont">&#xe714;</i>&nbsp;下载
											            </a>
				                            		</td>
								    			</#if>
								    		</#list>
								    	</#if>
                            	    </tr>	
                            	</#if>	
                            	<#if previewMail.attachmentThree??>
	                            	<tr>
								    	<#if allAttachments?size gt 0>
								    		<#list allAttachments as attachment>
								    			<#if attachment.id == previewMail.attachmentThree>
								    				<td>${attachment.name!""}(${attachment.size!""}KB)</td>
								    				<td style="text-align:center">
				                            			<a class="layui-btn" href="/upload/download_file?id=${attachment.id!""}">
											               <i class="icon iconfont">&#xe714;</i>&nbsp;下载
											            </a>
				                            		</td>
								    			</#if>
								    		</#list>
								    	</#if>
	                            	</tr>
                            	</#if>	
				        	</table>
				        </div>
                    </div>
                    
                	<div class="layui-form-item layui-form-text">
			            <label for="description" class="layui-form-label">邮件正文：</label>
			            <div class="layui-input-block">
			                <table class="layui-table">
                            	<tr>
                            		<td>
                            			${previewMail.content!""}
                            		</td>
                            	</tr>
				        	</table>
			            </div>
			        </div>
				</form>
        </div>
    </div>
        <script type="text/javascript">
        layui.use(['form', 'layer'],
            function() {
                $ = layui.jquery;
                var form = layui.form,
                layer = layui.layer;
                //监听提交
                form.on('submit(add)',
                function(data) {
                	var addForm = data.field;
                	console.log(addForm);
                	//ajax异步提交数据
              		$.ajax({
						url:'/admin/role/add',
						type:'post',
						dataType:'json',
						data:addForm,
						success:function(data){
							if(data.code == 0){
								 layer.alert(data.msg, {
				                        icon: 6
				                    },
				                    function() {
				                        // 获得frame索引
				                        var index = parent.layer.getFrameIndex(window.name);
				                        //关闭当前frame
				                        parent.layer.close(index);
				                        parent.parent.window.location.reload();
				                 });
							}else{
								layer.alert(data.msg, {icon: 5}); 
							}
						},
						error:function(){
							layer.alert('网络错误！添加角色失败！', {icon: 5}); 
						}
					});//end ajax
                   	
                    return false;
                    
                });//end function(data)

            });//end function()
            
            
	      
	                      
        </script>
        <script>var _hmt = _hmt || []; (function() {
                var hm = document.createElement("script");
                hm.src = "https://hm.baidu.com/hm.js?b393d153aeb26b46e9431fabaf0f6190";
                var s = document.getElementsByTagName("script")[0];
                s.parentNode.insertBefore(hm, s);
            })();
       </script>
    </body>

</html>