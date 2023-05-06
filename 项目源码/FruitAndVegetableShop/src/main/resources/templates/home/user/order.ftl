<!--前台系统用户我的订单页面-->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <#include "../common/header.ftl"/>
    <title>用户中心——我的订单</title>
</head>
<script type="text/javascript">
    $(document).ready(function(){

        setInterval(showTime, 1000);
        function timer(obj,txt){
            obj.text(txt);
        }
        function showTime(){
            var today = new Date();
            var weekday=new Array(7)
            weekday[0]="星期日"
            weekday[1]="星期一"
            weekday[2]="星期二"
            weekday[3]="星期三"
            weekday[4]="星期四"
            weekday[5]="星期五"
            weekday[6]="星期六"
            var y=today.getFullYear()+"年";
            var month=today.getMonth()+1+"月";
            var td=today.getDate();
            var d=weekday[today.getDay()];
            var h=today.getHours();
            var m=today.getMinutes();
            var s=today.getSeconds();
            timer($("#y"),y+month);
            //timer($("#MH"),month);
            timer($("h1"),td);
            timer($("#D"),d);
            timer($("#H"),h);
            timer($("#M"),m);
            timer($("#S"),s);
        }
    })
</script>
<body>
<!--顶部样式-->
<#include "../common/top_menu.ftl"/>
<!---->
<div><img src="/home/images/AD_page_img_02.png" width="100%"/></div>
<!--用户中心-->
<div class="Inside_pages clearfix">
    <div class="clearfix user" >
        <!--左侧菜单栏样式-->
        <#include "../common/user_left.ftl"/>
        <!--右侧样式-->
        <div class="user_right">
            <div class="user_center_style">
                <div class="user_time">
                    <h1></h1>
                    <h4 id="D"></h4>
                    <h4 id="y"></h4>
                </div>
                <ul class="user_center_info">
                    <li><img src="/home/images/user_img_04.png" />
                        <a href="javascript:void(0);">已发货（${Send!"0"}）</a>
                    </li>
                    <li><img src="/home/images/user_img_03.png" />
                        <a href="javascript:void(0);">已签收（${Sign!"0"}）</a>
                    </li>
                </ul>
            </div>
            <div class="Order_form">
                <div class="user_Borders">
                    <div class="title_name">
                        <span class="name">我的订单</span>
                    </div>
                    <div class="Order_form_list">
                        <table>
                            <thead>
                            <td class="list_name_title0" style="width: 240px">商品</td>
                            <td class="list_name_title1">单价(元)</td>
                            <td class="list_name_title2">数量</td>
                            <td class="list_name_title4">实付款(元)</td>
                            <td class="list_name_title5">订单状态</td>
                            <td class="list_name_title6">操作</td>
                            </thead>
                            <#if PageInfo.list?size gt 0>
                                <#list PageInfo.list as order>
                                    <tbody>
                                    <tr><td colspan="6" class="Order_form_time">${order.createTime?string('yyyy-MM-dd HH:mm:ss')!""}&nbsp;&nbsp;订单号：${order.orderNo!""}
                                            <br>订单留言：${order.remark!""}
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="3">
                                            <table class="Order_product_style">
                                                <#if order.orderItemList?size gt 0>
                                                    <#list order.orderItemList as orderItem>
                                                        <tr>
                                                            <td>
                                                                <div class="product_name clearfix">
                                                                    <a href="/home/product/detail?id=${orderItem.productId!""}"><img src="/photo/view?filename=${orderItem.productPic!""}"  width="80px" height="80px"/></a>
                                                                    <a href="/home/product/detail?id=${orderItem.productId!""}">${orderItem.productName}</a>
                                                                </div>
                                                            </td>
                                                            <td>${orderItem.productPrice!""}</td>
                                                            <td>${orderItem.quantity!""}</td>
                                                        </tr>
                                                    </#list>
                                                </#if>
                                            </table>
                                        </td>
                                        <td class="split_line">${order.totalPrice!""}</td>
                                        <#if order.state == 0>
                                            <td class="split_line">未支付</td>
                                            <td>
                                                <a href="/home/order/index?orderId=${order.id!""}">支付</a>
                                                <a href="javascript:void(0);" onclick="deleteOrder(${order.id!""});">删除</a>
                                                <a href="javascript:void(0);" onclick="cancelOrder(${order.id!""});">取消</a>
                                            </td>
                                        <#elseif order.state == 1>
                                            <td class="split_line">已支付，待发货</td>
                                            <td>
                                                <a href="javascript:void(0);" onclick="deleteOrder(${order.id!""});">删除</a>
                                                <a href="javascript:void(0);" onclick="cancelOrder(${order.id!""});">取消</a>
                                            </td>
                                        <#elseif order.state == 2>
                                            <td class="split_line">已取消</td>
                                            <td>
                                                <a href="javascript:void(0);" onclick="deleteOrder(${order.id!""});">删除</a>
                                            </td>
                                        <#elseif order.state == 3>
                                            <td class="split_line">已送达，待签收</td>
                                            <td>
                                                <a href="javascript:void(0);" onclick="deleteOrder(${order.id!""});">删除</a>
                                                <a href="javascript:void(0);" onclick="cancelOrder(${order.id!""});">取消</a>
                                            </td>
                                        <#elseif order.state == 4>
                                            <td class="split_line">已签收</td>
                                            <td>
                                                <a href="javascript:void(0);" onclick="deleteOrder(${order.id!""});">删除</a>
                                            </td>
                                        <#elseif order.state == 5>
                                            <td class="split_line">已发货</td>
                                            <td>
                                                <a href="javascript:void(0);" onclick="deleteOrder(${order.id!""});">删除</a>
                                                <a href="javascript:void(0);" onclick="cancelOrder(${order.id!""});">取消</a>
                                            </td>
                                        </#if>
                                    </tr>
                                    </tbody>
                                </#list>
                            </#if>
                        </table>
                    </div>
                </div>
                <!--分页-->
                <div class="pages_Collect clearfix">
                    <#if PageInfo.pageNum == 1>
                        <a href="javascript:void(0);" style="margin-left: -50px">&lt;&lt;</a>
                    <#else>
                        <a href="/home/user/order?pageNum=${PageInfo.prePage!""}">&lt;&lt;</a>
                    </#if>
                    <#list PageInfo.pageNum-2..PageInfo.pageNum-1 as i>
                        <#if i gte 1>
                            <a href="/home/user/order?pageNum=${i!""}">${i!""}</a>
                        </#if>
                    </#list>
                    <a href="javascript:void(0);" class="on">${PageInfo.pageNum!""}</a>
                    <#list PageInfo.pageNum+1..PageInfo.pageNum+2 as i>
                        <#if i lte PageInfo.pages>
                            <a href="/home/user/order?pageNum=${i!""}">${i!""}</a>
                        </#if>
                    </#list>
                    <#if PageInfo.pageNum == PageInfo.pages || PageInfo.pages == 0>
                        <a href="javascript:void(0);">&gt;&gt;</a>
                    <#else>
                        <a href="/home/user/order?pageNum=${PageInfo.nextPage!""}">&gt;&gt;</a>
                    </#if>
                </div>
            </div>
        </div>
    </div>
</div>
<!--底部样式-->
<#include "../common/footer.ftl"/>
<script type="text/javascript">

    //用户删除订单
    function deleteOrder(id){
        $.ajax({
            url:'/home/order/user_delete',
            data:{orderId:id,isDeleted:1},
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
                layer.alert("网络错误，删除订单失败！", {icon: 5});
            }
        });
    }

    //用户取消订单
    function cancelOrder(id){
        $.ajax({
            url:'/home/order/update_order_state',
            data:{orderId:id,state:2},
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
                layer.alert("网络错误，取消订单失败！", {icon: 5});
            }
        });
    }
</script>
</body>
</html>
