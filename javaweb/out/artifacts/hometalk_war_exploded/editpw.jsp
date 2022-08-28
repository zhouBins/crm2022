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
			background-image: url(/img/许愿.jpg);
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
		<title>修改密码</title>
	</head>

	<body>
		<h1>修改密码</h1>
		<hr>
		<div>
			<form id="pwdForm" action="${pageContext.request.contextPath}/user/updateuserpw" method="post">

				当前密码<input type="text"  id="useroldpassword" />
				<span id="oldpwdError"></span><br>

				新的密码<input type="text" name="userpassword" id="userpassword" />
				<span id="pwdError"></span><br>

				确认密码<input type="text"  id="userpassword2" />
				<span id="pwd2Error"></span><br>

				<input type ="hidden" name="userid" value="${sessionScope.userid}">
				<input type="button" id="submitBtn" value="确认修改" /><br>
			</form>
		</div>

		<script type="text/javascript">
			window.onload = function(){//


			//获取新密码错误提示的span标签
			var pwdErrorSpan = document.getElementById("pwdError");
			//获取新密码对象
			var userpasswordElt = document.getElementById("userpassword");
			//绑定blur事件
			userpasswordElt.onblur = function(){
			//获得密码

			var userpassword = userpasswordElt.value;

			//判断密码是否为空
			if(userpassword === ""){
			//密码为空
			pwdErrorSpan.innerText = "新密码不能为空";
			}
			//给userpassword这个文本框绑定获得焦点事件

			}

			userpasswordElt.onfocus = function(){
			//清空非法的信息
			pwdErrorSpan.innerText = "";
			}



			//获取确认密码错误提示的span标签
			var pwd2ErrorSpan = document.getElementById("pwd2Error");
			//获取确认密码对象
			var userpassword2Elt = document.getElementById("userpassword2");
			//绑定blur事件
			userpassword2Elt.onblur = function(){
			//获得密码和确认密码

			var userpassword2 = userpassword2Elt.value;
			//判断用户名是否为空
			if(userpassword2 === ""){
			//确认密码为空
			pwd2ErrorSpan.innerText = "确认密码不能为空";
			}else{

			if(userpassword != userpassword2){
			//密码不一致
			pwd2ErrorSpan.innerText ="密码不一致";
			}else{//密码一致}
			}
			}
			//给userpassword2这个文本框绑定获得焦点事件
			userpassword2Elt.onfocus = function(){
			//清空非法的信息
			pwd2ErrorSpan.innerText = "";
			}

			}





			//获取old密码错误提示的span标签
			var oldpwdErrorSpan = document.getElementById("oldpwdError");
			//获取确认密码对象
			var useroldpasswordElt = document.getElementById("useroldpassword");
			//绑定blur事件
			useroldpasswordElt.onblur = function(){
			//获得密码

			var useroldpassword = useroldpasswordElt.value;

			//判断密码是否为空
			if(useroldpassword === ""){
			//密码为空
			oldpwdErrorSpan.innerText = "密码不能为空";
			}
			//给userpassword这个文本框绑定获得焦点事件

			}

			useroldpasswordElt.onfocus = function(){
			//清空非法的信息
			oldpwdErrorSpan.innerText = "";
			}




			//给提交按钮绑定鼠标单击事件
			var submitBtnElt = 	document.getElementById("submitBtn");
			submitBtnElt.onclick = function(){

			useroldpasswordElt.focus();
			useroldpasswordElt.blur();

			userpasswordElt.focus();
			userpasswordElt.blur();

			userpassword2Elt.focus();
			userpassword2Elt.blur();




			//当所有表单项都是合法的时候，提交表单
			if(pwdErrorSpan.innerText ==""&& pwd2ErrorSpan.innerText =="" && oldpwdErrorSpan.innerText ==""){

			//获取表单对象
			var pwdFormElt = document.getElementById("pwdForm");
			//提交表单

			pwdFormElt.submit();
			}
			}
			}
		</script>
	</body>
</html>