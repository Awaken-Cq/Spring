package com.kitri.member.model.service;

import java.util.List;

import com.kitri.member.model.*;

public interface MemberService {

	String idCheck(String sid);
	String zipSearch(String doro);
	int registerMember(MemberDetailDto memberDetailDto);
	MemberDto loginMember(String id, String password);
	
	MemberDetailDto getMember(String id);
	int modifyMember(MemberDetailDto dto);
	int deleteMember(String id);
	
}
