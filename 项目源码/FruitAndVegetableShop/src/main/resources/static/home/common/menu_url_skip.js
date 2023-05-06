<!--菜单栏url页面跳转公共方法-->

//跳转首页
function index(){
    window.location.href = "/home/system/index";
}

//跳转用户中心
function center(){
    window.location.href = "/home/user/info";
}

//退出登录
function logout(){
    setTokenToCookie("", -1, );
    window.location.reload();
}

//跳转水果专区
function fruit(){
    window.location.href = "/home/product/fruit";
}

//跳转蔬菜专区
function vegetable(){
    window.location.href = "/home/product/vegetable";
}
