package com.kitri.cafe.common.service;

import java.util.Map;

import com.kitri.util.PageNavigation;

public interface CommonService {

	//
	int getNextSeq();
	
	//viewCnt
//	void updateHit(int seq);
	
	//pageNavigation
	PageNavigation getPageNavigation(Map<String, String> parameter);
	
	
}
