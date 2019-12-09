<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="WEB-INF/student/bs.jsp" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
<div class="container">
    <div class="page-header">
        <h3>
            <small>
                登录页面
            </small>
        </h3>
    </div>
    <div class="panel panel-default" style="width: 50%">
        <div class="panel-heading">
            <h3 class="panel-title">登录</h3>
        </div>
        <div class="panel-body">
            <form class="form-signin" method="post" action="/userLogin">
                <label class="sr-only">用户名</label>
                <div class="form-group">
                    <input type="text" name="username" class="form-control" placeholder="输入用户名">
                </div>

                <label class="sr-only">密码</label>
                <div class="form-group">
                    <input type="password" name="password" class="form-control" placeholder="输入密码">
                </div>
                <span style="color: red;">${error}</span>
                <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
            </form>
        </div>
    </div>


</div>
</body>
</html>
