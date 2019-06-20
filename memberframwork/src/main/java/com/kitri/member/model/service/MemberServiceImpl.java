package com.kitri.member.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kitri.member.model.MemberDetailDto;
import com.kitri.member.model.MemberDto;
import com.kitri.member.model.ZipcodeDto;
import com.kitri.member.model.dao.MemberDao;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDao memberDao;
	
	@Override
	public String idCheck(String id) {
		int cnt = memberDao.idCheck(id);
//		System.out.println("cnt = " + cnt);
//		json으로 넘기는 방법
//		json의 형태(내부적으로 맵의 형태)
		//	{json[jsonArray{json},{json}...]}
//		{"":	
//		[	//	jsonArray
//		     {"":"","":""},	//json
//		     {"":"","":""},
//		     {"":"","":""}]
//		}
		JSONObject json = new JSONObject();
		json.put("idcount", cnt);		
		return json.toString();	//	 {"idcount":0}

	}

	@Override
	public String zipSearch(String doro) {
		List<ZipcodeDto> list = memberDao.zipSearch(doro);
		JSONObject json = new JSONObject();
		JSONArray jarr = new JSONArray(list);
		
//		for(ZipcodeDto zipcodeDto : list) {
//			JSONObject zipcode = new JSONObject();
//			zipcode.put("zipcode", zipcodeDto.getZipcode());
//			zipcode.put("address", 
//					zipcodeDto.getSido() +" "+
//					zipcodeDto.getGugun() + " " +
//					zipcodeDto.getUpmyon() + " " +
//					zipcodeDto.getDoro() + " " + 
//					zipcodeDto.getBuildingNumber() + " " +
//					zipcodeDto.getSigugunBuildingName());
//			jarr.put(zipcode);
//		}
		
		json.put("ziplist", jarr);		
		return json.toString();
	}

	@Override
	public int registerMember(MemberDetailDto memberDetailDto) {
		int cnt = 0;
		cnt = memberDao.registerMember(memberDetailDto);
		return cnt;
	}

	@Override
	public MemberDto loginMember(String id, String pass) {
		Map<String, String> loginInfo = new HashMap<String, String>();
		loginInfo.put("userid",  id);
		loginInfo.put("userpwd", pass);
		
		
		return memberDao.loginMember(loginInfo);
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
