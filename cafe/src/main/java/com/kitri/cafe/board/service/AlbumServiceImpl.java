package com.kitri.cafe.board.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kitri.cafe.board.dao.AlbumDao;
import com.kitri.cafe.board.dao.ReboardDao;
import com.kitri.cafe.board.model.AlbumDto;
import com.kitri.cafe.board.model.ReboardDto;
import com.kitri.cafe.common.dao.CommonDao;
import com.kitri.util.CafeConstance;
import com.kitri.util.NumberCheck;

@Service
public class AlbumServiceImpl implements AlbumService {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<AlbumDto> listArticle(Map<String, String> parameter) {
		int pg = NumberCheck.NotNumberToOne(parameter.get("pg"));
		int end = pg * CafeConstance.ALBUMART_SIZE;
		int start = end - CafeConstance.ALBUMART_SIZE;
		parameter.put("start",start+"");
		parameter.put("end",end+"");
		return sqlSession.getMapper(AlbumDao.class).listArticle(parameter);
		
	}

	@Override
	public AlbumDto viewArticle(int seq) {
		sqlSession.getMapper(CommonDao.class).updateHit(seq);
		AlbumDto albumDto = sqlSession.getMapper(AlbumDao.class).viewArticle(seq);
		albumDto.setContent(albumDto.getContent().replace("\n", "<br>"));
		return albumDto;
		
	}

	@Override
	public int writeArticle(AlbumDto albumDto) {
		return sqlSession.getMapper(AlbumDao.class).writeArticle(albumDto);
	}

	@Override
	public int modifyArticle(AlbumDto albumDto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteArticle(int seq) {
		// TODO Auto-generated method stub

	}

}
