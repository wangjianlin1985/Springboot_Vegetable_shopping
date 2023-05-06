<!--前台系统蔬菜商品页面-->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <#include "../common/header.ftl"/>
    <title>商品中心——蔬菜</title>
</head>

<body>
<!--顶部样式-->
<#include "../common/top_menu.ftl"/>
<!---->
<div class="AD_img"><img src="/home/images/AD_page_img_02.png"  width="100%"/></div>
<!--位置-->
<div class="Bread_crumbs">
    <div class="Inside_pages clearfix">
        <div class="right Search">
            <input id="search-content" type="text" value="${content!""}"  class="Search_Box"/>
            <input type="button" id="search-button" class="Search_btn"/>
        </div>
    </div>
</div>
<!--产品-->
<div class="Inside_pages">
    <!--蔬菜馆-->
    <div class="vegetables_Forum">
        <div class="title_style">
            <div class="title_name"><img src="/home/images/vegetables_img_40.png" /></div>
            <div class="title_info">
                <p class="title_x_name">[健康蔬菜小知识]</p>
                <p class="x_info">蔬菜有很多的种类，不过每种都含有很多营养成分，营养元素，利于身体健康。它的种类大致可分为根菜类，叶菜类，瓜类，蘑菇类以及豆类等。一般颜色深的蔬菜都含有铁和维生素A，颜色越深含量就越高。且里面的脂肪，糖类，蛋白质的含量少，即便是多吃也不会对身体造成负担。</p>
            </div>
            <div class="title_img"><img src="/home/images/title_p_img_32.png" /></div>
        </div>
        <div class="list_style">
            <ul class="clearfix">
                <#if PageInfo.list?size gt 0>
                    <#list PageInfo.list as product>
                        <li class="clearfix">
                            <div class="product_lists clearfix">
                                <a href="/home/product/detail?id=${product.id!""}"><img src="/photo/view?filename=${product.productPic!""}" width="207px" height="219px"/></a>
                                <a href="javascript:void(0);" onclick="addCollect(${product.id!""});" title="添加收藏" class="Collect"></a>
                                <p class="title_p_name">${product.productName!""}</p>
                                <p class="title_Profile">${product.info!""}</p>
                                <p class="price">￥${product.price!""} 元/箱</p>
                                <p class="btn_style"><a href="javascript:void(0);" onclick="addCart(${product.id!""});" class="Join_btn"></a></p>
                            </div>
                        </li>
                    </#list>
                </#if>

            </ul>
        </div>
    </div>
    <!--分页-->
    <div class="pages_Collect clearfix">
        <#if PageInfo.pageNum == 1>
            <a href="javascript:void(0);">&lt;&lt;</a>
        <#else>
            <a href="/home/product/vegetable?pageNum=${PageInfo.prePage!""}&content=${content!""}">&lt;&lt;</a>
        </#if>
        <#list PageInfo.pageNum-2..PageInfo.pageNum-1 as i>
            <#if i gte 1>
                <a href="/home/product/vegetable?pageNum=${i!""}&content=${content!""}">${i!""}</a>
            </#if>
        </#list>
        <a href="javascript:void(0);" class="on">${PageInfo.pageNum!""}</a>
        <#list PageInfo.pageNum+1..PageInfo.pageNum+2 as i>
            <#if i lte PageInfo.pages>
                <a href="/home/product/vegetable?pageNum=${i!""}&content=${content!""}">${i!""}</a>
            </#if>
        </#list>
        <#if PageInfo.pageNum == PageInfo.pages || PageInfo.pages == 0>
            <a href="javascript:void(0);">&gt;&gt;</a>
        <#else>
            <a href="/home/product/vegetable?pageNum=${PageInfo.nextPage!""}&content=${content!""}">&gt;&gt;</a>
        </#if>
    </div>
</div>
<!--底部样式-->
<#include "../common/footer.ftl"/>
<script type="text/javascript">

    //查询商品信息
    document.getElementById("search-button").onclick = function () {
        var content = $("#search-content").val();
        window.location.href = "/home/product/vegetable?content="+content;
    }

    //添加购物车
    function addCart(id){
        $.ajax({
            url:'/home/cart/add',
            data:{productId:id},
            type:'post',
            dataType:'json',
            success:function(data){
                if(data.code == 0){
                    layer.alert(data.msg, {icon: 6}, function () {
                        window.location.reload();
                    });
                }else{
                    layer.alert(data.msg, {icon: 5});
                }
            },
            error:function(){
                layer.alert("网络错误，添加购物车失败！", {icon: 5});
            }
        });
    }

    //添加收藏
    function addCollect(id){
        $.ajax({
            url:'/home/collect/add',
            data:{id:id},
            type:'post',
            dataType:'json',
            success:function(data){
                if(data.code == 0){
                    layer.alert(data.msg, {icon: 6});
                }else{
                    layer.alert(data.msg, {icon: 5});
                }
            },
            error:function(){
                layer.alert("网络错误，添加收藏失败！", {icon: 5});
            }
        });
    }
</script>
</body>
</html>
