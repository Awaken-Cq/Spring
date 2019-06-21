package com.kitri.member.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.kitri.member.model.MemberDetailDto;
import com.kitri.member.model.MemberDto;
import com.kitri.member.model.service.MemberService;



@Controller
@RequestMapping("/user")
@SessionAttributes("userInfo")
//@SessionAttributes(names = {"userInfo", "b", "c"})
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
//	@Qualifier("Impl1") // 같은 memberService가 있을 경우 에러가 날 수 있기때문에 qualifier지정
	private MemberService memberService;
	
	
	
	//value는 배열이므로 여러개의 주소에서 이 메소드로 올 수 있다. ex) value="{/register.kitri, /mvjoin.kitri}"
	@RequestMapping(value = "/register.kitri", method = RequestMethod.GET)
	public String register() {
		return "user/member/member";
	}
	
	@RequestMapping(value = "/register.kitri", method = RequestMethod.POST)
	public String register(MemberDetailDto memberDetailDto, Model model) {
		logger.info(memberDetailDto.toString());
		int cnt = memberService.registerMember(memberDetailDto);
		System.out.println(cnt);
		if(cnt != 0) {
			model.addAttribute("registerInfo", memberDetailDto);
			return "user/member/registerok";
		}else {
			return "user/member/registerfail";
		}
	}
	
	@RequestMapping(value = "/idcheck.kitri", method = RequestMethod.GET)
	public @ResponseBody String idCheck(@RequestParam(name ="checkid", defaultValue = "")String checkid) {
		logger.info("checkid : " + checkid);
		String json = memberService.idCheck(checkid);
		return json;
	}

	@RequestMapping(value= "/login.kitri", method= RequestMethod.GET)
	public String login() {
		
		return "user/login/login";
	}
	
//	@RequestMapping(value= "/login.kitri", method= RequestMethod.POST)
//	public String login(@RequestParam("id") String id, @RequestParam("pass") String pass, HttpSession session) {
//		MemberDto memberDto = memberService.loginMember(id, pass);
//		if(memberDto != null){
//			session.setAttribute("userInfo", memberDto);
//			return "user/login/loginok";
//		}else {
//			return "user/login/loginfail";
//		}
//	}
	//spring 방식으로 자료를 관리하는법 - map을 사용(Dto가 없다는 가정하에)
	@RequestMapping(value= "/login.kitri", method= RequestMethod.POST)
	public String login(@RequestParam Map<String, String> map, Model model) {
		MemberDto memberDto = memberService.loginMember(map);
		if(memberDto != null){
			model.addAttribute("userInfo", memberDto);
			return "user/login/loginok";
		}else {
			return "user/login/loginfail";
		}
	}
	
	@RequestMapping("/zipsearch.kitri")
	@ResponseBody
	public String zipSearch(@RequestParam("doro")String doro) {
		System.out.println(doro);
		logger.info("검색도로명 : " + doro);
		String json = memberService.zipSearch(doro);
	
		return json;
	}

//	@RequestMapping("/logout.kitri")
//	public String logout(HttpSession session) {
//		MemberDto dto = (MemberDto)session.getAttribute("userInfo");
//		System.out.println(dto);
//		session.removeAttribute("userInfo");
//		dto = (MemberDto)session.getAttribute("userInfo");
//		System.out.println(dto);
//		return "redirect:/index.jsp";	//	redirect: 란 prefix를 지정.
//	}
	
	@RequestMapping("/logout.kitri")
	public String logout(@ModelAttribute("userInfo") MemberDto memberDto, SessionStatus sessionStatus) {
		System.out.println("왔다갑니다.");
		sessionStatus.setComplete();	//	@SessionAttributes로 
		//session을 지정했기 때문에 session.removeA~를 해도 내용은 지워지지만
		//껍데기?는 남아있다. 고로 session 객체자체를 제거하는 setComplete를 사용해준다.
		return "redirect:/index.jsp";	//	redirect: 란 prefix를 지정.
	}

	
}
