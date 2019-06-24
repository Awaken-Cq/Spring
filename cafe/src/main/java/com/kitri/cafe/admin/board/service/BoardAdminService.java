package com.kitri.cafe.admin.board.service;

import java.util.List;

import com.kitri.cafe.admin.board.model.BoardListDto;
import com.kitri.cafe.admin.board.model.BoardTypeDto;
import com.kitri.cafe.admin.board.model.CategoryDto;

public interface BoardAdminService {


	//게시판종류 getBoardMenuList로 처리가능.
	//List<BoardListDto> getBoardMenu();
	
	//카테고리 목록보기
	List<CategoryDto> getCategoryList();
	
	//카테고리 생성
	void makeCategory(CategoryDto categoryDto);
	
	
	
	//게시판 생성 및 변경을 위한 select
	//1) 카테고리 선택 2) 게시판 형식 선택
	//카테고리에 따라 게시판 목록이 다르게 나타나야하기 때문에 인자값으로 cate_no필요.
	//0일때는 모두가져오기
	List<BoardListDto> getBoardMenuList(int ccode);
	
	//게시판 형식목록보기
	List<BoardTypeDto> getBoardTypeList();
	
	//게시판 생성
	void makeBoard(BoardListDto boardListDto);
	
	
}
