package com.kitri.cafe.admin.board.controller;

import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kitri.cafe.admin.board.model.BoardListDto;
import com.kitri.cafe.admin.board.service.BoardAdminService;

@Controller
@RequestMapping("/boardadmin")
public class BoardAdminController {

//	private static Logger logger = 

	@Autowired
	private BoardAdminService boardAdminService;
	
	@RequestMapping("/boardmenu")
	public ModelAndView boardMenu(@RequestParam(name = "ccode", defaultValue = "0") int ccode) {
		System.out.println("게시판목록cont");
		List<BoardListDto> list = boardAdminService.getBoardMenuList(ccode);
		System.out.println(list.size());
		ModelAndView mav = new ModelAndView();
		mav.addObject("boardmenu", list);
		mav.setViewName("admin/boardmenu");

		return mav;
	}
}
