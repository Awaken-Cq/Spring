package com.kitri.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.kitri.member.model.MemberDetailDto;
import com.kitri.member.model.MemberDto;
import com.kitri.member.model.dao.MemberDaoImpl;
import com.kitri.member.model.service.MemberServiceImpl;
import com.kitri.util.SiteConstance;

//뒷단 컨트롤러

public class MemberController {

	private static MemberController memberController;

	static {

		memberController = new MemberController();
	}

	public static MemberController getMemberController() {
		return memberController;
	}

	public String register(HttpServletRequest request, HttpServletResponse response) {
		String path = "/index.jsp";
		MemberDetailDto memberDetailDto = new MemberDetailDto();
		memberDetailDto.setName(request.getParameter("name"));
		memberDetailDto.setId(request.getParameter("id"));
		memberDetailDto.setPass(request.getParameter("pass"));
		memberDetailDto.setEmailid(request.getParameter("emailid"));
		memberDetailDto.setEmaildomain(request.getParameter("emaildomain"));

		memberDetailDto.setTel1(request.getParameter("tel1"));
		memberDetailDto.setTel2(request.getParameter("tel2"));
		memberDetailDto.setTel3(request.getParameter("tel3"));
		memberDetailDto.setZipcode(request.getParameter("zipcode"));
		memberDetailDto.setAddress(request.getParameter("address"));
		memberDetailDto.setAddressDetail(request.getParameter("address_detail"));

		int cnt = MemberServiceImpl.getMemberService().registerMember(memberDetailDto);
		// 체크해보기!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		if (cnt != 0) {
			request.setAttribute("userInfo", memberDetailDto);
			path = "/user/member/registerok.jsp";
		} else {
			path = "/user/member/registerfail.jsp";
		}
		return path;
	}

	public String login(HttpServletRequest request, HttpServletResponse response) {
		String path = "/index.jsp";
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		System.out.println(id + pass);
		MemberDto memberDto = MemberServiceImpl.getMemberService().loginMember(id, pass);

		if (memberDto != null) {
			/////////////////// cookie//////////////////
			String idsv = request.getParameter("idsave");
			if ("idsave".equals(idsv)) {
				Cookie cookie = new Cookie("kid_inf", id);
				cookie.setDomain("localhost");
				cookie.setPath(request.getContextPath());
				cookie.setMaxAge(60 * 60 * 24 * 365 * 50);
				response.addCookie(cookie);
			} else {
				Cookie cookie[] = request.getCookies();
				if (cookie != null) {
					for (Cookie c : cookie) {
						if ("kid_inf".equals(c.getName())) {
							c.setDomain("localhost");
							c.setPath(request.getContextPath());
//							바로 만료시켜버림
							c.setMaxAge(0);
							response.addCookie(c);
							break;
						}
					}
				}
			}
			///////////////////////////////////////////
			/////////////////// session/////////////////
			HttpSession session = request.getSession();
			session.setAttribute("userInfo", memberDto);
			///////////////////////////////////////////
			path = "/user/login/loginok.jsp";

		} else {
			path = "/user/login/loginfail.jsp";
		}

		return path;
	}

	public String logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
//		session안에 userInfo에 null을 넣어라
//		session.setAttribute("userInfo", null);
//		session안에 userInfo란 이름의 Dto를 지워라
//		session.removeAttribute("userInfo");
//		session안에 모든 데이터들을 지워라
		session.invalidate();
		return "/user/login/login.jsp";
	}

	public void deletemember(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		
	}
	
	public String selectMemberDetail(HttpServletRequest request, HttpServletResponse response) {
		String path = "/user/member/modify.jsp";
		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto)session.getAttribute("userInfo");
		System.out.println(memberDto);//id반환 확인		
		 
		MemberDetailDto memberDetailDto = 
				MemberDaoImpl.getMemberDao().getMember(memberDto.getId());
		System.out.println(memberDetailDto);
		request.setAttribute("memberDetailDto", memberDetailDto);
		return path;
		
	}
	
	public String modify(HttpServletRequest request, HttpServletResponse response) {
		String path = "/index.jsp";
//		MemberDetailDto memberDetailDto = new MemberDetailDto();
//		memberDetailDto.setName(request.getParameter("name"));
//		memberDetailDto.setId(request.getParameter("id"));
//		memberDetailDto.setPass(request.getParameter("pass"));
//		memberDetailDto.setEmailid(request.getParameter("emailid"));
//		memberDetailDto.setEmaildomain(request.getParameter("emaildomain"));
//
//		memberDetailDto.setTel1(request.getParameter("tel1"));
//		memberDetailDto.setTel2(request.getParameter("tel2"));
//		memberDetailDto.setTel3(request.getParameter("tel3"));
//		memberDetailDto.setZipcode(request.getParameter("zipcode"));
//		memberDetailDto.setAddress(request.getParameter("address"));
//		memberDetailDto.setAddressDetail(request.getParameter("address_detail"));
//
//		int cnt = MemberServiceImpl.getMemberService().registerMember(memberDetailDto);
//		// 체크해보기!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//		if (cnt != 0) {
//			request.setAttribute("userInfo", memberDetailDto);
//			path = "/user/member/registerok.jsp";
//		} else {
//			path = "/user/member/registerfail.jsp";
//		}
		return path;
	}
}
