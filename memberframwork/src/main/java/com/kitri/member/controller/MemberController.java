package com.kitri.member.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kitri.member.model.MemberDetailDto;
import com.kitri.member.model.service.MemberService;



@Controller
@RequestMapping("/user")
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
			model.addAttribute("userInfo", memberDetailDto);
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

	@RequestMapping("/login.kitri")
	public String login() {
		
		return "user/login/login";
	}
	
	@RequestMapping("/zipsearch.kitri")
	@ResponseBody
	public String zipSearch(@RequestParam("doro")String doro) {
		System.out.println(doro);
		logger.info("검색도로명 : " + doro);
		String json = memberService.zipSearch(doro);
		
		return json;
	}


	
}
