<%@page import="com.kitri.util.MoveUrl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/template/header.jsp"%>

<c:if test="${userInfo != null}"/>
	<script type="text/javascript">
		function deleteMember(){
			if(confirm("탈퇴하시겠습니까?")){
				$(location).attr("href","${root}/user/delete.kitri");
				
			}
		}
	</script>

<Strong>${userInfo.name }(${userInfo.id })</Strong>님 환영합니다 ^_^<br>
<a href="${root}/user/logout.kitri">로그아웃</a>
<a href="${root}/user/modify.kitri">정보수정</a>
<a href="" onclick="javascript:deleteMember();">회원탈퇴</a>

<c:if test="${'now0914' == userInfo.id}">
<a href="${root}/admin/mvmemberlist.kitri">관리자</a>
</c:if>

<c:if test="${userInfo == null }">
	<c:redirect url="/user/login.kitri"></c:redirect>
</c:if>



<%@ include file="/WEB-INF/views/template/footer.jsp"%>