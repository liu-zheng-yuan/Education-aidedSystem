<%--
  Created by IntelliJ IDEA.
  User: lzhy
  Date: 2018/12/10
  Time: 16:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%
    pageContext.setAttribute("APP_PATH",request.getContextPath());
%>
<html lang="zh-CN">
<head>
    <title>Title</title>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="keys" content="">
    <meta name="author" content="">
    <style>

    </style>
</head>
<body>
<form method="post" >
    <input type="text" id="SloginAcct" name="loginAcct">
    <input type="text" id="Spassword" name="passWord">
    <button onclick="doSLogin()" >学生登陆</button>

</form>
<form method="post" >
    <input type="text" id="TloginAcct" name="loginAcct">
    <input type="text" id="Tpassword" name="passWord">
    <button onclick="doTLogin()">教师登陆</button>
</form>
<script src="jquery/jquery-2.1.1.min.js"></script>
<script>
    function doSLogin() {
        var loginAcct = $("#SloginAcct").val();
        var passWord = $("#Spassword").val();
        alert("sdf")
        $.ajax({type:"post",
            url:"doSLogin",
            data:{
                "loginAcct":loginAcct,
                "passWord":passWord
            },
            success:function (result) {
                if(result=="success"){
                    alert("学生登陆成功")
                }else{
                    alert("登陆失败")
                }
            }
        })
    }
    function doTLogin() {
        var loginAcct = $("#TloginAcct").val();
        var passWord = $("#Tpassword").val();
        $.ajax({type:"post",
            url:"doTLogin",
            data:{
                "loginAcct":loginAcct,
                "passWord":passWord
            },
            success:function (result) {
                if(result=="success"){
                    alert("老师登陆成功")
                }else{
                    alert("登陆失败")
                }
            }
        })
    }
</script>
</body>
</html>
