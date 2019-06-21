package com.kitri.member.model.service;

import java.util.List;
import java.util.Map;


import com.kitri.member.model.*;

public interface MemberService {
	
	String idCheck(String sid);
	String zipSearch(String doro);
	int registerMember(MemberDetailDto memberDetailDto);
	MemberDto loginMember(Map<String, String> map);
	
	MemberDetailDto getMember(String id);
	int modifyMember(MemberDetailDto dto);
	int deleteMember(String id);
	
}
