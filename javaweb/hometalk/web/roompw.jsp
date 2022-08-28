<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/8/18
  Time: 20:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <style type="text/css">
    body {

    text-align: center;
    /* 加载背景图 */
    background-image: url(/img/温蒂公主的侍卫.jpg);
    /* 背景图垂直、水平均居中 */
    background-position: center center;
    /* 背景图不平铺 */
    background-repeat: no-repeat;
    /* 当内容高度大于图片高度时，背景图像的位置相对于viewport固定 */
    background-attachment: fixed;
    /* 让背景图基于容器大小伸缩 */
    background-size: cover;

    }

    #houseOwner{

      margin-left:32px;
    }

    </style>
    <meta charset='utf-8'>
      <title>输入房间密码</title>
  </head>
  <body>
    房间主题<input type="text"  value="${pageContext.request.getParameter("roomTheme")}" readonly > <br>
    房主<input id="houseOwner" type="text" value="${pageContext.request.getParameter("houseOwner")}" readonly><br>
    <form action="${pageContext.request.contextPath}/room/join">
      房间密码<input type="text" name="roomPW"><br>
      <input type="hidden" name="roomTheme" value="${pageContext.request.getParameter("roomTheme")}">
      <input type="submit" value="确认">
    </form>

  </body>
</html>
