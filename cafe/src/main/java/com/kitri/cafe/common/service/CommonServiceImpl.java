package com.kitri.cafe.common.service;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kitri.cafe.common.dao.CommonDao;
import com.kitri.util.CafeConstance;
import com.kitri.util.NumberCheck;
import com.kitri.util.PageNavigation;

@Service
public class CommonServiceImpl implements CommonService {


	@Autowired
	private SqlSession sqlSession;
	
	
	@Override
	public int getNextSeq() {
		return sqlSession.getMapper(CommonDao.class).getNextSeq();
	}


	@Override
	public PageNavigation getPageNavigation(Map<String, String> parameter) {
		PageNavigation navigation = new PageNavigation();
		int newArticleCount = sqlSession.getMapper(CommonDao.class).
							getNewArticleCount(Integer.parseInt(parameter.get("bcode")));
		int totalArticleCount = sqlSession.getMapper(CommonDao.class).
							getTotalArticleCount(parameter);
		
		//산술시간
		//TA	tp
		//237	12
		//361	19
		//360	18
		//(TA-1) / 20 +1 
		int totalPageCount = (totalArticleCount-1) / CafeConstance.ARTICLE_SIZE +1 ;
		int pg = NumberCheck.NotNumberToOne(parameter.get("pg"));
		
		
		navigation.setNewArticleCount(newArticleCount);
		navigation.setTotalArticleCount(totalArticleCount);
		navigation.setTotalPageCount(totalPageCount);
		navigation.setNowEnd((totalPageCount-1)/CafeConstance.PAGE_SIZE * 
													CafeConstance.PAGE_SIZE <pg);
		navigation.setNowFirst(pg <= CafeConstance.PAGE_SIZE);
		navigation.setPageNo(pg);
		
		return navigation;
	}


//	@Override
//	public void updateHit(int seq) {
//		// TODO Auto-generated method stub
//
//	}

}
