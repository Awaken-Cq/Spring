package com.kitri.cafe.board.dao;

import java.util.List;
import java.util.Map;

import com.kitri.cafe.board.model.BbsDto;

public interface BbsDao {

	//글목록(게시판번호, 페이지번호, 검색조건, 검색어)
	List<BbsDto>listArticle(Map<String, String> parameter);
	
	//글보기
	BbsDto viewArticle(int seq);

	//글쓰기(글 작성 후 글보기로 넘어가기 위해 return int)
	int writeArticle(BbsDto bbsDto);
	
	//글수정
	int modifyArticle(BbsDto bbsDto);
	
	//글삭제
	void deleteArticle(int seq);
}
