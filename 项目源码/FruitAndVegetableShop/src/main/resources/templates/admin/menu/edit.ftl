<!--菜单编辑页面ftl-->
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
                	<input type="hidden" name="id" value="${editMenu.id!""}" />
                    <div class="layui-form-item">
                        <label for="username" class="layui-form-label">
                            <span class="x-red">*</span>菜单名称：</label>
                        <div class="layui-input-inline">
                            <input type="text" id="name" name="name" value="${editMenu.name!""}" required="" lay-verify="required" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    
                    <div class="layui-form-item">
                        <label for="sort" class="layui-form-label">
                            <span class="x-red">*</span>菜单排序：</label>
                        <div class="layui-input-inline">
                            <input type="text" id="sort" name="sort" onkeyup="value=value.replace(/^0(0+)|[^\d]+/g,'')" required="" value="${editMenu.sort!""}" lay-verify="required" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    
                    <div class="layui-form-item">
                        <label for="url" class="layui-form-label">
                            	菜单路径：</label>
                        <div class="layui-input-inline">
                            <input type="text" id="url" name="url" value="${editMenu.url!""}" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    
                   
                    <div class="layui-form-item">
                        <label for="parentId" class="layui-form-label">
                    		上级菜单：</label>
                        <div class="layui-input-inline">
                            <select id="parentId" name="parentId" class="valid">
                                <option value="0">无</option>
                                <optgroup label="一级菜单">
	                                <#if FirstMenus?size gt 0>
					 					<#list FirstMenus as firstMenu>
					 						<#if firstMenu.id == editMenu.parentId>
					 							<option selected="selected" value="${firstMenu.id!""}">${firstMenu.name!""}</option>
					 						<#else>
					 							<option value="${firstMenu.id!""}">${firstMenu.name!""}</option>
					 						</#if>
	                                	</#list>
	                               	</#if>
                               	</optgroup>
                               	<optgroup label="二级菜单">
	                               	<#if SecondMenus?size gt 0>
					 					<#list SecondMenus as secondMenu>
					 						<#if secondMenu.id == editMenu.parentId>
					 							<option selected="selected" value="${secondMenu.id!""}">${secondMenu.name!""}</option>
					 						<#else>
					 							<option value="${secondMenu.id!""}">${secondMenu.name!""}</option>
					 						</#if>
	                                	</#list>
	                               	</#if>
                               	</optgroup>
                            </select>
                        </div>
                    </div>
                    
                   <div class="layui-form-item">
                        <label for="icon" class="layui-form-label">
                        	<span class="x-red">*</span>菜单图标：</label>
                        <div class="layui-input-inline">
                            <input type="text" id="icon" name="icon" readonly="readonly" autocomplete="off" required="" lay-verify="required" class="layui-input">
                        </div>
                        <a class="layui-btn" onclick="xadmin.open('菜单图标选择','/admin/menu/icon')">
			               <i class="icon iconfont">&#xe6b9;</i>&nbsp;选择图标
			            </a>
                    </div>
                    
                   <div class="layui-form-item">
                    	  <label for="state" class="layui-form-label" style="padding-top:3px;">
                    		     菜单状态：
                          </label>
                            
                         <div class="layui-input-inline">
                         	<#if editMenu.state == 1>
                         		<input type="checkbox" name="switch" method="saveState" lay-text="开启|停用"  checked lay-skin="switch">
                         	<#else>
                         		<input type="checkbox" name="switch" method="saveState" lay-text="开启|停用"  lay-skin="switch">
                         	</#if>
                  		 </div>
                    </div>
        
	        		<div class="layui-form-item">
			            <label for="L_repass" class="layui-form-label"></label>
			            <button class="layui-btn" lay-filter="edit" lay-submit="">
			               <i class="icon iconfont">&#xe747;</i>&nbsp;保存
			            </button>
		            </div>
				</form>
        </div>
        </div>
        <script type="text/javascript">
         //状态更换
		 var state = ${editMenu.state!""}; //1：开启；2：停用
         function saveState(){
          	if(state == 1){
          		state = 2;
          	}else{
          		state = 1;
          	}
          }
         //图标初始化
         window.onload=function(){
         	$("#icon").val('${editMenu.icon!""}');
         }
        layui.use(['form', 'layer'],
            function() {
                $ = layui.jquery;
                var form = layui.form,
                layer = layui.layer;
                //监听提交
                form.on('submit(edit)',
                function(data) {
                	var editForm = data.field;
                	editForm.state = state
                	console.log(editForm);
                   	//ajax异步提交数据
              		$.ajax({
						url:'/admin/menu/edit',
						type:'post',
						dataType:'json',
						data:editForm,
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
							layer.alert('网络错误！编辑菜单失败！', {icon: 5}); 
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