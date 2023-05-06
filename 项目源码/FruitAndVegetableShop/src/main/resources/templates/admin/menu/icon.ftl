<!--菜单添加图标页面ftl-->
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
    <script type="text/javascript" src="/admin/X-admin-2.2/js/icon.js"></script>
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
      <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
      <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
</style>
    <body>
       		<table class="layui-table" id="icon-table">
              	
            </table>
    </body>
    
   	<script>
   		function selectIcon(i){
   			
   			parent.$("#icon").val(iconArr[i]);
   			
            // 获得frame索引
            var index = parent.layer.getFrameIndex(window.name);
            //关闭当前frame
            parent.layer.close(index);
      
   		
   		}
   		
   		window.onload=function(){
   			 //一行5个图标
   			 var html = '';
   			 for(var i=0;i<iconArr.length;i++){
   			 	if(i%5 == 0){
   			 		html += '<tr style="text-align:center">';
   			 	}
   			 	html += '<td><a onclick="selectIcon('+i+');" style="cursor:pointer;"><i class="icon iconfont">'+iconArr[i]+'</i></a></td>';
   			 	if((i+1)%5 == 0){
   			 		html += '</tr>';
   			 	}
   			 }
   			 $("#icon-table").append(html);
   		}
   	
   	</script>
</html>
