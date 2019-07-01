package com.kitri.cafe.board.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.kitri.cafe.board.dao.MemoDao;
import com.kitri.cafe.board.model.MemoDto;

@Service
public class MemoServiceImpl implements MemoService {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public String listMemo(int seq) {
		return makeJson(seq); 
		 
	}

	private String makeJson(int seq) {
		List<MemoDto> list = sqlSession.getMapper(MemoDao.class).listMemo(seq);
		JSONArray array = new JSONArray(list);
		JSONObject json = new JSONObject();
		json.put("memolist", array);
		
		return json.toString();
	}

	@Override
	public void writeMemo(MemoDto memoDto) {
		sqlSession.getMapper(MemoDao.class).writeMemo(memoDto);
	}

	@Override
	public String modifyMemo(MemoDto memoDto) {
		
		return "";
	}

	@Override
	public String deleteMemo(int seq, int mseq) {
		sqlSession.getMapper(MemoDao.class).deleteMemo(mseq);
		return makeJson(seq);
		
	}


}
