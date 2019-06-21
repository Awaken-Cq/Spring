<%@page import="com.kitri.util.MoveUrl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/template/header.jsp"%>


세션있나?
<c:if test="${userInfo != null}">
세션있다.
</c:if>
<c:if test="${userInfo == null}">
세션없다.
</c:if>




<%@ include file="/WEB-INF/views/template/footer.jsp"%>