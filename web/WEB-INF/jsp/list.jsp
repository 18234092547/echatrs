<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@include file="common/head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript">
$(function(){
	
})

function btnClick(){
	$.ajax({  
        type: "POST",  
        contentType : "application/json ; charset=utf-8",  
        url:contextPath + "/user/add",  
        data:JSON.stringify({name:"于晓禹",password:"1"}),  
        dataType: "json",  
        async: false,  
        success:function(data){  
        }  
     });  
}
</script>
</head>
<body>
<button onclick="btnClick();">点击添加一个用户</button>
</body>
</html>