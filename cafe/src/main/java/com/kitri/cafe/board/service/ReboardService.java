package com.kitri.cafe.board.service;

import java.util.List;
import java.util.Map;

import com.kitri.cafe.board.model.BoardDto;
import com.kitri.cafe.board.model.ReboardDto;

public interface ReboardService {

	//글목록(게시판번호, 페이지번호, 검색조건, 검색어)
	List<ReboardDto>listArticle(Map<String, String> parameter);
	
	//글보기
	ReboardDto viewArticle(int seq);
	

	//글쓰기(글 작성 후 글보기로 넘어가기 위해 return int)
	int writeArticle(ReboardDto reboardDto);
	
	//글수정
	int modifyArticle(ReboardDto reboardDto);
	
	//글삭제
	void deleteArticle(int seq);
	
	
	
	//답글 프로세스
	//답글(1)을위한 원글의 정보를 불러오는 메소드
	ReboardDto getArticle(int seq);
		
	int replyArticle(ReboardDto reboardDto);
	
}
