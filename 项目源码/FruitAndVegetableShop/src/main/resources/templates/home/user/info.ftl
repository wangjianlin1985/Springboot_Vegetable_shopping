<!--前台系统用户个人信息页面-->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <#include "../common/header.ftl"/>
    <title>用户中心——个人信息</title>
</head>

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
            <div class="user_Borders">
                <div class="title_name">
                    <span class="name">个人信息设置</span>
                </div>
                <div class="about_user_info">
                    <form id="update_info_form">
                        <input type="hidden" id="head_pic" name="headPic" value="${headPic!""}"/>
                        <input type="hidden" name="id" value="${id!""}"/>
                        <div class="user_layout">
                            <ul >
                                <li><label class="user_title_name">用户头像：</label><img id="preview-photo" src="/photo/view?filename=${headPic!"common/user_img.jpg"}" style="float:left;" width="80px" height="80px" />
                                    <a style="float:left;margin-top:35px;margin-left:35px;" href="javascript:void(0);" onclick="uploadPhoto();" ><input type="button" value="上传头像" /></a>
                                </li>
                                <li style="margin-top:50px;"><label class="user_title_name">用户名称：</label><input name="username" type="text"  class="add_text required" value="${username!""}" placeholder="请输入用户名称" tips="用户名称不能为空！"/><em>*</em></li>
                                <li><label class="user_title_name">手机号码：</label><input name="phone" type="text"  class="add_text required" value="${phone!""}" placeholder="请输入手机号码" tips="手机号码不能为空！"/><em>*</em></li>
                                <li><label class="user_title_name">电子邮箱：</label><input name="email" id="email" type="text"  class="add_text required" value="${email!""}" placeholder="请输入电子邮箱" tips="电子邮箱不能为空！"/><em>*</em></li>
                            </ul>
                            <div class="operating_btn"><input type="button" id="update_info_button" value="提交" class="submit—btn"/></div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<input type="file" id="photo-file" style="display:none;" onchange="upload();">
<!--底部样式-->
<#include "../common/footer.ftl"/>
<script type="text/javascript">

    function uploadPhoto(){
        $("#photo-file").click();
    }

    //上传图片
    function upload(){
        if($("#photo-file").val() == '')return;
        var formData = new FormData();
        formData.append('photo',document.getElementById('photo-file').files[0]);
        $.ajax({
            url:'/upload/upload_photo',
            type:'post',
            data:formData,
            contentType:false,
            processData:false,
            success:function(data){
                if(data.code == 0){
                    $("#user-photo").attr('src','/photo/view?filename=' + data.data);
                    $("#preview-photo").attr('src','/photo/view?filename=' + data.data);
                    $("#head_pic").val(data.data);
                    layer.alert("上传头像成功！", {icon: 6});
                }else{
                    layer.alert(data.msg, {icon: 5});
                }
            },
            error:function(){
                layer.alert("网络错误，上传头像失败！", {icon: 5});
            }
        });
    }

    //修改个人信息
    document.getElementById("update_info_button").onclick = function () {
        //进行统一表单非空验证
        if(!checkForm('update_info_form')){
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
        var data = $('#update_info_form').serialize();
        $.ajax({
            url:'/home/user/update_info',
            data:data,
            type:'post',
            dataType:'json',
            success:function(data){
                if(data.code == 0){
                    layer.alert(data.msg, {icon: 6},function () {
                        setTokenToCookie(data.data,7);
                        window.location.reload();
                    });
                }else{
                    layer.alert(data.msg, {icon: 5});
                }
            },
            error:function(){
                layer.alert("网络错误，修改个人信息失败！", {icon: 5});
            }
        });
    }

</script>
</body>
</html>
