
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            background-image: url(/img/田野2人.jpg);
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
    <meta charset='utf-8'>
    <title>创建房间</title>
</head>
<body>
<div>
    <h1>创建房间</h1>
    <hr>
    <form id="roomForm" action="${pageContext.request.contextPath}/room/create" method="post">
        房间主题<input type="text" name="roomtheme" id="roomtheme" />
        <span id="roomthemeError"></span><br>
        房间密码<input type="text" name="roompw" id="roompw" />
        <span id="roompwError"></span><br>
        本次沟通职称选择<br>
        默认职称<input type="text"  id="usercall" value="${sessionScope.user.usercall}" readonly/><br>
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


        <input type="button" id="submitBtn" value="创建" /><br>


        <script type="text/javascript">
            window.onload = function(){//获得roomtheme的span标签
                var roomthemeErrorSpan = document.getElementById("roomthemeError");
                //给用户名文本框绑定blur事件
                var roomthemeElt = document.getElementById("roomtheme");
                roomthemeElt.onblur = function(){
                    //获取用户名
                    var roomtheme = roomthemeElt.value;
                    //去除前后空白
                    roomtheme = roomtheme.trim();
                    //判断用户名是否为空
                    if(roomtheme === ""){
                        //用户名为空
                        roomthemeErrorSpan.innerText = "主题不能为空";
                    }else{
                        //用户名不为空
                        //继续判断长度[6-14]
                        if(roomtheme.length<1||roomtheme.length>255){
                            //用户名长度非法
                            roomthemeErrorSpan.innerText = "主题长度必须在[1-255]之间";
                        }else{
                            //用户名长度非法
                            //继续判断是否含有特殊符号
                            var regExp =/^[\u4e00-\u9fa5A-Za-z0-9]+$/;
                            var ok = regExp.test(roomtheme);
                            if(ok){
                                //用户名最终合法
                            }else{
                                //用户名中含有非法符号
                                roomthemeErrorSpan.innerText = "主题只能由汉字，数字和字母组成";
                            }
                        }
                    }

                }

                //给roomtheme这个文本框绑定获得焦点事件
                roomthemeElt.onfocus = function(){
                    //清空非法的信息
                    roomthemeErrorSpan.innerText = "";
                }





                //获取密码错误提示的span标签
                var roompwErrorSpan = document.getElementById("roompwError");
                //获取确认密码对象
                var roompwElt = document.getElementById("roompw");
                //绑定blur事件
                roompwElt.onblur = function(){
                    //获得密码

                    var roompw = roompwElt.value;

                    //判断密码是否为空
                    if(roompw === ""){
                        //密码为空
                        roompwErrorSpan.innerText = "密码不能为空";
                    }
                    //给roompw这个文本框绑定获得焦点事件

                }

                roompwElt.onfocus = function(){
                    //清空非法的信息
                    roompwErrorSpan.innerText = "";
                }














                //给提交按钮绑定鼠标单击事件
                var submitBtnElt = 	document.getElementById("submitBtn");
                submitBtnElt.onclick = function(){




                    roomthemeElt.focus();
                    roomthemeElt.blur();

                    roompwElt.focus();
                    roompwElt.blur();






                    //当所有表单项都是合法的时候，提交表单
                    if(roomthemeErrorSpan.innerText =="" && roompwErrorSpan.innerText ==""){

                        //获取表单对象
                        var roomFormElt = document.getElementById("roomForm");
                        //提交表单

                        roomFormElt.submit();
                    }
                }
            }

        </script>



    </form>
</div>
</body>
</html>
