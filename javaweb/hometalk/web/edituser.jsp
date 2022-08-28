<%@page contentType="text&html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
	<head>
		<style type="text/css">
			span{
			color:red;
			font-size:12px;
			}

			body {

			text-align: center;
			/* 加载背景图 */
			background-image: url(/img/一手星星一手月亮.jpg);
			/* 背景图垂直、水平均居中 */
			background-position: center center;
			/* 背景图不平铺 */
			background-repeat: no-repeat;
			/* 当内容高度大于图片高度时，背景图像的位置相对于viewport固定 */
			background-attachment: fixed;
			/* 让背景图基于容器大小伸缩 */
			background-size: cover;

			}
			#userid{

			margin-left:15px;
			}



		</style>
		<meta charset='utf-8'>
		<title>修改个人信息</title>
	</head>
	<body>
		<h1>修改个人信息</h1>
		<hr>
		<form id="userForm" action="${pageContext.request.contextPath}/user/updateuser" method="post">

			用户ID<input type="text" name="userid" id="userid" value="${sessionScope.userid}" readonly/><br>
			用户昵称<input type="text" name="username" id="username" value="${sessionScope.username}"/>
			<span id="usernameError"></span><br>
			默认职称<input type="text"  id="usercall" value="${sessionScope.usercall}" readonly/><br>

			<input type="radio" name="usercall" value="父亲" />父亲
			<input type="radio" name="usercall" value="母亲" />母亲
			<input type="radio" name="usercall" value="女儿" />女儿
			<input type="radio" name="usercall" value="儿子" />儿子
			<input type="radio" name="usercall" value="妻子" />妻子
			<input type="radio" name="usercall" value="丈夫" />丈夫
			<input type="radio" name="usercall" value="男朋友" />男朋友
			<input type="radio" name="usercall" value="女朋友" />女朋友
			<input type="radio" name="usercall" value="朋友" />朋友
			<input type="radio" name="usercall" value="哥哥" />哥哥
			<input type="radio" name="usercall" value="姐姐" />姐姐
			<input type="radio" name="usercall" value="弟弟" />弟弟
			<input type="radio" name="usercall" value="妹妹" />妹妹
			<!--<input type="text" name="homename"  />其他-->
			<input type="button" id="submitBtn" value="修改" /><br>
		</form>



		<script type="text/javascript">
			window.onload = function(){//获得username的span标签
			var usernameErrorSpan = document.getElementById("usernameError");
			//给用户名文本框绑定blur事件
			var usernameElt = document.getElementById("username");
			usernameElt.onblur = function(){
			//获取用户名
			var username = usernameElt.value;
			//去除前后空白
			username = username.trim();
			//判断用户名是否为空
			if(username === ""){
			//用户名为空
			usernameErrorSpan.innerText = "用户名不能为空";
			}else{
			//用户名不为空
			//继续判断长度[6-14]
			if(username.length<2||username.length>16){
			//用户名长度非法
			usernameErrorSpan.innerText = "用户名长度必须在[2-16]之间";
			}else{
			//用户名长度非法
			//继续判断是否含有特殊符号
			var regExp =/^[\u4e00-\u9fa5A-Za-z0-9]+$/;
			var ok = regExp.test(username);
			if(ok){
			//用户名最终合法
			}else{
			//用户名中含有非法符号
			usernameErrorSpan.innerText = "用户名只能由汉字，数字和字母组成";
			}
			}
			}

			}

			//给username这个文本框绑定获得焦点事件
			usernameElt.onfocus = function(){
			//清空非法的信息
			usernameErrorSpan.innerText = "";
			}


			//给提交按钮绑定鼠标单击事件
			var submitBtnElt = 	document.getElementById("submitBtn");
			submitBtnElt.onclick = function(){




			usernameElt.focus();
			usernameElt.blur();





			//当所有表单项都是合法的时候，提交表单
			if(usernameErrorSpan.innerText ==""){

			//获取表单对象
			var userFormElt = document.getElementById("userForm");
			//提交表单

			userFormElt.submit();
			}
			}
			}

		</script>
	</body>
</html>


