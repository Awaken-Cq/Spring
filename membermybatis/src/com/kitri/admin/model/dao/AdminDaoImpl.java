package com.kitri.admin.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.kitri.member.model.MemberDetailDto;
import com.kitri.sqlmap.MyBatisConfiguration;

public class AdminDaoImpl implements AdminDao  {

private static AdminDao adminDao;
	
	private final String NAME_SPACE = "com.kitri.admin.model.dao.AdminDao";

	static {
		adminDao = new AdminDaoImpl();
		
	}

	private AdminDaoImpl() {}
	
	public static AdminDao getAdminDao() {
		return adminDao;
	}
	
	public List<MemberDetailDto> getMemberList(Map<String,  String> map){
		SqlSession session = MyBatisConfiguration.getSqlSessionFactory().openSession();
		try {
			return session.selectList(NAME_SPACE+".getMemberList", map);
		} finally {
			session.close();
		}
	}
	
}
