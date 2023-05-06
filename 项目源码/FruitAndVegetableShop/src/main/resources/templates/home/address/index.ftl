<!--前台系统地址管理页面-->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <#include "../common/header.ftl"/>
    <title>用户中心——地址管理</title>
</head>

<body>
<!--顶部样式-->
<#include "../common/top_menu.ftl"/>
<!---->
<div><img src="/static/home/images/AD_page_img_02.png" width="100%"/></div>
<!--用户中心(地址管理界面)-->
<div class="Inside_pages clearfix">
    <div class="clearfix user" >
        <!--左侧菜单栏样式-->
        <#include "../common/user_left.ftl"/>
        <!--右侧内容样式-->
        <div class="user_right">
            <div class="user_Borders">
                <div class="title_name">
                    <span class="name">地址管理</span>
                </div>
                <div class="about_user_info">
                    <form id="add_address_form">
                        <div class="user_layout">
                            <ul>
                                <li><label class="user_title_name">收件人姓名：</label><input name="receiverName" type="text" class="add_text required" placeholder="请输入收货人姓名" tips="收货人姓名不能为空！"><em>*</em></li>
                                <li><label class="user_title_name">收货人地址：</label><input name="receiverAddress" type="text" class="add_text required" placeholder="请输入收货人地址" tips="收货人地址不能为空！"><em>*</em></li>
                                <li><label class="user_title_name">收货人手机：</label><input name="receiverPhone" type="text" class="add_text required" placeholder="请输入收货人电话" tips="收货人电话不能为空！"><em>*</em></li>
                            </ul>
                            <div class="operating_btn"><input id="add_address_button" type="button" value="添加" class="submit—btn"></div>
                        </div>
                    </form>
                </div>
                <!--地址列表-->
                <div class="Address_List">
                    <div class="title_name"><span class="name">用户地址列表</span></div>
                    <div class="list">
                        <table>
                            <thead>
                            <td class="list_name_title0">收件人姓名</td>
                            <td class="list_name_title3">电话</td>
                            <td class="list_name_title4">收货地址</td>
                            <td class="list_name_title5">操作</td>
                            </thead>
                            <#list addressList as address>
                                <tr>
                                    <td>${address.receiverName!""}</td>
                                    <td>${address.receiverPhone!""}</td>
                                    <td>${address.receiverAddress!""}</td>
                                    <td>
                                        <#if address.firstSelected == 0>
                                            <a href="javascript:void(0);" onclick="setFirstSelected(${address.id!""});">设为订单首选</a>
                                            <a href="javascript:void(0);" onclick="deleteAddress(${address.id!""});">删除</a>
                                        <#else>
                                            <font color="red">订单首选地址</font>
                                            <a href="javascript:void(0);" onclick="deleteAddress(${address.id!""});">删除</a>
                                        </#if>
                                    </td>
                                </tr>
                            </#list>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!--底部样式-->
<#include "../common/footer.ftl"/>
<script type="text/javascript">

    //添加地址
    document.getElementById("add_address_button").onclick = function () {
        //进行统一表单非空验证
        if(!checkForm('add_address_form')){
            return;
        }
        //表单数据序列化
        var data = $('#add_address_form').serialize();
        $.ajax({
            url:'/home/address/add',
            data:data,
            type:'post',
            dataType:'json',
            success:function(data){
                if(data.code == 0){
                    layer.alert(data.msg, {icon: 6},function () {
                        window.location.reload();
                    });
                }else{
                    layer.alert(data.msg, {icon: 5});
                }
            },
            error:function(){
                layer.alert("网络错误，添加地址失败！", {icon: 5});
            }
        });
    }

    //把地址设为订单首选
    function setFirstSelected(id){
        $.ajax({
            url:'/home/address/set_first_selected',
            data:{id:id},
            type:'post',
            dataType:'json',
            success:function(data){
                if(data.code == 0){
                    layer.alert(data.msg, {icon: 6},function () {
                        window.location.reload();
                    });
                }else{
                    layer.alert(data.msg, {icon: 5});
                }
            },
            error:function(){
                layer.alert("网络错误，设置订单首选地址失败！", {icon: 5});
            }
        });
    }

    //删除地址
    function deleteAddress(id){
        $.ajax({
            url:'/home/address/delete',
            data:{id:id},
            type:'post',
            dataType:'json',
            success:function(data){
                if(data.code == 0){
                    layer.alert(data.msg, {icon: 6},function () {
                        window.location.reload();
                    });
                }else{
                    layer.alert(data.msg, {icon: 5});
                }
            },
            error:function(){
                layer.alert("网络错误，删除地址失败！", {icon: 5});
            }
        });
    }
</script>
</body>
</html>
