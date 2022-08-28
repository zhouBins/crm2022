<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<style>

		body {

			text-align: center;
			/* 加载背景图 */
			background-image: url(/img/chuSheng.jpg);
			/* 背景图垂直、水平均居中 */
			background-position: center center;
			/* 背景图不平铺 */
			background-repeat: no-repeat;
			/* 当内容高度大于图片高度时，背景图像的位置相对于viewport固定 */
			background-attachment: fixed;
			/* 让背景图基于容器大小伸缩 */
			background-size: cover;

		}
		h2{
			margin-top:40px;
		}

		form{
			overflow:hidden;
		}

		#login{
			margin-left:65px;
		}

		#userid{
			margin-left:16px;
		}

		#regBut{
			margin-right:185px;
		}
	</style>

	<meta charset="utf-8">
	<title>家庭沟通</title>
</head>
<body>
<h1>家庭沟通</h1>
<hr>
<h2>致力于营造一个良好的沟通环境</h2>


<form action="${pageContext.request.contextPath}/user/login" method = "post" >
	用户ID<input type="text" name="userid" id="userid"/><br>

	用户密码<input type="text" name="userpassword" id="userpw"/><br>

	<input type="checkbox" name="flag" value="1"/>10天内免登录

	<input type="submit" value="登录" display="block" id="login" />
</form>

<a href="reg.jsp">
	<input type="button" value="注册" id="regBut"/>
</a>



</body>


</html>
