<!--前台系统用户登录页面-->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <#include "../common/header.ftl"/>
    <script src="http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion=402727"></script>
    <title>用户登陆</title>
</head>

<body>
<!--顶部样式-->
<#include "../common/top_menu.ftl"/>
<!---->
<div><img src="/home/images/AD_page_img_02.png" width="100%"/></div>
<div class="Inside_pages clearfix">
    <!--登录样式-->
    <div class="login">
        <div class="style_login clearfix">
            <form id="login_form">
                <div class="layout">
                    <div class="login_title">登录</div>
                    <div class="item item-fore1"><label for="loginname" class="login-label name-label"></label><input name="username" id="username" type="text" class="text required" placeholder="请输入用户名称" tips="用户名称不能为空！"></div>
                    <div class="item item-fore2"><label for="nloginpwd" class="login-label pwd-label"></label><input name="password" id="password" type="password" class="text required" placeholder="请输入密码" tips="密码不能为空！"> </div>
                    <div class="login-btn"><a href="javascript:void(0);" id="login_button" class="btn_login">登&nbsp;&nbsp;&nbsp;&nbsp;录</a></div>
                </div>
            </form>
        </div>
        <div class="login_img"><img src="/home/images/login_img_03.png" /></div>
    </div>
</div>
<!--底部样式-->
<#include "../common/footer.ftl"/>
<script type="text/javascript">
    document.getElementById("login_button").onclick = function () {
        //进行统一表单非空验证
        if(!checkForm('login_form')){
            return;
        }
        //表单数据序列化
        var data = $('#login_form').serialize();
        $.ajax({
            url:'/home/user/login',
            data:data,
            type:'post',
            dataType:'json',
            success:function(data){
                if(data.code == 0){
                    setTokenToCookie(data.msg, 7);
                    window.location.href = "/home/system/index";
                }else{
                    layer.alert(data.msg, {icon: 5});
                }
            },
            error:function(){
                layer.alert("网络错误，登录失败！", {icon: 5});
            }
        });
    };
</script>
</body>
</html>
