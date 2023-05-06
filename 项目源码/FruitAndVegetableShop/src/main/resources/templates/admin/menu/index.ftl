<!--菜单页面ftl-->
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
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="/admin/X-admin-2.2/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="/admin/X-admin-2.2/js/xadmin.js"></script>
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
      <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
      <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

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
            <div class="layui-row layui-col-space15">
                <div class="layui-col-md12">
                    <div class="layui-card">
                        <div class="layui-card-body ">
                                <div class="layui-input-inline layui-show-xs-block">
	                                <#if onThirdMenus?size gt 0>
	                                	<#list onThirdMenus as onThirdMenu>
	                                		<#if onThirdMenu.parentId == currentMenu.id>
		                                		<a onclick="${onThirdMenu.url!""}">
		                                			<#if onThirdMenu.url == "openEdit();">
				                                    	<button class="layui-btn layui-btn-warm layui-btn-xs"><i class="icon iconfont">${onThirdMenu.icon!""}</i>&nbsp;${onThirdMenu.name!""}</button>
				                                	<#elseif onThirdMenu.url == "deleteMenu();">
			                                			<button class="layui-btn-danger layui-btn layui-btn-xs"><i class="icon iconfont">${onThirdMenu.icon!""}</i>&nbsp;${onThirdMenu.name!""}</button>
				                                	<#else>
				                                		<button class="layui-btn"><i class="icon iconfont">${onThirdMenu.icon!""}</i>&nbsp;${onThirdMenu.name!""}</button>
				                                	</#if>
				                                </a>
			                                </#if>
	                                	</#list>
	                               	</#if>
                                </div>
                                
                            <hr>
                            <blockquote class="layui-elem-quote">Tips：菜单排序的值在同级别中越大，排序越靠前哦！</blockquote>
                        </div>
                        <div class="layui-card-body ">
                            <table class="layui-table layui-form">
                              <thead>
                                  <th width="20" style="text-align:center">
                                  </th>
                                  <th width="40" style="text-align:center">菜单ID</th>
                                  <th style="text-align:center">菜单名称</th>
                                  <th width="40" style="text-align:center">菜单排序</th>
                                  <th width="40" style="text-align:center">菜单图标</th>
                                  <th width="80" style="text-align:center">菜单状态</th>
                                  <th width="300" style="text-align:center">菜单路径</th>
                              </thead>
                              <tbody class="x-cate">
                              
                              <#if FirstMenus?size gt 0>
				 				<#list FirstMenus as firstMenu>
	                                <tr cate-id='${firstMenu.id!""}' fid='${firstMenu.parentId!""}' >
	                                  <td style="text-align:center">
	                                    <input type="checkbox" name="" id="${firstMenu.id!""}" method="checkedId" lay-skin="primary">
	                                  </td>
	                                  <td style="text-align:center">${firstMenu.id!""}</td>
	                                  <td>
	                                    <i class="layui-icon x-show" status='false'>&#xe625;</i>
	                               		${firstMenu.name!""}
	                                  </td>
	                                  <td style="text-align:center">${firstMenu.sort!""}</td>
	                                  <td style="text-align:center"><i class="icon iconfont">${firstMenu.icon!""}</i></td>
	                                  <td style="text-align:center">
	                                  	<#if firstMenu.state == 1>
	                                    	<font color="green">开启</font>
	                                    <#else>
	                                    	<font color="red">停用</font>
	                                    </#if>
	                                  </td>
	                                  <td class="td-manage">
	                                   	  ${firstMenu.url!""}	
	                                  </td>
	                                </tr>
	                                
	                                <#if SecondMenus?size gt 0>
				 						<#list SecondMenus as secondMenu>
				 							<#if secondMenu.parentId == firstMenu.id>
					 							<tr cate-id='${secondMenu.id!""}' fid='${secondMenu.parentId!""}'>
				                                   <td style="text-align:center">
				                                     <input type="checkbox" name="" id="${secondMenu.id!""}" method="checkedId" lay-skin="primary">
				                                   </td>
				                                   <td style="text-align:center">${secondMenu.id!""}</td>
				                                   <td>
				                                     &nbsp;&nbsp;&nbsp;&nbsp;
				                                     <i class="layui-icon x-show" status='false'>&#xe625;</i>
				                                   	   ${secondMenu.name!""}
				                                   </td>
				                                   <td style="text-align:center">${secondMenu.sort!""}</td>
				                                   <td style="text-align:center"><i class="icon iconfont">${secondMenu.icon!""}</i></td>
				                                   <td style="text-align:center">
				                                   	 <#if secondMenu.state == 1>
				                                    	<font color="green">开启</font>
					                                 <#else>
					                                    <font color="red">停用</font>
					                                 </#if>
				                                   </td>
				                                   <td class="td-manage">
				                                   	   ${secondMenu.url!""}
				                                   </td>
					                             </tr>
					                             <#if ThirdMenus?size gt 0>
							 						<#list ThirdMenus as thirdMenu>
							 							<#if thirdMenu.parentId == secondMenu.id>
				 											 <tr cate-id='${thirdMenu.id!""}' fid='${thirdMenu.parentId!""}' >
								                                  <td style="text-align:center">
								                                    <input type="checkbox" name="" id="${thirdMenu.id!""}" method="checkedId" lay-skin="primary">
								                                  </td>
								                                  <td style="text-align:center">${thirdMenu.id!""}</td>
								                                  <td>
								                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								                                    ├${thirdMenu.name!""}
								                                  </td>
								                                  <td style="text-align:center">${thirdMenu.sort!""}</td>
								                                  <td style="text-align:center"><i class="icon iconfont">${thirdMenu.icon!""}</i></td>
								                                  <td style="text-align:center">
								                                  	 <#if thirdMenu.state == 1>
								                                  	 	<font color="green">开启</font>
								                                  	 <#else>
								                                  	 	<font color="red">停用</font>
								                                  	 </#if>
								                                  </td>
								                                  <td class="td-manage">
								                                 	   ${thirdMenu.url!""}
								                                  </td>
								                             </tr>
				                                        </#if>
				                                	</#list>
				                                 </#if>
					                             
					                             
					                         </#if>
	                                	</#list>
	                                </#if>
	                                
                                </#list>
                               </#if>
                                
                               
                                
                                
                               
                               
                              </tbody>
                            </table>
                        </div>
                      
                    </div>
                </div>
            </div>
        </div>
		<script>
          layui.use(['form'], function(){
            form = layui.form;
            
          });

          // 分类展开收起的分类的逻辑
          // 
          $(function(){
          //  $("tbody.x-cate tr[fid!='0']").hide();
            // 栏目多级显示效果
            $('.x-show').click(function () {
                if($(this).attr('status')=='true'){
                    $(this).html('&#xe625;'); 
                    $(this).attr('status','false');
                    cateId = $(this).parents('tr').attr('cate-id');
                    $("tbody tr[fid="+cateId+"]").show();
               }else{
                    cateIds = [];
                    $(this).html('&#xe623;');
                    $(this).attr('status','true');
                    cateId = $(this).parents('tr').attr('cate-id');
                    getCateId(cateId);
                    for (var i in cateIds) {
                        $("tbody tr[cate-id="+cateIds[i]+"]").hide().find('.x-show').html('&#xe623;').attr('status','true');
                    }
               }
            });
            
          })

          var cateIds = [];
          function getCateId(cateId) {
              $("tbody tr[fid="+cateId+"]").each(function(index, el) {
                  id = $(el).attr('cate-id');
                  cateIds.push(id);
                  getCateId(id);
              });
          }
          
          function changeState(i){
          	$.ajax({
				url:'/admin/menu/change_state',
				type:'post',
				dataType:'json',
				data:{id:i},
				success:function(data){
					if(data.code != 0){
						layer.alert(data.msg, {icon: 5},function(){parent.window.location.reload();}); 
					}
				},
				error:function(){
					layer.alert('网络错误！更改状态失败！', {icon: 5},function(){parent.window.location.reload();}); 
				}
			});
          }
          
          var ids = "";
          function checkedId(i){
          		if(ids == ""){
          			ids = i + ","
          		}else{
          			var flag = false; //判断有没有重复
          			ids = ids.substring(0,ids.length-1);
          			var arr = ids.split(',');
          			ids = "";
	          		for(var k=0;k<arr.length;k++){
	          			if(arr[k] != i){
	          				ids += arr[k];
	          				ids += ",";
	          			}else{
	          				flag = true;
	          			}
	          		}
	          		if(!flag){
	          			ids += i;
	          			ids += ",";
	          		}
          		
          		}
          }
          
          //打开编辑窗口
          function openEdit(){
          	  var id = ids.substring(0,ids.length-1);
          	  if(id.indexOf(",") != -1 || id == ""){
          	  	  layer.alert("请选择一条数据进行编辑！", {icon: 5}); 
          	  	  return;
          	  }
          	  xadmin.open('菜单编辑','/admin/menu/edit?id='+id,700,500);
          }
          
          //删除
          function deleteMenu(){
          	  var id = ids.substring(0,ids.length-1);
          	  if(id.indexOf(",") != -1 || id == ""){
          	   	  layer.alert("请选择一条数据进行删除！", {icon: 5}); 
          	  	  return;
          	  }
          	  layer.confirm('确认要删除<font color="red">菜单id='+id+'</font>的这条数据吗？',function(index){
                 $.ajax({
					url:'/admin/menu/delete',
					type:'post',
					dataType:'json',
					data:{id:id},
					success:function(data){
						if(data.code == 0){
							layer.alert(data.msg, {icon: 6},function(){parent.window.location.reload();}); 
						}else{
							layer.alert(data.msg, {icon: 5}); 
						}
					},
					error:function(){
						layer.alert('网络错误！删除菜单失败！', {icon: 5}); 
					}
				});
	          });
          }
          
          //打开添加菜单按钮窗口
          function openAddButton(){
          		 var id = ids.substring(0,ids.length-1);
	          	 if(id.indexOf(",") != -1 || id == ""){
	          	   	  layer.alert("请选择一条二级菜单数据进行添加按钮！", {icon: 5}); 
	          	  	  return;
	          	 }
          		 //判断所选id是哪个级别的菜单
          		 $.ajax({
					url:'/admin/menu/level',
					type:'post',
					dataType:'json',
					data:{id:id},
					success:function(result){
						if(result.data == 2){
							xadmin.open('菜单按钮添加','/admin/menu/add_button?id='+id,700,500);
						}else{
							layer.alert('请选择一条二级菜单数据进行添加按钮！', {icon: 5}); 
						}
					},
					error:function(){
						layer.alert('网络错误！添加菜单按钮失败！', {icon: 5}); 
					}
				});
          }
        </script>
    </body>
</html>
