<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="bs.jsp"%>
<html>
<head>
    <meta charset="UTF-8">
    <style>
        .panel {
            width: 60%;
            margin: 0 auto;
        }
    </style>
    <title>添加学生</title>
</head>
<body>
<div class="container">
    <div class="page-header">
        <h3>
            <small>
                添加学生
            </small>
        </h3>
    </div>

    <div class="panel panel-success">
        <div class="panel-heading">
            学生信息
        </div>
        <div class="panel-body">
            <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/student?cmd=pageadd">
                <div class="form-group">
                    <label class="col-sm-2 control-label">姓名</label>
                    <div class="col-sm-8">
                        <input class="form-control" name="sname">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">年龄</label>
                    <div class="col-sm-8">
                        <input class="form-control" name="age">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">性别</label>
                    <div class="col-sm-8">
                        <div class="radio">
                            <label>
                                <input type="radio" name="sex" value="男" checked>男
                            </label>
                            <label>
                                <input type="radio" name="sex" value="女">女
                            </label>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label">住址</label>
                    <div class="col-sm-8">
                        <input class="form-control" name="addr">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">班级</label>
                    <div class="col-sm-4">
                        <select class="form-control" name="cid">
                            <c:forEach items="${classes}" var="c">
                                <option value="${c.cid}">${c.cname}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-3 col-sm-4">
                        <a href="${pageContext.request.contextPath}/student?cmd=pagelist" class="btn btn-default">取消</a>
                    </div>
                    <div class="col-sm-3">
                        <button type="submit" class="btn btn-success">保存</button>
                    </div>
                </div>
            </form>
        </div>
        <div class="panel-footer text-right">
            泽林教育2000-2019.
        </div>
    </div>
</div>
</body>
</html>
