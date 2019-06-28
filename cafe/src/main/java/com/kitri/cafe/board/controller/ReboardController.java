package com.kitri.cafe.board.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kitri.cafe.board.model.ReboardDto;
import com.kitri.cafe.board.service.ReboardService;
import com.kitri.cafe.common.service.CommonService;
import com.kitri.cafe.member.model.MemberDto;
import com.kitri.util.PageNavigation;

@Controller
@RequestMapping("/reboard")
public class ReboardController {

	private static final Logger logger = LoggerFactory.getLogger(ReboardController.class);
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private ReboardService reboardService;
	
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public void write(@RequestParam Map<String, String> parameter, Model model) {
		model.addAttribute("parameter",parameter);
	}
	
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String write(ReboardDto reboardDto, @RequestParam Map<String, String> parameter, Model model, HttpSession session) {
		logger.info("writepost :" + parameter);
		String path="";
		MemberDto memberDto = (MemberDto)session.getAttribute("userInfo");
		
		if(memberDto != null) {
		int seq = commonService.getNextSeq();
		reboardDto.setSeq(seq);;
		reboardDto.setId(memberDto.getId());
		reboardDto.setName(memberDto.getName());
		reboardDto.setEmail(memberDto.getEmail());
		reboardDto.setRef(seq);
		logger.info(seq+"");
		
		seq = reboardService.writeArticle(reboardDto);
		
			if(seq != 0) {
				model.addAttribute("seq",seq);
				path = "reboard/writeok";
			}else{
				path = "reboard/writefail";
			}
		}else{
			path = "reboard/writefail";
		}
		model.addAttribute("parameter", parameter);
		return path;
	}
	
	@RequestMapping(value="/view", method=RequestMethod.GET)
	public String view(@RequestParam("seq") int seq,
			@RequestParam Map<String, String> parameter, Model model, HttpSession session) {
		logger.info("viewget :" + parameter);
		String path = "";
		
		//로그인 검사
		MemberDto memberDto = (MemberDto) session.getAttribute("userInfo");
		if(memberDto != null) {
			//조횟수 선 증가
			//commonService.updateHit(seq);
			//조횟수 증가시킨 reboardDto select
			ReboardDto reboardDto = reboardService.viewArticle(seq);
			
		//dto null검사
			
			
			model.addAttribute("article", reboardDto);
			model.addAttribute("parameter",parameter);
			path = "reboard/view";
		}else {
			path = "redirect:/index.jsp";
		}
		
		return path;
		
	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public void list(@RequestParam Map<String, String> parameter, Model model, HttpServletRequest request) {
		logger.info("listget :" + parameter);

		List<ReboardDto> list = reboardService.listArticle(parameter);
		PageNavigation pageNavigation = commonService.getPageNavigation(parameter);
		pageNavigation.setRoot(request.getContextPath());
		pageNavigation.makeNavigator();
		
		model.addAttribute("parameter",parameter);
		model.addAttribute("articleList",list);
		model.addAttribute("navigator", pageNavigation);
		
	}
	
	@RequestMapping(value="/reply", method=RequestMethod.GET)
	public String reply(@RequestParam("seq") int seq, HttpSession session,
						@RequestParam Map<String, String> parameter, Model model) {
		logger.info("replyget :" + parameter);
		String path = "";
		
		//로그인 검사
		MemberDto memberDto = (MemberDto) session.getAttribute("userInfo");
		if(memberDto != null) {
			//조횟수 선 증가
			//commonService.updateHit(seq);
			//조횟수 증가시킨 reboardDto select
			ReboardDto reboardDto = reboardService.getArticle(seq);
			//dto null검사
			//생략
			model.addAttribute("article", reboardDto);
			model.addAttribute("parameter",parameter);
			logger.info("replyget :" + parameter);
			logger.info("replyget :" + reboardDto.getBcode() + reboardDto.toString() + reboardDto.getRef());
			path = "reboard/reply";
		}else {
			path = "redirect:/index.jsp";
		}
		return path;
	}
	
	@RequestMapping(value="/reply", method=RequestMethod.POST)
	public String reply(ReboardDto reboardDto, @RequestParam Map<String, String> parameter, Model model, HttpSession session) {
		logger.info("replypost :" + parameter);
		logger.info("replypost :" + reboardDto);
		String path="";
		MemberDto memberDto = (MemberDto)session.getAttribute("userInfo");
		
		if(memberDto != null) {
		int seq = commonService.getNextSeq();
		reboardDto.setSeq(seq);;
		reboardDto.setId(memberDto.getId());
		reboardDto.setName(memberDto.getName());
		reboardDto.setEmail(memberDto.getEmail());
		
		seq = reboardService.replyArticle(reboardDto);
		
			if(seq != 0) {
				model.addAttribute("seq",seq);
				model.addAttribute("parameter", parameter);
				path = "reboard/writeok";
			}else{
				path = "reboard/writefail";
			}
		}else{
			path = "reboard/writefail";
		}
		System.out.println("reply post");
		return path;
	}
}
