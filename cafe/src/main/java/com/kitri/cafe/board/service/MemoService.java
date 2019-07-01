package com.kitri.cafe.board.service;

import java.util.List;
import java.util.Map;

import com.kitri.cafe.board.model.MemoDto;

public interface MemoService {

	//댓글목록()
	String listMemo(int seq);
	
	//글쓰기(글 작성 후 글보기로 넘어가기 위해 return int)
	void writeMemo(MemoDto memoDto);
	
	//글수정
	String modifyMemo(MemoDto memoDto);
	
	//글삭제
	String deleteMemo(int seq, int mseq);
	
	
}
