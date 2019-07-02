package com.kitri.cafe.board.controller;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kitri.cafe.board.model.AlbumDto;
import com.kitri.cafe.board.model.ReboardDto;
import com.kitri.cafe.board.service.AlbumService;
import com.kitri.cafe.common.service.CommonService;
import com.kitri.cafe.member.model.MemberDto;

@Controller
@RequestMapping("/album")
public class AlbumController {
	
	@Autowired
	private ServletContext servletContext;

	private static final Logger logger = LoggerFactory.getLogger(ReboardController.class);
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private AlbumService albumService;
	
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public void write(@RequestParam Map<String, String> parameter, Model model) {
		model.addAttribute("parameter",parameter);
	}
	
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String write(AlbumDto albumDto, @RequestParam Map<String, String> parameter, 
					Model model, HttpSession session, @RequestParam("picture") MultipartFile multipartFile) {
		logger.info("writepost :" + parameter);
		MemberDto memberDto = (MemberDto)session.getAttribute("userInfo");
		
		if(memberDto != null) {
		int seq = commonService.getNextSeq();
		albumDto.setSeq(seq);;
		albumDto.setId(memberDto.getId());
		albumDto.setName(memberDto.getName());
		albumDto.setEmail(memberDto.getEmail());
		
		
		//파일의 정보를 Dto에 집어넣는 프로세스
		if(multipartFile != null && !multipartFile.isEmpty()) {
			//파일을 올렸다면
			String originalPicture = multipartFile.getOriginalFilename();
			String realPath = servletContext.getRealPath("/upload/album");
			DateFormat df = new SimpleDateFormat("yyMMdd");
			String saveFolder = df.format(new Date());
			//File.separator : 윈도우냐 리눅스냐에서 따라서 구분자가 자동으로 잡힘.
			String realSaveFolder = realPath + File.separator +saveFolder;
			System.out.println(realSaveFolder);
			File dir = new File(realSaveFolder);
			if(!dir.exists()) {
				dir.mkdirs();
			}
			
			//똑같지 않은 이름 생성(중복금지)
			String savePicture = UUID.randomUUID().toString() + 
					originalPicture.substring(originalPicture.lastIndexOf('.'));
			File file = new File(realSaveFolder,savePicture);
			
			try {
				multipartFile.transferTo(file);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			albumDto.setOriginalPicture(originalPicture);
			albumDto.setSavePicture(savePicture);
			albumDto.setSaveFolder(saveFolder);
		}
//		TODO : 1. image file 검사(jpg,jpeg,png,gif), 2. thumbnail(용량을 줄이기 위해서)image
		seq = albumService.writeArticle(albumDto);
		
			if(seq != 0) {
				model.addAttribute("seq",seq);
			}else{
				model.addAttribute("errorMsg","서버문제로 글작성 실패!!!");
			}
		}else{
			model.addAttribute("errorMsg","로그인 후 글작성하시오!!!");
		}
		model.addAttribute("parameter", parameter);
		return "album/writeok";
	}
	
}
