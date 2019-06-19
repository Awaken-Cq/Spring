package com.kitri.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.kitri.member.model.MemberDetailDto;
import com.kitri.member.model.service.MemberServiceImpl;
import com.kitri.util.MoveUrl;
import com.kitri.util.SiteConstance;

//앞단 컨트롤러의 역할 요구에 따라 어디로 가라라는 제어역할
@WebServlet("/user")
public class MemberFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String act = request.getParameter("act");
		String path = "/index.jsp";
//		윗방식으로 처리하려면 먼저 act가 null인지 비교를 해야함 그렇지 않으면 nullpointEx이 뜰 가능성이있음.
//		if(act != null) {
//		if(act.equals("")) {
//		}	}
		if ("mvjoin".equals(act)) {
			System.out.println(act);
			MoveUrl.redirect(request, response, "/user/member/member.jsp");
			
		} else if ("mvlogin".equals(act)) {
			System.out.println(act);
			MoveUrl.redirect(request, response, "/user/login/login.jsp");

		} else if ("idcheck".equals(act)) {
//				Ajax용이라 아예 따로하는게 좋음.
//			Ajax라서 다른 부분에 비해 형식이 조금 달라짐
			String sid = request.getParameter("sid");
			String resultXML = MemberServiceImpl.getMemberService().idCheck(sid);
			response.setContentType("text/xml;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print(resultXML);

		} else if ("zipsearch".equals(act)) {
//		Ajax용이라 아예 따로하는게 좋음.
			String doro = request.getParameter("doro");
	
			System.out.println("검색 도로명 : " + doro);
			String resultXML = MemberServiceImpl.getMemberService().zipSearch(doro);
			System.out.println(resultXML);
			response.setContentType("text/xml;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print(resultXML);
		} else if ("register".equals(act)) {
			path = MemberController.getMemberController().register(request, response);
//			sendreirect는 이동하면서 갖고있던것을 모두 버리고 간다. 고로 null이 뜸.
//			그렇기 때문에 session의 forward를 사용해줘야함.
			MoveUrl.forward(request, response, path);
		} else if ("login".equals(act)) {
			path = MemberController.getMemberController().login(request, response);
			MoveUrl.redirect(request, response, path);
		} else if ("logout".equals(act)) {
			MemberController.getMemberController().logout(request, response);
			MoveUrl.redirect(request, response, path);
		} else if ("deletemember".equals(act)) {
			MemberController.getMemberController().deletemember(request,response);
		} else if ("mvmodify".equals(act)) {
			path = MemberController.getMemberController().selectMemberDetail(request, response);
			MoveUrl.forward(request, response, path);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding(SiteConstance.ENCODE);
		doGet(request, response);
	}

}
