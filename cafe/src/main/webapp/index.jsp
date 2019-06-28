<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kitri.cafe.member.model.MemberDto"%>
<%
response.sendRedirect(request.getContextPath() + "/boardadmin/boardmenu");

MemberDto memberDto = new MemberDto();
memberDto.setId("now1234");
memberDto.setName("지금");
memberDto.setEmail("now1234@naver.com");

session.setAttribute("userInfo", memberDto);
%>