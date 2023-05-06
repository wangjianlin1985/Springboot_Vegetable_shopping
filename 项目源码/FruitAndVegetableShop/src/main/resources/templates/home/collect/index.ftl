<!--前台系统收藏管理页面-->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <#include "../common/header.ftl"/>
    <title>用户中心——我的收藏</title>
</head>

<body>
<!--顶部样式-->
<#include "../common/top_menu.ftl"/>
<!---->
<div><img src="/home/images/AD_page_img_02.png" width="100%"/></div>
<!--用户中心(收藏)-->
<div class="Inside_pages clearfix">
    <div class="clearfix user" >
        <!--左侧菜单栏样式-->
        <#include "../common/user_left.ftl"/>
        <!--右侧内容样式-->
        <div class="user_right">
            <div class="user_Borders clearfix">
                <div class="title_name">
                    <span class="name">用户收藏</span>
                </div>
                <!--收藏样式-->
                <div class="Collect">
                    <ul class="Collect_list">
                        <#if PageInfo.list?size gt 0>
                            <#list PageInfo.list as collect>
                                <li>
                                    <div class="Collect_pro_name">
                                        <a href="javascript:void(0);" onclick="deleteCollect(${collect.id!""});" class="delete_Collect"></a>
                                        <p class="img center"><a href="/home/product/detail?id=${collect.product.id!""}"><img src="/photo/view?filename=${collect.product.productPic!""}" /></a></p>
                                        <p><a href="/home/product/detail?id=${collect.product.id!""}">${collect.product.productName!""}</a></p>
                                        <p class="Collect_price">￥${collect.product.price!""} 元/箱</p>
                                    </div>
                                </li>
                            </#list>
                        </#if>
                    </ul>
                    <!--分页-->
                    <div class="pages_Collect clearfix">
                        <#if PageInfo.pageNum == 1>
                            <a href="javascript:void(0);">&lt;&lt;</a>
                        <#else>
                            <a href="/home/collect/index?pageNum=${PageInfo.prePage!""}">&lt;&lt;</a>
                        </#if>
                        <#list PageInfo.pageNum-2..PageInfo.pageNum-1 as i>
                            <#if i gte 1>
                                <a href="/home/collect/index?pageNum=${i!""}">${i!""}</a>
                            </#if>
                        </#list>
                        <a href="javascript:void(0);" class="on">${PageInfo.pageNum!""}</a>
                        <#list PageInfo.pageNum+1..PageInfo.pageNum+2 as i>
                            <#if i lte PageInfo.pages>
                                <a href="/home/collect/index?pageNum=${i!""}">${i!""}</a>
                            </#if>
                        </#list>
                        <#if PageInfo.pageNum == PageInfo.pages || PageInfo.pages == 0>
                            <a href="javascript:void(0);">&gt;&gt;</a>
                        <#else>
                            <a href="/home/collect/index?pageNum=${PageInfo.nextPage!""}">&gt;&gt;</a>
                        </#if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!--底部样式-->
<#include "../common/footer.ftl"/>
<script type="text/javascript">

    function deleteCollect(id){
        $.ajax({
            url:'/home/collect/delete',
            data:{id:id},
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
                layer.alert("网络错误，删除收藏失败！", {icon: 5});
            }
        });
    }

</script>
</body>
</html>
