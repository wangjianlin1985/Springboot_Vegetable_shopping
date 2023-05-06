<!--前台系统商品详情页面-->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <#include "../common/header.ftl"/>
    <!--[if IE 7]>
    <link rel="stylesheet" href="assets/css/font-awesome-ie7.min.css">
    <![endif]-->
    <title>商品详细介绍</title>
</head>
<body>
<!--顶部样式-->
<#include "../common/top_menu.ftl"/>
<!---->
<div><img src="/home/images/AD_page_img_02.png" width="100%"/></div>
<!--商品详细介绍-->
<div class="Inside_pages clearfix">
    <div class="left_style">
        <div class="ranking">
            <div class="ranking_title"><span>销量</span>排行</div>
            <ul class="ranking_list">
                <#assign count = 1/>
                <#if SellNumList?size gt 0>
                    <#list SellNumList as product>
                        <li class="">
                            <em class="ranking_label">${count!""}</em>
                            <a href="/home/product/detail?id=${product.id!""}" class="img"> <img src="/photo/view?filename=${product.productPic!""}" width="100px" height="100px" /></a>
                            <p class="ranking_name">${product.productName!""}</p>
                            <p class="price"><b>￥</b>${product.price!""}</p>
                            <p><a href="/home/product/detail?id=${product.id!""}">立即查看< </a></p>
                        </li>
                        <#assign count = count + 1/>
                    </#list>
                </#if>
            </ul>
        </div>
    </div>
    <!--详细介绍样式-->
    <div class="right_style">
        <div class="pro_detailed">
            <div class="Details_style clearfix" id="goodsInfo">
                <div class="mod_picfold clearfix">
                    <div class="clearfix" id="detail_main_img">
                        <div class="layout_wrap">
                            <!--相册样式 420x500-->
                            <img src="/photo/view?filename=${Product.productPic!""}" width="350px" height="350px" />
                        </div>
                    </div>
                    <div class="Sharing">
                        <div class="coding">商品编码：${Product.id!""}</div>
                    </div>
                </div>
                <!--购买信息-->
                <div class="Buying_info">
                    <div class="product_name"><h2>${Product.productName!""}</h2><span>${Product.info!""}</span></div>
                    <div class="product_price">
                        <div class="price"><label>商城价：</label>￥${Product.price!""} <b>元/箱</b></div>
                        <div class="jyScore-fra">共有${Product.commentNum!""}条评论</div>
                    </div>
                    <div class="productDL">
                        <dl>
                            <dt>数量：</dt>
                            <dd class="left">
                                <div class="Numbers">
                                    <a href="javascript:void(0);" onclick="reduce()" class="jian  ">-</a>
                                    <input id="number" name="number" type="text" value="1" onkeyup="value=value.replace(/^0(0+)|[^\d]+/g,'')" class="number_text">
                                    <a href="javascript:void(0);" onclick="add();" class="jia  ">+</a>
                                </div>
                            </dd>
                            <dd class="left Quantity">(库存：${Product.stock!""})</dd>
                        </dl>
                    </div>
                    <div class="product_Quantity">销量：${Product.sellNum!""} 箱</div>
                    <div class="operating">
                        <a href="javascript:void(0);" onclick="addCart(${Product.id!""});" class="Join_btn"></a>
                        <a href="javascript:void(0);" onclick="addCollect(${Product.id!""});" class="Collect_btn"></a>
                    </div>
                </div>
            </div>
            <!--信息展示-->
            <div class="mainListRight" id="status1">
                <ul class="fixed_bar" style="">
                    <li class="status_on active"><a href="javascript:void(0);status2">商品评价<span>(${TotalComment!""})</span></a></li>
                </ul>
            </div>
            <!--发表评论-->
            <div class="commentBox" style="display: block;">
                <div class="bd">
                    <textarea name="content" id="content" style="width:943px;height: 70px; border:solid 0.5px" placeholder="请输入评论内容（不得超过100字！）" ></textarea>
                    <p class="g">
                        <a href="javascript:void(0)" onclick="submitComment(${Product.id!""});"><input style="position:absolute;right:100px;margin-top: 5px;" type="button" value="发表" class="btn_common"></a>
                </div>
            </div>
            <!--评论列表-->
            <div  class="CommentText" id="goodsdetail_comments_list" style="display:block;margin-top: 50px;margin-bottom: 50px;">
                <#assign x = 1+(PageInfo.pageNum-1)*PageInfo.pageSize/>
                <#if PageInfo.list?size gt 0>
                    <#list PageInfo.list as comment>
                        <p style="margin-top: 5px;"><span style="line-height:22px;font-size:14px;color:#347BC4;">${comment.user.username!""}</span><span style="color:#888;line-height:22px;font-size:14px;">(${comment.createTime?string('yyyy-MM-dd HH:mm:ss')!""})</span>
                            <span style="position:absolute;right:100px;font-size:16px;color:#ccc;">#${x!""}</span><br><span style="color:#888;line-height:22px;font-size:14px;">${comment.content!""}</span>
                        </p>
                        <#assign x = x + 1 />
                    </#list>
                </#if>
            </div>
            <!--分页-->
            <div class="pages_Collect clearfix"  >
                <#if PageInfo.pageNum == 1>
                    <a href="javascript:void(0);" style="margin-left: -50px">&lt;&lt;</a>
                <#else>
                    <a href="/home/product/detail?id=${Product.id!""}&pageNum=${PageInfo.prePage!""}">&lt;&lt;</a>
                </#if>
                <#list PageInfo.pageNum-2..PageInfo.pageNum-1 as i>
                    <#if i gte 1>
                        <a href="/home/product/detail?id=${Product.id!""}&pageNum=${i!""}">${i!""}</a>
                    </#if>
                </#list>
                <a href="javascript:void(0);" class="on">${PageInfo.pageNum!""}</a>
                <#list PageInfo.pageNum+1..PageInfo.pageNum+2 as i>
                    <#if i lte PageInfo.pages>
                        <a href="/home/product/detail?id=${Product.id!""}&pageNum=${i!""}">${i!""}</a>
                    </#if>
                </#list>
                <#if PageInfo.pageNum == PageInfo.pages || PageInfo.pages == 0>
                    <a href="javascript:void(0);">&gt;&gt;</a>
                <#else>
                    <a href="/home/product/detail?id=${Product.id!""}&pageNum=${PageInfo.nextPage!""}">&gt;&gt;</a>
                </#if>
            </div>
        </div>
    </div>
</div>
<!--底部样式-->
<#include "../common/footer.ftl"/>
<script type="text/javascript">

    //商品数量加一
    function add(){
        var now_num = parseInt($("#number").val());
        now_num = now_num + 1;
        $("#number").val(now_num);
    }

    //商品数量减一
    function reduce(){
        var now_num = parseInt($("#number").val());
        if(now_num > 1){
            now_num = now_num - 1;
        }
        $("#number").val(now_num);
    }

    //添加购物车
    function addCart(id){
        var number = $("#number").val();
        $.ajax({
            url:'/home/cart/add',
            data:{productId:id,quantity:number},
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

    //发表评论
    function submitComment(id){
        var content = $("#content").val();
        $.ajax({
            url:'/home/comment/submit_comment',
            data:{productId:id,content:content},
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
                layer.alert("网络错误，发布评论失败！", {icon: 5});
            }
        });
    }
</script>
</body>
</html>
