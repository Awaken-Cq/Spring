package com.kitri.member.model.service;

import java.util.*;

import com.kitri.member.model.*;
import com.kitri.member.model.dao.MemberDaoImpl;

public class MemberServiceImpl implements MemberService {

	

	private static MemberService memberService;
	
	static {
		memberService = new MemberServiceImpl();
	}
	
	private MemberServiceImpl() {}
	
	public static MemberService getMemberService() {
		return memberService;
	}
	
	@Override
	public String idCheck(String id) {
		int cnt = MemberDaoImpl.getMemberDao().idCheck(id);
//		System.out.println("cnt = " + cnt);
//		xml로 넘기는 방법
		String result = "<idcount> \n";
		result += "<cnt>" + cnt + "</cnt> \n";
		result += "<checkedid>" + id + "</checkedid>";
		result += "</idcount>";
//		result += "";
//		result += "";
		
		return result;
	}

	@Override
	public String zipSearch(String doro) {
		String result = "";
		List<ZipcodeDto> list = MemberDaoImpl.getMemberDao().zipSearch(doro);
		result += "<ziplist>\n";
		for(ZipcodeDto zipDto : list) {
		result += " <zip> \n";
		result += "  <zipcode>"+ zipDto.getZipcode() +"</zipcode> \n";
		result += "  <address><![CDATA["+ zipDto.getSido() + " " + zipDto.getGugun() + " "
				+ zipDto.getUpmyon() + " " + zipDto.getDoro() + " " 
				+ zipDto.getBuildingNumber() + " " + zipDto.getSigugunBuildingName() + "]]></address> \n";
		result += " </zip> \n";
		}
		result += "</ziplist> ";
		return result;
	}

	@Override
	public int registerMember(MemberDetailDto memberDetailDto) {
		int cnt = 0;
		cnt = MemberDaoImpl.getMemberDao().registerMember(memberDetailDto);
		return cnt;
	}

	@Override
	public MemberDto loginMember(String id, String pass) {
		Map<String, String> loginInfo = new HashMap<String, String>();
		loginInfo.put("userid",  id);
		loginInfo.put("userpwd", pass);
		
		
		return MemberDaoImpl.getMemberDao().loginMember(loginInfo);
	}

	@Override
	public MemberDetailDto getMember(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int modifyMember(MemberDetailDto dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteMember(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
