package com.kitri.admin.controller;


import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kitri.admin.model.service.AdminService;
import com.kitri.member.model.MemberDetailDto;

@Controller
@RequestMapping("/admin")
public class Admincontroller {
	
	private static final Logger logger = LoggerFactory.getLogger(Admincontroller.class);
	
	@Autowired
	private AdminService adminService;
	
	@RequestMapping(value = "/mvmemberlist.kitri", method=RequestMethod.GET)
	public String memberlist() {
		System.out.println("들어와따");
		return "admin/member/memberlist";
	}
	
	@RequestMapping(value = "/memberlist.kitri", method=RequestMethod.GET)
	@ResponseBody
	public String memberlist(@RequestParam Map<String, String> map) {
		System.out.println(map.get("key"));
		System.out.println(map.get("word"));
		
		String memberlist = adminService.getMemberList(map);
		
		
		return memberlist;
	}
	
}
