<!--前台系统用户我的评论页面-->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <#include "../common/header.ftl"/>
    <title>用户中心——我的评论</title>
</head>
<body>
<!--顶部样式-->
<#include "../common/top_menu.ftl"/>
<!---->
<div><img src="/home/images/AD_page_img_02.png" width="100%"/></div>
<!--用户中心(积分)-->
<div class="Inside_pages clearfix">
    <div class="clearfix user" >
        <!--左侧菜单栏样式-->
        <#include "../common/user_left.ftl"/>
        <!--右侧内容样式-->
        <div class="user_right">
            <div class="user_Borders">
                <div class="title_name">
                    <span class="name">用户评论</span>
                </div>
                <!--积分样式-->
                <div class="user_integral_style slideTxtBox">
                    <div class="bd">
                        <ul>
                            <table>
                                <thead>
                                <tr>
                                    <td>商品名称</td>
                                    <td>商品图片</td>
                                    <td>评论内容</td>
                                    <td>评论时间</td>
                                </tr>
                                </thead>
                                <tbody>
                                    <#if PageInfo.list?size gt 0>
                                        <#list PageInfo.list as comment>
                                            <tr>
                                                <td>${comment.product.productName!""}</td>
                                                <td><img src="/photo/view?filename=${comment.product.productPic!""}" style="width: 90px;height: 90px;padding: 10px 0px;" /></td>
                                                <td>${comment.content!""}</td>
                                                <td>${comment.createTime?string('yyyy-MM-dd HH:mm:ss')!""}</td>
                                            </tr>
                                        </#list>
                                    </#if>
                                </tbody>
                            </table>
                        </ul>
                    </div>
                </div>
            </div>
            <!--分页-->
            <div class="pages_Collect clearfix">
                <#if PageInfo.pageNum == 1>
                    <a href="javascript:void(0);" style="margin-left: -50px">&lt;&lt;</a>
                <#else>
                    <a href="/home/user/comment?pageNum=${PageInfo.prePage!""}">&lt;&lt;</a>
                </#if>
                <#list PageInfo.pageNum-2..PageInfo.pageNum-1 as i>
                    <#if i gte 1>
                        <a href="/home/user/comment?pageNum=${i!""}">${i!""}</a>
                    </#if>
                </#list>
                <a href="javascript:void(0);" class="on">${PageInfo.pageNum!""}</a>
                <#list PageInfo.pageNum+1..PageInfo.pageNum+2 as i>
                    <#if i lte PageInfo.pages>
                        <a href="/home/user/comment?pageNum=${i!""}">${i!""}</a>
                    </#if>
                </#list>
                <#if PageInfo.pageNum == PageInfo.pages || PageInfo.pages == 0>
                    <a href="javascript:void(0);">&gt;&gt;</a>
                <#else>
                    <a href="/home/user/comment?pageNum=${PageInfo.nextPage!""}">&gt;&gt;</a>
                </#if>
            </div>
        </div>
    </div>
</div>
<!--底部样式-->
<#include "../common/footer.ftl"/>
<script type="text/javascript">


</script>
</body>
</html>
