<!--前台系统首页页面-->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <#include "../common/header.ftl"/>
    <!--[if IE 7]>
    <link rel="stylesheet" href="/home/css/font-awesome-ie7.min.css">
    <![endif]-->
    <title>水果蔬菜商城首页</title>
</head>

<body>
<!--顶部样式-->
<#include "../common/top_menu.ftl"/>
<!--幻灯片样式-->
<div class="AD_bg_img">
    <!--幻灯片样式-->
    <div class="slider">
        <div id="slideBox" class="slideBox">
            <div class="hd">
                <ul></ul>
            </div>
            <div class="bd">
                <ul>
                    <li><img src="/home/images/AD_img.png" /></li>
                </ul>
            </div>
            <a class="prev" href="javascript:void(0)"></a>
            <a class="next" href="javascript:void(0)"></a>
        </div>
        <script type="text/javascript">
            jQuery(".slideBox").slide({titCell:".hd ul",mainCell:".bd ul",autoPlay:true,autoPage:true,interTime:9000});
        </script>
    </div>
</div>
<!--最新上架产品样式-->
<div class="new_products clearfix">
    <div class="mian">
        <div id="slideBox_list" class="slideBox_list">
            <div class="hd">
                <div class="title_name"></div>
                <div class="list_title"><ul><li><h3>01</h3><a href="javascript:void(0);">水果</a></li><li><h3>02</h3><a href="javascript:void(0);">蔬菜</a></li></ul></div>
            </div>

            <div class="bd">
                <div class="fixed_title_name">
                    <span>新鲜</span>
                </div>
                <ul class="">
                    <li class="advertising">
                        <div class="AD1"><img src="/home/images/product_AD_10.png" /></div>
                        <div class="AD2"><img src="/home/images/product_AD_14.png" /><img src="/home/images/product_AD_07.png" /></div>
                        <div class="AD3"><img src="/home/images/product_AD_11.png" /></div>
                    </li>
                    <li class="advertising">
                        <div class="AD1"><img src="/home/images/product_AD_10.png" /></div>
                        <div class="AD2"><img src="/home/images/product_AD_07.png" /><img src="/home/images/product_AD_14.png" /></div>
                        <div class="AD3"><img src="/home/images/product_AD_11.png" /></div>
                    </li>
                </ul>
            </div>
        </div>
        <script type="text/javascript">jQuery(".slideBox_list").slide({mainCell:".bd ul"});</script>
    </div>
</div>
<!--底部样式-->
<#include "../common/footer.ftl"/>
</body>
</html>
