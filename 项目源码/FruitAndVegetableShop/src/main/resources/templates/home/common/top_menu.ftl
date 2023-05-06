<!--公共顶部菜单-->
<div class="top_header">
    <em class="left_img"></em>
    <div class="header clearfix" id="header">
        <a href="/home/system/index" class="logo_img"><img src="/home/images/logo.png" /></a>
        <div class="header_Section">
            <div class="shortcut">
                <ul>
                    <#if username??>
                        <li  class="hd_menu_tit" id="li_user">欢迎您，${username!""}！</li>
                    <#else>
                        <li  class="hd_menu_tit" id="li_login"><em class="login_img"></em><a href="/home/user/login">登录</a></li>
                    </#if>
                    <li  class="hd_menu_tit"><em  class="registered_img"></em><a href="/home/user/register">注册</a></li>
                    <li  class="hd_menu_tit" ><em class="Collect_img"></em><a href="javascript:void(0);" onclick="addFavorite();" title="收藏水果蔬菜商城网站">
                            收藏网站</a></li>
                    <li  class="hd_menu_tit"><em class="cart_img"></em><a href="/home/cart/index">购物车（${cartTotal!"0"}）</a></li>
                </ul>
            </div>
            <div class="nav" id="Navigation">
                <ul class="Navigation_name">
                    <li class=""><a herf="javascript:void(0);" onclick="index();">首页</a></li>
                    <li class=""><a herf="javascript:void(0);" onclick="fruit();">水果专区</a></li>
                    <li class=""><a herf="javascript:void(0);" onclick="vegetable();">蔬菜专区</a></li>
                    <li class=""><a herf="javascript:void(0);" onclick="center();">用户中心</a></li>
                    <#if username??>
                        <li class=""><a herf="javascript:void(0);" onclick="logout();">退出登录</a></li>
                    </#if>
                </ul>
            </div>
            <script>$("#Navigation").slide({titCell:".Navigation_name li"});</script>
        </div>
    </div>
    <em class="right_img"></em>
</div>