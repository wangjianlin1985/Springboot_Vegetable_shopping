<!--角色权限页面ftl-->
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
    <![endif]-->
  </head>
  
  <body>
    <div class="layui-fluid">
        <div class="layui-row">
            <form action="" method="post" class="layui-form layui-form-pane">
                <div class="layui-form-item layui-form-text">
                    <label class="layui-form-label">
            			拥有权限
                    </label>
                    
                    
                    <table  class="layui-table layui-input-block">
                        <tbody>
						    <#if FirstMenus?size gt 0>
                            	<#list FirstMenus as firstMenu>
                            		<tr>
		                                <td style="width:150px;">
		                                    <input id="${firstMenu.id!""}" style="vertical-align:middle;" type="checkbox" name="id[]" lay-skin="primary" lay-filter="father" value="${firstMenu.id!""}">
		                                    <span style="vertical-align:middle;">${firstMenu.name!""}</span>
		                                </td>
		                                
		                                <td style="padding-top:2px;padding-bottom:0px;padding-left:0px;padding-right:0px;">
		                                    <div class="layui-input-block">
	                                    	    <table  class="layui-table layui-input-block">
	                                    		   
		                                    		 <#if SecondMenus?size gt 0>
														<#list SecondMenus as secondMenu>
															<#if secondMenu.parentId == firstMenu.id>
																<tr>
																	<td style="width:150px;">
																		<input id="${secondMenu.id!""}" style="vertical-align:middle;" name="id[]" lay-skin="primary" type="checkbox" value="${secondMenu.id!""}" lay-filter="father">
																		<span style="vertical-align:middle;">${secondMenu.name!""}</span>
																	</td>
																	<td>
																		<#if ThirdMenus?size gt 0>
																			<#list ThirdMenus as thirdMenu>
																				<#if thirdMenu.parentId == secondMenu.id>
																					<input id="${thirdMenu.id!""}" style="vertical-align:middle;" name="id[]" lay-skin="primary" type="checkbox" value="${thirdMenu.id!""}">
																					<span style="vertical-align:middle;">${thirdMenu.name!""}</span>
																				</#if>
																			</#list>
																		</#if>
																	 </td>
																</tr>
																
															</#if>
														</#list>
													</#if>
													
		                                    	</table>
		                                    </div>
		                                </td>
		                           </tr>
                            	</#list>
                            </#if>
                        </tbody>
                    </table>
                </div>
              
	            <div class="layui-form-item" style="text-align:center;">
	            <button class="layui-btn" lay-submit="" lay-filter="save">保存</button>
	          </div>
	        </form>
	    </div>
	</div>
    <script>
        layui.use(['form','layer'], function(){
            $ = layui.jquery;
          var form = layui.form
          ,layer = layui.layer;
        
        	window.onload=function(){
        		<#if AuthorityList?size gt 0>
        			<#list AuthorityList as authority>
        				$("#"+${authority.menuId!""}).prop("checked", true);
        			</#list>
        		</#if>
        		form.render(); 
        	}

          //监听提交
          form.on('submit(save)', function(data){
            console.log(data);
            var saveForm = data.field;
            var ids = "";
            for(var p in saveForm){//遍历json对象的每个key/value对,p为key
			 ids += saveForm[p];
			 ids += ",";
            }
            ids = ids.substring(0,ids.length-1); //权限ID集合
            //ajax提交数据
            roleId = ${id!""}; //角色ID
            $.ajax({
					url:'/admin/role/save_authority',
					type:'post',
					dataType:'json',
					data:{ids:ids,roleId:roleId},
					success:function(data){
						if(data.code == 0){
							layer.alert(data.msg, {icon: 6},function(){parent.parent.window.location.reload();}); 
						}else{
							layer.alert(data.msg, {icon: 5}); 
						}
					},
					error:function(){
						layer.alert('网络错误！保存角色权限失败！', {icon: 5}); 
					}
			});
            
            return false;
          });


        form.on('checkbox(father)', function(data){

            if(data.elem.checked){
                $(data.elem).parent().siblings('td').find('input').prop("checked", true);
                form.render(); 
            }else{
               $(data.elem).parent().siblings('td').find('input').prop("checked", false);
                form.render();  
            }
        });
          
          
        });
    </script>
    <script>var _hmt = _hmt || []; (function() {
        var hm = document.createElement("script");
        hm.src = "https://hm.baidu.com/hm.js?b393d153aeb26b46e9431fabaf0f6190";
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(hm, s);
      })();</script>
  </body>

</html>