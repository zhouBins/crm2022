
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<html>
<head>
    <style>

        body {

            text-align: center;
            /* 加载背景图 */
            background-image: url(/img/并肩向前.jpg);
            /* 背景图垂直、水平均居中 */
            background-position: center center;
            /* 背景图不平铺 */
            background-repeat: no-repeat;
            /* 当内容高度大于图片高度时，背景图像的位置相对于viewport固定 */
            background-attachment: fixed;
            /* 让背景图基于容器大小伸缩 */
            background-size: cover;

        }

    </style>

    <meta charset="utf-8">
    <title>房间大厅</title>
</head>
<body>
<h1>致力于营造一个良好的沟通环境</h1>
<hr>
<h2>房间大厅</h2>

<a href="${pageContext.request.contextPath}/roomcreate.jsp" class="input">
    <input type="button" value="创建房间">
</a>

<a href="${pageContext.request.contextPath}/user/user" class="input">
    <input type="button" value="个人中心">
</a>

<a href="${pageContext.request.contextPath}/rooms.jsp" class="input">
    <input type="button" value="刷新页面">
</a>

<table border="1px" align="center" width="50%">
    <tr>
        <th>房间主题</th>
        <th>房主</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${roomList}" var="room">
        <tr>
            <td>${room.roomTheme}</td>
            <td>${room.houseOwner}</td>
            <td>
                <a href="${pageContext.request.contextPath}/roompw.jsp?houseOwner=${room.houseOwner}&roomTheme=${room.roomTheme}">
                    <input type="button" value="加入房间">
                </a>
            </td>
        </tr>
    </c:forEach>

</table>

<br>

</body>
</html>
