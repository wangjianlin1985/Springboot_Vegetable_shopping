<!--系统欢迎页面ftl-->
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
        <script src="/admin/X-admin-2.2/lib/layui/layui.js" charset="utf-8"></script>
        <script type="text/javascript" src="/admin/X-admin-2.2/js/xadmin.js"></script>
        <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
        <!--[if lt IE 9]>
          <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
          <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>
    <body>
        <div class="layui-fluid">
            <div class="layui-row layui-col-space15">
                <div class="layui-col-md12">
                    <div class="layui-card">
                        <div class="layui-card-body ">
                            <blockquote class="layui-elem-quote">欢迎管理员：
                                <span class="x-red">${loginedAdmin.name!""}</span>！
                            </blockquote>
                        </div>
                    </div>
                </div>
                <div class="layui-col-md12">
                    <div class="layui-card">
                        <div class="layui-card-header">数据统计</div>
                        <div class="layui-card-body ">
                            <ul class="layui-row layui-col-space10 layui-this x-admin-carousel x-admin-backlog">
                                <li class="layui-col-md2 layui-col-xs6">
                                    <a href="javascript:;" class="x-admin-backlog-body">
                                        <h3>今日订单个数</h3>
                                        <p>
                                            <cite>${TodayOrder!""}</cite></p>
                                    </a>
                                </li>
                                <li class="layui-col-md2 layui-col-xs6">
                                    <a href="javascript:;" class="x-admin-backlog-body">
                                        <h3>本周订单个数</h3>
                                        <p>
                                            <cite>${WeekOrder!""}</cite></p>
                                    </a>
                                </li>
                                <li class="layui-col-md2 layui-col-xs6">
                                    <a href="javascript:;" class="x-admin-backlog-body">
                                        <h3>本月订单个数</h3>
                                        <p>
                                            <cite>${MonthOrder!""}</cite></p>
                                    </a>
                                </li>
                                <li class="layui-col-md2 layui-col-xs6">
                                    <a href="javascript:;" class="x-admin-backlog-body">
                                        <h3>邮件个数</h3>
                                        <p>
                                            <cite>${mailTotal!""}</cite></p>
                                    </a>
                                </li>
                                <li class="layui-col-md2 layui-col-xs6">
                                    <a href="javascript:;" class="x-admin-backlog-body">
                                        <h3>公告个数</h3>
                                        <p>
                                            <cite>${announcementTotal!""}</cite></p>
                                    </a>
                                </li>
                                <li class="layui-col-md2 layui-col-xs6 ">
                                    <a href="javascript:;" class="x-admin-backlog-body">
                                        <h3>附件个数</h3>
                                        <p>
                                            <cite>${attachmentTotal!""}</cite></p>
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                
               <div class="layui-col-md12">
                    <div class="layui-card">
                        <div class="layui-card-header">公告信息</div>
                        <div class="layui-card-body ">
                            <table class="layui-table">
                            	<thead>
                                  <th width="500" style="text-align:center">公告内容</th>
                                  <th width="80" style="text-align:center;">发布者</th>
                                  <th width="110" style="text-align:center">发布时间</th>
                                </thead>
                                <tbody>
                                	<#if PageInfo.list?size gt 0>
                                		<#list PageInfo.list as announcement>
                                			<tr>
		                                        <td>${announcement.content!""}</td>
		                                        <td style="text-align:center;">
		                                        	<#if allAdmins?size gt 0>
		                                        		<#list allAdmins as admin>
		                                        			<#if admin.id == announcement.adminId>
		                                        				${admin.name!""}
		                                        			</#if>
		                                        		</#list>
		                                        	</#if>
		                                        </td>
		                                        <td style="text-align:center">${announcement.createTime?string('yyyy-MM-dd HH:mm:ss')!""}</td>
		                                    </tr>
                                		</#list>
                                	</#if>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                
                <div class="layui-col-md12">
                    <div class="layui-card">
                        <div class="layui-card-header">系统信息</div>
                        <div class="layui-card-body ">
                            <table class="layui-table">
                                <tbody>
                                    <tr>
                                        <th>运行环境</th>
                                        <td>Idea2019.3.5 + jdk1.8 + MYSQL5.7 + Maven3.6.3 + Redis5.0.5</td></tr>
                                    <tr>
                                        <th>MYSQL版本</th>
                                        <td>5.7</td></tr>
                                    <tr>
                                        <th>开发日期</th>
                                        <td>2020-11-05 --- 2020-11-25</td></tr>
                                    <tr>
                                        <th>上传附件大小限制</th>
                                        <td>200M</td></tr>
                                    <tr>
                                        <th>权限有效时间</th>
                                        <td>30mins</td></tr>
                                    <tr>
                                        <th>上传图片大小限制</th>
                                        <td>2M</td></tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="layui-col-md12">
                    <div class="layui-card">
                        <div class="layui-card-header">开发团队</div>
                        <div class="layui-card-body ">
                            <table class="layui-table">
                                <tbody>
                                    <tr>
                                        <th>开发者</th>
                                        <td>杨杨吖(823208782@qq.com)</td></tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <style id="welcome_style"></style>
                <div class="layui-col-md12">
                    <blockquote class="layui-elem-quote layui-quote-nm" style="text-align:center">Copyright (c)2020-杨杨吖</blockquote></div>
            </div>
        </div>
        </div>
    </body>
</html>