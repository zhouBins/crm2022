<%@page contentType="text&html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
    <head>
        <style>

            body {

            text-align: center;
            /* 加载背景图 */
            background-image: url(/img/亲爱的.jpg);
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
        <title>修改个人信息</title>
    </head>
    <body>
        <h1>修改个人信息</h1>

        <a href = "${pageContext.request.contextPath}/user/edituser">
            <input type="button" value="编辑信息"/>
        </a>


        <a href = "${pageContext.request.contextPath}/user/editpw">
            <input type="button" value="修改密码"/>
        </a>


        <a href = "${pageContext.request.contextPath}/user/exit">
            <input type="button" value="退出账号"/>
        </a>

        <table border="1px" align="center" width="50%">
        <tr>
            <th>用户ID</th>
            <th>用户昵称</th>
            <th>默认家庭职称</th>
        </tr>

        <tr>
            <td>${sessionScope.userid}</td>
            <td>${sessionScope.username}</td>
            <td>${sessionScope.usercall}</td>
        </tr>

        </table>





    </body>
    <script type="text/javascript">
        function exit(){
            var ok = window.confirm("是否要退出");
            if(ok){
                document.location.href = "${pageContext.request.contextPath}/user/exit";
            }
        }
    </script>
</html>