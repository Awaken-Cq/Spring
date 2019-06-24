package com.kitri.cafe.board.service;

import java.util.List;
import java.util.Map;

import com.kitri.cafe.board.model.AlbumDto;

public interface AlbumService {

	
	//글목록(게시판번호, 페이지번호, 검색조건, 검색어)
	List<AlbumDto>listArticle(Map<String, String> parameter);
	
	//글보기
	AlbumDto viewArticle(int seq);

	//글쓰기(글 작성 후 글보기로 넘어가기 위해 return int)
	int writeArticle(AlbumDto albumDto);
	
	//글수정
	int modifyArticle(AlbumDto albumDto);
	
	//글삭제
	void deleteArticle(int seq);
	
	
}
