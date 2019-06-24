package com.kitri.cafe.board.dao;

import java.util.List;
import java.util.Map;

import com.kitri.cafe.board.model.MemoDto;

public interface MemoDao {

	//댓글목록()
	List<MemoDto> listMemo(Map<String, String> parameter);
	
	//글쓰기(글 작성 후 글보기로 넘어가기 위해 return int)
	void writeMemo(MemoDto memoDto);
	
	//글수정
	void modifyMemo(MemoDto momeDto);
	
	//글삭제
	void deleteMemo(int mseq);
	
}
