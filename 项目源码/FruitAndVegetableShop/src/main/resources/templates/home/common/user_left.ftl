<!--用户中心左侧公共菜单栏部分-->
<div class="user_left">
    <div class="user_info">
        <div class="Head_portrait"><img src="/photo/view?filename=${headPic!"common/user_img.jpg"}" id="user-photo" width="80px" height="80px"/><!--头像区域--></div>
        <div class="user_name">${username!""}<a href="/home/user/info">[个人资料]</a></div>
    </div>
    <ul class="Section">
        <li><a href="/home/user/info"><em></em><span>个人信息</span></a></li>
        <li><a href="/home/user/update_passwd"><em></em><span>修改密码</span></a></li>
        <li><a href="/home/user/order"><em></em><span>我的订单</span></a></li>
        <li><a href="/home/user/comment"><em></em><span>我的评论</span></a></li>
        <li><a href="/home/collect/index"><em></em><span>我的收藏</span></a></li>
        <li><a href="/home/address/index"><em></em><span>收货地址管理</span></a></li>
    </ul>
</div>