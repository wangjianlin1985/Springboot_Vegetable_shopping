<!--cookie和token公共方法-->

//将token存入客户端浏览器的cookie中
function setTokenToCookie(value,day) {
    var Days = day; //此 cookie 将被保存 7 天
    var exp = new Date();
    exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
    document.cookie = "my_token =" + escape(value) +";path=/" + ";expires=" + exp.toGMTString();
}

//将token从客户端浏览器cookie中取出
function getCookie(name) {
    var cookieValue = "";
    if (document.cookie && document.cookie !== '') {
        var cookies = document.cookie.split(';');
        for (var i = 0; i < cookies.length; i++) {
            var cookie = $.trim(cookies[i]);
            if (cookie.substring(0, name.length + 1) === (name + '=')) {
                cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
                break;
            }
        }
    }
    return cookieValue;
}

