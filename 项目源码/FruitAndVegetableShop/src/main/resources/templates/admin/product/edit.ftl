<!--商品编辑页面ftl-->
<!DOCTYPE html>
<html class="x-admin-sm">

<head>
    <meta charset="UTF-8">
    <title>欢迎页面-X-admin2.2</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <link rel="stylesheet" href="/admin/X-admin-2.2/css/font.css">
    <link rel="stylesheet" href="/admin/X-admin-2.2/css/xadmin.css">
    <script type="text/javascript" src="/admin/X-admin-2.2/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="/admin/X-admin-2.2/js/xadmin.js"></script>
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]--></head>

<body>
<div class="layui-fluid">
    <div class="layui-row">
        <form class="layui-form">
            <input type="hidden" name="id" value="${Product.id!""}" />
            <div class="layui-form-item">
                <label for="name" class="layui-form-label">
                    <span class="x-red">*</span>商品名称：</label>
                <div class="layui-input-inline">
                    <input type="text" id="productName" value="${Product.productName!""}" name="productName" required="" placeholder="请输入商品名称" lay-verify="required" autocomplete="off" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label for="headPic" class="layui-form-label">
                    商品图片：</label>
                <img src="/photo/view?filename=${Product.productPic!""}" id="preview-productPic" style="width:90px;height:90px;padding-right:10px;">
                <input type="hidden" id="productPic" name="productPic" value="${Product.productPic!""}" >
                <a class="layui-btn" onclick="uploadPhoto();">
                    <i class="icon iconfont">&#xe6a8;</i>&nbsp;上传图片
                </a>
            </div>

            <div class="layui-form-item layui-form-text">
                <label for="description" class="layui-form-label">
                    <span class="x-red">*</span>商品详情：</label>
                <div class="layui-input-block">
                    <textarea placeholder="请输入商品详情" id="info" name="info" class="layui-textarea">${Product.info!""}</textarea>
                </div>
            </div>

            <div class="layui-form-item">
                <label for="parentId" class="layui-form-label">
                    <span class="x-red">*</span>商品种类：</label>
                <div class="layui-input-inline">
                    <select id="categoryId" name="categoryId" class="valid">
                        <#if productCategoryList?size gt 0>
                            <#list productCategoryList as productCategory>
                                <#if Product.categoryId == productCategory.id>
                                    <option selected="selected" value="${productCategory.id!""}">${productCategory.categoryName!""}</option>
                                <#else>
                                    <option value="${productCategory.id!""}">${productCategory.categoryName!""}</option>
                                </#if>
                            </#list>
                        </#if>
                    </select>
                </div>
            </div>

            <div class="layui-form-item">
                <label for="name" class="layui-form-label" style="width:90px;padding-left: 5px;">
                    <span class="x-red">*</span>价格(元/箱)：</label>
                <div class="layui-input-inline">
                    <input type="text" id="price" name="price" onkeyup="num(this)" value="${Product.price!""}" required="" placeholder="请输入商品价格" lay-verify="required" autocomplete="off" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label for="name" class="layui-form-label" style="width:90px;padding-left: 5px;">
                    <span class="x-red">*</span>商品库存(箱)：</label>
                <div class="layui-input-inline">
                    <input type="text" id="stock" name="stock" onkeyup="value=value.replace(/^0(0+)|[^\d]+/g,'')" value="${Product.stock!""}" required="" placeholder="请输入商品库存" lay-verify="required" autocomplete="off" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label for="L_repass" class="layui-form-label"></label>
                <button class="layui-btn" lay-filter="edit" lay-submit="">
                    <i class="icon iconfont">&#xe747;</i>&nbsp;保存
                </button>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript">

    //限制只能输入数字或者两位小数
    function num(obj){
        obj.value = obj.value.replace(/[^\d.]/g,""); //清除"数字"和"."以外的字符
        obj.value = obj.value.replace(/^\./g,""); //验证第一个字符是数字
        obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个, 清除多余的
        obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
        obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3'); //只能输入两个小数
    }



    layui.use(['form', 'layer'],
        function() {
            $ = layui.jquery;
            var form = layui.form,
                layer = layui.layer;
            //监听提交
            form.on('submit(edit)',
                function(data) {
                    var editForm = data.field;
                    console.log(editForm);
                    //ajax异步提交数据
                    $.ajax({
                        url:'/admin/product/edit',
                        type:'post',
                        dataType:'json',
                        data:editForm,
                        success:function(data){
                            if(data.code == 0){
                                layer.alert(data.msg, {
                                        icon: 6
                                    },
                                    function() {
                                        // 获得frame索引
                                        var index = parent.layer.getFrameIndex(window.name);
                                        //关闭当前frame
                                        parent.layer.close(index);
                                        parent.parent.window.location.reload();
                                    });
                            }else{
                                layer.alert(data.msg, {icon: 5});
                            }
                        },
                        error:function(){
                            layer.alert('网络错误！商品编辑失败！', {icon: 5});
                        }
                    });//end ajax

                    return false;

                });//end function(data)

        });//end function()





</script>
<script>var _hmt = _hmt || []; (function() {
        var hm = document.createElement("script");
        hm.src = "https://hm.baidu.com/hm.js?b393d153aeb26b46e9431fabaf0f6190";
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(hm, s);
    })();
</script>
</body>

</html>