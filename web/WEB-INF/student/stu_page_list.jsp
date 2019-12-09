<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="bs.jsp" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <style>
        .table {
            margin-top: 10px;
        }

        .table th, .table td {
            text-align: center;
            vertical-align: middle !important;
        }
        #form1{
            margin-top: 10px;
        }
    </style>
    <script>
        function pageSearch(p) {
            $("#page").val(p);
            $("#form1").submit();
        }
        function pagePreOrNext(n) {
            let page = parseInt($("#page").val()) + n;
            if(page <= 0 ){
                page = 1;
            }else if(page >= ${pageResult.totalPages}){
                page = ${pageResult.totalPages};
            }
            $("#page").val(page);
            $("#form1").submit();
        }
    </script>
    <title>学生列表</title>

</head>

<body>
<div class="container">
    <div class="page-header">
        <h3>
            <small>
                学生列表信息
            </small>
        </h3>
    </div>
    <div class="panel panel-success">
        <div class="panel-heading">
            <div class="row">
                <div class="col-md-10">
                    <h4>学生列表</h4>
                </div>
            </div>
        </div>


        <form method="post" id="form1" action="${pageContext.request.contextPath}/student?cmd=pageSerach" class="form-inline">
            <div class="row">
                <div class="col-md-1">
                    <input type="hidden" name="page" id="page" value="${param.page}">
                </div>
                <div class="col-md-3">
                    <label class="control-label">学生姓名</label>
                    <input type="text" name="sname" value="${param.sname}" class="form-control">
                </div>
                <div class="col-md-3">
                    <label class="control-label">学生住址</label>
                    <input type="text" name="addr" value="${param.addr}" class="form-control">
                </div>
                <div class="col-md-3">
                    <label class="control-label">班级</label>
                    <select class="form-control" name="cid">
                        <option value="0">选择班级</option>
                        <c:forEach items="${classes}" var="c">
                            <option value="${c.cid}" ${c.cid == param.cid?'selected':''}>${c.cname}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-md-1">
                    <a href="javascript:void(0)" onclick="pageSearch('1')" class="btn btn-primary">查询</a>
                </div>
                <div class="col-md-1">
                    <a href="${pageContext.request.contextPath}/student?cmd=pagetoadd" class="btn btn-info btn-right">添加</a>
                </div>
            </div>
        </form>


        <table class="table table-bordered table-hover table-striped">
            <tr>
                <th>学号</th>
                <th>姓名</th>
                <th>性别</th>
                <th>年龄</th>
                <th>住址</th>
                <th>所在班级</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${pageResult.list}" var="stud">
                <tr>
                    <td>${stud.sid}</td>
                    <td>${stud.sname}</td>
                    <td>${stud.sex}</td>
                    <td>${stud.age}</td>
                    <td>${stud.addr}</td>
                    <td>${stud.cname}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/student?cmd=pagetoupdate&sid=${stud.sid}"
                           class="glyphicon glyphicon-pencil btn btn-success btn-sm">修改</a>
                        <a href="${pageContext.request.contextPath}/student?cmd=pagedelete&sid=${stud.sid}"
                           class="glyphicon glyphicon-trash btn btn-danger btn-sm"
                           onclick="return confirm('是否删除')">删除</a>
                    </td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="7">
                    <nav aria-label="Page navigation">
                        <ul class="pagination">
                            <li>
                                <a href="javascript:void(0)" onclick="pagePreOrNext(-1)" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                            <c:forEach begin="1" end="${pageResult.totalPages}" var="p">
                                <li class="${p == pageResult.page ? 'active' : ''}">
                                    <a href="javascript:void(0)" onclick="pageSearch('${p}')">${p}</a>
                                </li>
                            </c:forEach>
                            <li>
                                <a href="javascript:void(0)" onclick="pagePreOrNext(+1)" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </ul>
                    </nav>
                </td>
            </tr>
        </table>
        <div class="panel-footer text-right">
            一共有<label style="color:red">${pageResult.totalCount}</label>数据 泽林教育2000-2019.
        </div>
    </div>
</div>
</body>
</html>