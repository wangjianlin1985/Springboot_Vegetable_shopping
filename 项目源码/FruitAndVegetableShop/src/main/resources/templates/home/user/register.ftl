<!--前台系统用户注册页面-->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <#include "../common/header.ftl"/>
        <title>用户注册</title>
    </head>

    <body>
    <!--顶部样式-->
    <#include "../common/top_menu.ftl"/>
    <!---->
    <div><img src="/home/images/AD_page_img_02.png" width="100%"/></div>
    <!--注册样式-->
    <div class="Inside_pages clearfix">
        <div class="register">
            <div class="register_style">
                <form id="register_form">
                    <div class="u_register">
                        <ul>
                            <li><label class="name">用户名称：</label><input name="username" id="username" type="text"  class="text_Add required" placeholder="请输入长度不大于8位的用户名称" tips="用户名称不能为空！"/></li>
                            <li><label class="name">设置密码：</label><input name="password" id="password" type="password"  class="text_Add required" placeholder="请输入长度为6-16位的密码" tips="密码不能为空！"/></li>
                            <li><label class="name">确认密码：</label><input name="repassword" id="repassword" type="password"  class="text_Add required" placeholder="请再次输入密码" tips="确认密码不能为空！"/></li>
                            <li><label class="name">电子邮箱：</label><input name="email" id="email" type="text"  class="text_Add required" placeholder="为了后续通知消息发送，请填写您的真实邮箱" tips="电子邮箱不能为空！"/></li>
                            <li><label class="name">手机号码：</label><input name="phone" id="phone" type="text"  class="text_Add required" placeholder="请输入长度11位的手机号码" tips="手机号码不能为空！"/></li>
                            <li><label class="name">验 证 码：</label><input name="cpacha" id="cpacha" type="text"  class="text_verification required" tips="验证码不能为空！"/>
                                <img id="cpacha-img" title="点击切换验证码" style="cursor:pointer;margin-left: 20px" src="/common/cpacha/generate_cpacha?vl=4&fs=21&w=98&h=33&method=user_register" width="110px" height="36px" onclick="changeCpacha()">
                            </li>
                        </ul>
                        <div class="register-btn"><a href="javascript:void(0);" id="register_button" class="btn_register">注&nbsp;&nbsp;&nbsp;&nbsp;册</a></div>
                    </div>
                </form>
            </div>
            <div class="register_img"><img src="/home/images/Register_img.png" /></div>
        </div>
    </div>
    <!--底部样式-->
    <#include "../common/footer.ftl"/>
        <script type="text/javascript">
            <!--更换验证码-->
            function changeCpacha(){
                $("#cpacha-img").attr("src",'/common/cpacha/generate_cpacha?vl=4&fs=21&w=98&h=33&method=user_register&t=' + new Date().getTime());
            }
            document.getElementById("register_button").onclick = function () {
                //进行统一表单非空验证
                if(!checkForm('register_form')){
                    return;
                }
                var email = document.getElementById("email");
                //对电子邮箱格式的验证
                var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
                if(!myreg.test(email.value))
                {
                    layer.alert("电子邮箱格式不正确!", {icon: 5});
                    return false;
                }
                //表单数据序列化
                var data = $('#register_form').serialize();
                $.ajax({
                    url:'/home/user/register',
                    data:data,
                    type:'post',
                    dataType:'json',
                    beforeSend: function () {
                        layer.msg('正在注册用户信息...请稍后....', {
                            shade: 0.4,
                            time:false //取消自动关闭
                        });
                    },
                    success:function(data){
                        if(data.code == 0){
                            layer.alert(data.msg, {icon: 6});
                        }else{
                            layer.alert(data.msg, {icon: 5});
                        }
                        changeCpacha();
                    },
                    error:function(){
                        layer.alert("网络错误，注册失败！", {icon: 5});
                        changeCpacha();
                    }
                });
            };



        </script>
    </body>
</html>
