<!--个人信息页面ftl-->
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
                	<input type="hidden" name="id" value="${loginedAdmin.id!""}" />
                    <div class="layui-form-item">
                        <label for="name" class="layui-form-label">
                            <span class="x-red">*</span>管理员名称：</label>
                        <div class="layui-input-inline">
                            <input type="text" id="name" name="name" value="${loginedAdmin.name!""}" required="" lay-verify="required" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    
                    <div class="layui-form-item">
                        <label for="password" class="layui-form-label">
                            <span class="x-red">*</span>管理员密码：</label>
                        <div class="layui-input-inline">
                            <input type="text" id="password" name="password" value="${loginedAdmin.password!""}" required="" lay-verify="required" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    
                    <div class="layui-form-item">
                        <label for="headPic" class="layui-form-label">
                        	管理员头像：</label>
                       	<img src="/photo/view?filename=${loginedAdmin.headPic!""}" id="preview-headPic" style="width:90px;height:70px;padding-right:10px;">
                       	<input type="hidden" id="headPic" name="headPic" value="${loginedAdmin.headPic!""}" >
                        <a class="layui-btn" onclick="uploadPhoto();">
			               <i class="icon iconfont">&#xe6a8;</i>&nbsp;上传头像
			            </a>
                    </div>
                    
                   
                    <div class="layui-form-item">
                        <label for="sex" class="layui-form-label">
                    		管理员性别：</label>
                        <div class="layui-input-inline">
                            <select id="sex" name="sex" class="valid">
                            	<#if loginedAdmin.sex == 1>
                            		<option value="3">未知</option>
	                                <option selected="selected" value="1">男</option>
	                                <option value="2">女</option>
                            	<#elseif loginedAdmin.sex == 2>
                            		<option value="3">未知</option>
	                                <option value="1">男</option>
	                                <option selected="selected" value="2">女</option>
                            	<#else>
                            		<option selected="selected" value="3">未知</option>
	                                <option value="1">男</option>
	                                <option value="2">女</option>
                            	</#if>
                            </select>
                        </div>
                    </div>
                    
                    <div class="layui-form-item">
                        <label for="mobile" class="layui-form-label" style="width:90px;padding-left:5px;">
                            <span class="x-red">*</span>管理员手机号：</label>
                        <div class="layui-input-inline">
                            <input type="text" id="mobile" name="mobile" value="${loginedAdmin.mobile!""}" onkeyup="value=value.replace(/^0(0+)|[^\d]+/g,'')" required="" lay-verify="required" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    
                   <div class="layui-form-item">
                        <label for="address" class="layui-form-label">
                            	管理员住址：</label>
                        <div class="layui-input-inline">
                            <input type="text" id="address" name="address" value="${loginedAdmin.address!""}" autocomplete="off" class="layui-input">
                        </div>
                   </div>
                    
        
	        		<div class="layui-form-item">
			            <label for="L_repass" class="layui-form-label"></label>
			            <button class="layui-btn" lay-filter="save" lay-submit="">
			               <i class="icon iconfont">&#xe747;</i>&nbsp;保存
			            </button>
		            </div>
				</form>
        </div>
    </div>
	<input type="file" id="photo-file" style="display:none;" onchange="upload()">
        <script type="text/javascript">
        layui.use(['form', 'layer'],
            function() {
                $ = layui.jquery;
                var form = layui.form,
                layer = layui.layer;
                //监听提交
                form.on('submit(save)',
                function(data) {
                	var saveForm = data.field;
                	console.log(saveForm);
                	//ajax异步提交数据
              		$.ajax({
						url:'/admin/system/save_person_info',
						type:'post',
						dataType:'json',
						data:saveForm,
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
							layer.alert('网络错误！保存个人信息失败！', {icon: 5}); 
						}
					});//end ajax
                   	
                    return false;
                    
                });//end function(data)

            });//end function()
            
            
	        function uploadPhoto(){
				$("#photo-file").click();
			}
             
            //上传图片
			function upload(){
				if($("#photo-file").val() == '')return;
				//new FormData():异步上传二进制文件。
				var formData = new FormData();
				//取出所选图片中的第一张
				formData.append('photo',document.getElementById('photo-file').files[0]);
				$.ajax({
					url:'/upload/upload_photo',
					type:'post',
					data:formData,
					//用ajax上传文件时候，必须设置contentType:false,processData:false
					contentType:false,
					processData:false,
					success:function(data){
						if(data.code == 0){
							$("#preview-headPic").attr('src','/photo/view?filename=' + data.data);
							$("#headPic").val(data.data);
							layer.alert("上传成功！", {icon: 6}); 
						}else{
							layer.alert(data.msg, {icon: 5}); 
						}
					},
					error:function(data){
						layer.alert('上传失败！请上传正确格式或者大小的文件！', {icon: 5}); 
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