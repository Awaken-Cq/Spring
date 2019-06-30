<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<meta charset="UTF-8">
<title>index.jsp</title>
<script>
$(function() {
	
	$("#enter").click(function(){
	$.ajax({
		url : "${root}/api/enter.api",
		type : "get",
		
		dataType : "json",
		data : {"key":"keykey"},
		success :function(result){
			console.log("넘어옴");
			$("#result").html(result);
			
			
			
			
		}
				
	});
		
		return;
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