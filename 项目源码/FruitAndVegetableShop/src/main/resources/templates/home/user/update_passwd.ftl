<!--前台系统用户修改密码页面-->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <#include "../common/header.ftl"/>
    <title>用户中心——修改密码</title>
</head>

<body>
<!--顶部样式-->
<#include "../common/top_menu.ftl"/>
<!---->
<div><img src="/home/images/AD_page_img_02.png" width="100%"/></div>
<!--修改密码样式-->
<div class="Inside_pages clearfix">
    <div class="clearfix user" >
        <!--左侧菜单栏样式-->
        <#include "../common/user_left.ftl"/>
        <!--右侧样式-->
        <div class="user_right">
            <div class="user_Borders">
                <div class="title_name">
                    <span class="name">修改密码</span>
                </div>
                <!--修改密码样式-->
                <div class="about_user_info">
                    <form id="update_passwd_form">
                        <div class="user_layout">
                            <ul >
                                <li><label class="user_title_name">原密码：</label><input name="prePassword" type="password"  class="add_text required" placeholder="请输入原密码" tips="原密码不能为空！"/><em>*</em></li>
                                <li><label class="user_title_name">新密码：</label><input name="newPassword" type="password"  class="add_text required" placeholder="请输入新密码" tips="新密码不能为空！"/><em>*</em></li>
                                <li><label class="user_title_name">确认新密码：</label><input name="reNewPassword" type="password"  class="add_text required" placeholder="请输入确认新密码" tips="确认新密码不能为空！"/><em>*</em></li>
                            </ul>
                            <div class="operating_btn"><input id="update_passwd_button" type="button" value="确认"  class="submit—btn"/></div>
                        </div>
                    </form>

                </div>
            </div>
        </div>
    </div>
</div>
<!--底部样式-->
<#include "../common/footer.ftl"/>
<script type="text/javascript">

    //修改密码
    document.getElementById("update_passwd_button").onclick = function () {
        //进行统一表单非空验证
        if(!checkForm('update_passwd_form')){
            return;
        }
        //表单数据序列化
        var data = $('#update_passwd_form').serialize();
        $.ajax({
            url:'/home/user/update_passwd',
            data:data,
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
                layer.alert("网络错误，修改密码失败！", {icon: 5});
            }
        });
    }

</script>
</body>
</html>
