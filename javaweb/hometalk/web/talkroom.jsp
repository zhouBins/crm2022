
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>



<head>
    <style type="text/css">

        #div1{
            position: absolute;
            left: 0;
            width: 450px;
            height: 200px;
            border: 1px;
            font-size: 15px;
            text-indent: 30px;
            /*段落首行缩进，text-indent的值为font-size的2倍，相当于缩进2个汉字*/
            line-height: 22px;
            /*line-height的值为font-size的1.5倍，即1.5倍行距，使用line-height: 1.5效果相同;*/
            text-align:justify;
        }

        #div2{position: absolute;
            right: 0;
            width: 450px;
            height: 200px;
            border: 1px;
            font-size: 15px;
            text-indent: 30px;
            /*段落首行缩进，text-indent的值为font-size的2倍，相当于缩进2个汉字*/
            line-height: 22px;
            /*line-height的值为font-size的1.5倍，即1.5倍行距，使用line-height: 1.5效果相同;*/
            text-align:justify;
        }


        body {
            overflow:hidden;
            text-align: center;
            /* 加载背景图 */
            background-image: url(img/田野2人.jpg);
            /* 背景图垂直、水平均居中 */
            background-position: center center;
            /* 背景图不平铺 */
            background-repeat: no-repeat;
            /* 当内容高度大于图片高度时，背景图像的位置相对于viewport固定 */
            background-attachment: fixed;
            /* 让背景图基于容器大小伸缩 */
            background-size: cover;

        }

        #roomchars{
            text-align:left;
            width:600px;
            height:120px;


        }

        #key{
            margin:0 auto;
        }

        span{
            color:black;
            font-size:16px;
        }

    </style>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>沟通房间</title>
</head>
<body>
<span id="div1">
	<p id="p1">注意：请严格遵守沟通流程！1、沟通前置需知：房间内的人们都分为两方，一方是倾听者；另一方是讲述者。通常讲述者是一个人。</p>
	<p id="p2">2、大致流程：讲述者先根据"非暴力沟通四要素"进行有序的讲述。在此期间，倾听者请不要发言！（即使对方可能会错意你的原意，
	你想纠正TA，也请等待，之后会有倾听者的专属发言）。讲述者"非暴力沟通四要素"完毕后，倾听者进行"非暴力沟通四要素"。流程反复执行直到互相了解对方的需要。</p>
	<p>3、二、倾听者:关切地倾听他人，而不解读为批评或指责并给予反馈<br>
	(1）观察<br>
	你所观察（看、听、回忆、想)到的有助于(或无助于）你的福祉的具体行为:{举实例}<br>
	“……（看、听、想到你看到的/听到的），你说的是这回事吗？”<br>

	(2）感受<br>
	对于这些行为，你有什么样的感受（是情感而非思想）：{举实例}<br>
	“你感到……是吗?”<br>

	(3）需要<br>
	什么样的需要或价值（而非偏好或某种具体的行为）导致你那样的感受:{举实例}<br>
	“因为你需要……是吗？”<br>

	(4）请求<br>
	关切地倾听那些能丰富你生命的具体请求,而不解读为命令:{举实例}<br>
	“所以,你想……是吗？”<br><br><br><br>
	<a href="${pageContext.request.contextPath}/example.jsp" >父亲用非暴力沟通与儿子谈论生死攸关的问题</a></p>
</span>
<span id="div2">
	3、细致流程：一、讲述者:诚实地表达自己,而不批评、指责<br>
	(1）观察<br>
	我所观察(看、听、回忆、想)到的有助于(或无助于）我的福祉的具体行为{举实例}:<br>
	“当我（看、听、想到我看到的/听到的)……”<br>
	注意[观察和评论很容易混淆，我们需要表达观察而非评价]<br>
	例子：<br>
	评价：你很少配合我/他经常过来<br>
	观察：我最近组织三次活动，每次你都说你不愿参加/他每周最少过来三次<br>

	(2）感受<br>
	对于这些行为,找有什么样的感受（情感而非思想):“我感到……”{举实例}<br>
	注意[感受和总结很容易混淆，我们需要表达感受而非总结]<br>
	例子：<br>
	总结：我觉得你不爱我/我觉得我被误解了<br>
	感受：我很伤心或者我十分痛苦/我感到郁闷或我很伤心<br>

	(3）需要<br>
	什么样的需要或价值（而非偏好或某种具体的行为)导致我那样的感受:{举实例}<br>
	“因为我需要……”<br>
	例子：我需要尊重/我需要关心<br>

	(4)请求<br>
	清楚地请求(而非命令)那些能丰富我生命的具体行为,{举实例}<br>
	“你是否愿意……?”<br>
	注意[请求和命令很容易混淆，我们需要表达请求而非命令并且是具体到某件事的请求]<br>
	例子：<br>
	命令：请打开窗户/我很孤独希望你今晚能陪我聊聊<br>
	请求：帮我打开窗户好吗？/<a href="${pageContext.request.contextPath}/theatre.jsp" >[请求]小剧场</a><br>

</span>
<h1>致力于营造一个良好的沟通环境</h1>
<table id="key" style="border: 1px solid #00F;">
    <tbody>
    <tr>
        <td colspan="2" align="center">
            <h3>${sessionScope.room.roomTheme}</h3>
        </td>
    </tr>


    <tr>
        <td width="600px" height="300px" style="border: 1px solid #00F; vertical-align: top;" id="content"
            name="content">
            <div>
            <table id="tbRecord">
                <tbody id="record" style="display:block;height:300px; width:500px; overflow:auto;">
                <c:forEach items="${sessionScope.newsList}" var="news">

                    <tr>
                        <td>${news}</td>
                    </tr>

                </c:forEach>
                </tbody>
            </table>
            </div>
        </td>

    </tr>


    </tbody>
    <tfoot>
    <tr>
        <td colspan="2" align="center">
            <form id="sendForm" action="${pageContext.request.contextPath}/room/send">
                <textarea type="text"  name="roomchars" id="roomchars"></textarea><br>
                <span id="roomcharsError"></span><br>
                <input type="hidden" value="${sessionScope.room.roomTheme}"  name="roomtheme">
                <input type="hidden" value="${sessionScope.user.usercall}" name="usercall">
                <input type="button" value="发送信息" id="submitBtn">

            </form>
        </td>
    </tr>
    <tr>
        <td colspan="2" align="center">

            <a href="${pageContext.request.contextPath}/room/del?username=${user.username}&roomtheme=${sessionScope.room.roomTheme}">
                <input type="button" value="关闭房间">
            </a>

        </td>
    </tr>
    </tfoot>
</table>


<script type="text/javascript">
    var optAction = false;      //定义判断条件false，if(!optAction)来判断
    var autoTime;               //定时器
    var body = document.getElementsByTagName("body")[0];

    //当有鼠标和键盘激活时清除自动刷新页面定时器
    body.onkeydown = body.onmousemove = body.onclick = function(){
        optAction = true;           //让判断条件为true
        clearTimeout(autoTime);     //清除自动刷新页面定时器
        autoTime = setTimeout(function(){
            optAction = false;      //页面无操作后3秒，重时开启定时器
        },3000)
    }

    //每三秒自执行
    setTimeout(function(){
        setInterval(function(){
            if(!optAction){
                window.location.reload();
            }
        },3000)
    },5000)





    window.onload = function(){//获得roomtheme的span标签
        var roomcharsErrorSpan = document.getElementById("roomcharsError");
        //给用户名文本框绑定blur事件
        var roomcharsElt = document.getElementById("roomchars");
        roomcharsElt.onblur = function(){
            //获取用户名
            var roomchars = roomcharsElt.value;

            //判断用户名是否为空
            if(roomchars === ""){
                //用户名为空
                roomcharsErrorSpan.innerText = "发送内容不能为空";
            }else{
                //用户名不为空
                //继续判断长度[6-14]
                if(roomchars.length<1||roomchars.length>255){
                    //用户名长度非法
                    roomcharsErrorSpan.innerText = "一次发送长度必须在[1-255]之间";
                }else{
                    //用户名长度非法
                    //继续判断是否含有特殊符号

                    var ok1 = roomchars.match(/观察/g);

                    var ok2 = roomchars.match(/请求/g);

                    var ok3 = roomchars.match(/感受/g);

                    var ok4 = roomchars.match(/需要/g);

                    if(ok1 == null && ok2 == null && ok3 == null && ok4 == null ){
                        roomcharsErrorSpan.innerText = "发送内容里必须包含以下一个或以上的词语：观察，感受，需要，请求";

                    }else{//合格


                    }

                }
            }

        }

        //给roomtheme这个文本框绑定获得焦点事件
        roomcharsElt.onfocus = function(){
            //清空非法的信息
            roomcharsErrorSpan.innerText = "";
        }

        //给提交按钮绑定鼠标单击事件
        var submitBtnElt = 	document.getElementById("submitBtn");
        submitBtnElt.onclick = function(){

            roomcharsElt.focus();
            roomcharsElt.blur();

            //当所有表单项都是合法的时候，提交表单
            if(roomcharsErrorSpan.innerText =="" ){

                //获取表单对象
                var sendFormElt = document.getElementById("sendForm");
                //提交表单

                sendFormElt.submit();
            }
        }
    }


</script>
</body>


</html>