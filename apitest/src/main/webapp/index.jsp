<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index.jsp</title>
<script>
$(function() {
	
	$("#enter").click(function(){
	$.ajax({
		url : "${root}/api/enter.api",
		type : "get",
		data : {"key":"keykey"},
		success :function(data){
			alert(data);
			$("#result").append(data);
		}
				
	});
		
		
	});
	
	
	
	
});

</script>
</head>
<body>
안녕! 여긴 APITest하는 곳이야.
<button id="enter">controller연결</button>
<div id="result"></div>
</body>
</html>