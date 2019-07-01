package com.kitri.cafe.board.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kitri.cafe.board.model.MemoDto;
import com.kitri.cafe.board.service.MemoService;
import com.kitri.cafe.member.model.MemberDto;

//restful : CRUD를 쿼리스트링 등을 사용하지 않고 url로 모두 처리가능.
//@RestController의 의미 : 이는 각각의 메소드 반환값에
//@Responsebody를 명시하지않아도 이 컨트롤러안에 모든 메소드는
//모든 반환을 @Responsebody의 형식으로 한다라고 명시.
@RestController
@RequestMapping("/memo")
public class MemoController {
	
	@Autowired
	private MemoService memoService;
	
	//restful 사용 할 것임.
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(int seq) {
		String json = memoService.listMemo(seq);
		System.out.println(json);
		return json;
	}
	
	
//	@RequestMapping(value="/{mseq}", method = RequestMethod.POST)
//	public String list(int seq) {
//		String json = memoService.listMemo(seq);
//		System.out.println(json);
//		return json;
//	}
	
	// /memo/392740239(글번호)
	//value="/{}"중괄호는 값이 변한다 즉, 변수이다를 명시.
	@RequestMapping(value="/{seq}/{mseq}", method = RequestMethod.DELETE)
	public String DELETE(@PathVariable(name="seq")int seq, @PathVariable(name="mseq")int mseq) {
		String json = memoService.deleteMemo(seq, mseq);
		//String json = memoService.listMemo(seq);
		System.out.println(json);
		return json;
	}
	
	//JSON으로 넘어온것을 인자값에 집어넣을때는 @Requestbody를 써주면되고,
	//JSON자체로 넘길때는 @ResponseBody를 써주면 된다.
	//headers 는 배열의 형태로 지정
	@RequestMapping(method = RequestMethod.POST)
	public String write(@RequestBody MemoDto memoDto, HttpSession session) {
		System.out.println(memoDto.getMcontent());
		MemberDto memberDto = (MemberDto) session.getAttribute("userInfo");
		String json = "";
		if(memberDto != null) {
			memoDto.setId(memberDto.getId());
			memoDto.setName(memberDto.getName());
			memoService.writeMemo(memoDto);
			json = memoService.listMemo(memoDto.getSeq());
		}
		System.out.println(memoDto.toString());
		System.out.println(json);
		return json;
	}
	
}
