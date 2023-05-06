<!--邮件发送页面ftl-->
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
        <link rel="stylesheet" type="text/css" href="/admin/X-admin-2.2/css/formSelects-v4.css" />
        <script type="text/javascript" src="/admin/X-admin-2.2/lib/layui/layui.js" charset="utf-8"></script>
        <script type="text/javascript" src="/admin/X-admin-2.2/js/xadmin.js"></script>
        <script src="/admin/X-admin-2.2/js/formSelects-v4.js" type="text/javascript" charset="utf-8"></script>
      
        <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
        <!--[if lt IE 9]>
            <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
            <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
        <![endif]--></head>
    
    <body>
    	 <div class="x-nav">
            <span class="layui-breadcrumb">
                <a href="javascript:void(0);">${parentMenu.name!""}</a>
                <a>
                	<cite>${currentMenu.name!""}</cite>
                </a>
            </span>
            <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" onclick="location.reload()" title="刷新">
                <i class="layui-icon layui-icon-refresh" style="line-height:30px"></i>
            </a>
        </div>
        <div class="layui-fluid">
            <div class="layui-row">
                <form class="layui-form">
                	<h1 style="text-align:center">邮件发送中心</h1><br>
                    <div class="layui-form-item">
                        <label for="receivers" class="layui-form-label">
                            <span class="x-red">*</span>收件人：</label>
                        <div class="layui-input-inline"  style="width:500px;">
	                        <select name="receivers" xm-select="selectId" required="" lay-verify="required">
	                        	<#if Receivers?size gt 0>
	                        		<#list Receivers as receiver>
							    		<option value="${receiver.id!""}">${receiver.name!""}</option>
							    	</#list>
							    </#if>
							</select>
                        </div>
                    </div>
                    
                    <div class="layui-form-item">
                        <label for="title" class="layui-form-label">
                            <span class="x-red">*</span>主题：</label>
                        <div class="layui-input-inline">
	                       <div class="layui-input-inline">
                            	<input type="text"  style="width:500px;" id="title" name="title" required="" lay-verify="required" autocomplete="off" class="layui-input">
                        	</div>
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
				        	</table>
				        </div>
                    </div>
                    
                     <div class="layui-form-item">
                        <label for="title" class="layui-form-label">
                        		 </label>
                         <div class="layui-input-block">
                              <a class="layui-btn" onclick="uploadAttachment();">
					               <i class="icon iconfont">&#xe6c0;</i>&nbsp;上传附件
					          </a>
					         &nbsp;<span class="x-red">一封邮件最多只能包含三个附件！单个附件大小不要超过200MB！</span>
				        </div>
                    </div>
                    
                	<div class="layui-form-item layui-form-text">
			            <label for="content" class="layui-form-label">邮件正文：</label>
			            <div class="layui-input-block">
			                <textarea id="content" name="content" style="height:400px;"></textarea>
			            </div>
			        </div>
        
	        		<div class="layui-form-item">
			            <label for="L_repass" class="layui-form-label"></label>
			            <button class="layui-btn" lay-filter="send" lay-submit="">
			               <i class="icon iconfont">&#xe71d;</i>&nbsp;发送
			            </button>
		            </div>
				</form>
        </div>
    </div>
    <input type="file" id="attachment-file" style="display:none;" onchange="upload()">
    <script type="text/javascript" charset="utf-8" src="/ueditor/ueditor.config.js"></script>
    <!-- 编辑器源码文件 -->
    <script type="text/javascript" charset="utf-8" src="/ueditor/ueditor.all.js"></script>
    <script type="text/javascript" charset="utf-8" src="/ueditor/lang/zh-cn/zh-cn.js"></script>
    
    
    <script type="text/javascript">
        var ue = UE.getEditor('content');
		//UEditor编辑器上传邮件内容
		UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
		UE.Editor.prototype.getActionUrl = function(action) {
		    if (action == 'uploadimage' || action == 'uploadscrawl' || action == 'uploadimage') {
		        return 'http://localhost:8080/ueditor/imgUpdate'; //在这里返回我们实际的上传图片地址
		    } else {
		        return this._bkGetActionUrl.call(this, action);
		    }
		}
        
        layui.use(['form', 'layer'],
            function() {
                $ = layui.jquery;
                var form = layui.form,
                layer = layui.layer;
                
                //监听提交
                form.on('submit(send)',
                function(data) {
                	var sendForm = data.field;
                	var i = -1;
                	$('#attachment_table').find('tr').each(function(){
                	 	 i++;
			             var t = $(this).attr('id');
						 if(i == 1){
			                 sendForm.attachmentOne = t;
						 }else if(i == 2){
						 	 sendForm.attachmentTwo = t;
						 }else if(i == 3){
						 	 sendForm.attachmentThree = t;
						 }
			         });
                	if(i >= 4){
                		layer.alert("一封邮件最多只能包含三个附件！", {icon: 5}); 
                	}
                	console.log(sendForm);
                	
                	
                	$.ajax({
					url:'/admin/mail/send_mail',
					type:'post',
					dataType:'json',
					data:sendForm,
					success:function(data){
						if(data.code == 0){
							layer.alert(data.msg, {icon: 6},function(){window.location.reload();}); 
						}else{
							layer.alert(data.msg, {icon: 5}); 
						}
					},
					error:function(){
						layer.alert('网络错误！发送邮件失败！', {icon: 5}); 
					}
				  });//end ajax
                	
                    return false;
                    
                });//end function(data)
                
                
               

            });//end function()
            
            
            //移除附件
            function remove(id){
            	$.ajax({
					url:'/admin/mail/remove_attachment',
					type:'post',
					dataType:'json',
					data:{Id:id},
					success:function(data){
						if(data.code == 0){
							layer.alert(data.msg, {icon: 6}); 
		                    $('#attachment_table').find('tr').each(function(){
					            var t = $(this).attr('id');
					            if(t == id){
					                $(this).remove();
					            }
					        });
						}else{
							layer.alert(data.msg, {icon: 5}); 
						}
					},
					error:function(){
						layer.alert('网络错误！删除附件失败！', {icon: 5}); 
					}
				  });//end ajax
            }
           
          
          //打开上传附件的窗口
          function uploadAttachment(){
				$("#attachment-file").click();
		  }
	   
	   	  //上传附件
		  function upload(){
				if($("#attachment-file").val() == '')return;
				//new FormData():异步上传二进制文件。
				var formData = new FormData();
				//取出所选附件中的第一个
				formData.append('attachment',document.getElementById('attachment-file').files[0]);
				var myMsg = layer.msg('正在上传中...请稍后....', {
			             shade: 0.4,
			             time:false //取消自动关闭
			    });
				$.ajax({
					url:'/upload/upload_attachment',
					type:'post',
					data:formData,
					//用ajax上传文件时候，必须设置contentType:false,processData:false
					contentType:false,
					processData:false,
					success:function(data){
						if(data.code == 0){
							layer.close(myMsg);//手动关闭
							layer.alert("文件："+data.data.name+"，上传成功！", {icon: 6}); 
							var html = '';
							html += '<tr id="'+data.data.id+'">';
							html += '<td>'+data.data.name+'('+data.data.size+'KB)</td>';
							html += '<td style="text-align:center">';
							html += '<a class="layui-btn layui-btn layui-btn-xs" href="/upload/download_file?id='+data.data.id+'" >';
							html += '<i class="icon iconfont">&#xe714;</i>&nbsp;下载</a>';
							html += '<a class="layui-btn-danger layui-btn layui-btn-xs"  onclick="remove('+data.data.id+');" >';
							html += '<i class="layui-icon">&#xe640;</i>删除</a></td></tr>'
           	 				$("#attachment_table").append(html);
						}else{
							layer.alert(data.msg, {icon: 5}); 
						}
						$("#attachment-file").val("");
					},
					error:function(data){
						layer.alert('上传失败！请上传正确格式或者大小的文件！', {icon: 5}); 
						$("#attachment-file").val("");
					}
				});
			}
			
			
	                      
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