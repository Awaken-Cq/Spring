package com.kitri.cafe.common.dao;

import java.util.Map;


public interface CommonDao {

	public int getNextSeq();
	//조회수 증가
	public void updateHit(int seq);
	//오늘쓴 글의 수
	public int getNewArticleCount(int bcode);
	//전체 글의 수
	public int getTotalArticleCount(Map<String, String> parameter);
	
	
}
