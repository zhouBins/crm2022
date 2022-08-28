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
			background-image: url(/img/大合抱2.jpg);
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
		<meta charset="utf-8">
		<title>注册账号</title>
	</head>
	<body>

		<h1>注册账号</h1>
		<hr>
		<form id="userForm" action="${pageContext.request.contextPath}/user/reg" method="post" >
			用户ID<input type="text" name="userid" id="userid" />
			<span id="useridError"></span><br>
			用户昵称<input type="text" name="username" id="username" />
			<span id="usernameError"></span><br>
			账号密码<input type="text" name="userpassword" id="userpassword" />
			<span id="pwdError"></span><br>
			确认密码<input type="text" id="userpassword2" />
			<span id="pwd2Error"></span><br>
			默认职称

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

			<br>

			<input type="button" id="submitBtn" value="注册" /><br>
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




			//获取密码错误提示的span标签
			var pwdErrorSpan = document.getElementById("pwdError");
			//获取确认密码对象
			var userpasswordElt = document.getElementById("userpassword");
			//绑定blur事件
			userpasswordElt.onblur = function(){
			//获得密码

			var userpassword = userpasswordElt.value;

			//判断密码是否为空
			if(userpassword === ""){
			//密码为空
			pwdErrorSpan.innerText = "密码不能为空";
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
			var userpasswordElt = document.getElementById("userpassword");
			var userpassword = userpasswordElt.value;
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







			//获取userid错误提示的span标签
			var useridErrorSpan = document.getElementById("useridError");
			//获取userid对象
			var useridElt = document.getElementById("userid");
			//绑定blur事件
			useridElt.onblur = function(){
			//获得用户id
			var userid = useridElt.value;

			//判断用户id是否为空
			if(userid === ""){
			//为空
			useridErrorSpan.innerText = "用户ID不能为空";
			}else{

			if(userid.length<2||userid.length>12){
			//用户名id长度非法
			useridErrorSpan.innerText = "用户id长度必须在[2-11]之间";
			}else{
			//用户名id长度合法
			//继续判断是否含有特殊符号
			var regExp =/^[0-9]+$/;
			var ok = regExp.test(userid);
			if(ok){
			//用户名最终合法
			}else{
			//用户名中含有非法符号
			useridErrorSpan.innerText = "用户id只能由数字组成";
			}
			}
			}
			}
			//给这个文本框绑定获得焦点事件
			useridElt.onfocus = function(){
			//清空非法的信息
			useridErrorSpan.innerText = "";
			}






			//给提交按钮绑定鼠标单击事件
			var submitBtnElt = 	document.getElementById("submitBtn");
			submitBtnElt.onclick = function(){


			useridElt.focus();
			useridElt.blur();

			usernameElt.focus();
			usernameElt.blur();

			userpasswordElt.focus();
			userpasswordElt.blur();

			userpassword2Elt.focus();
			userpassword2Elt.blur();


			//当所有表单项都是合法的时候，提交表单
			if(useridErrorSpan.innerText =="" && usernameErrorSpan.innerText ==""&& pwdErrorSpan.innerText ==""&&pwd2ErrorSpan.innerText ==""){

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
