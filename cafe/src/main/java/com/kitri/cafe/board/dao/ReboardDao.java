package com.kitri.cafe.board.dao;

import java.util.List;
import java.util.Map;

import com.kitri.cafe.board.model.ReboardDto;

public interface ReboardDao {

	
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
	
	
	//답글을 위한 프로세스 update - insert - update
	//원글의 ref와 원글의 step필요 -> 각각의 step 1씩 증가.
	void updateStep(ReboardDto reboardDto);
	//답글 insert.
	int replyArticel(ReboardDto reboardDto);
	//원글의 reply 증가.
	void updateReply(int pseq);
	
}
