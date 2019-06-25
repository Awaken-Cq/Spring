package com.kitri.api.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api")
public class ApiController {
	
	//produces="application/json;charset=UTF-8", 
		private static final Logger logger = LoggerFactory.getLogger(ApiController.class);
	
		@RequestMapping(value="/enter.api", method =RequestMethod.GET)
		@ResponseBody
		public String enter(@RequestParam(name = "key") String msg) {
			System.out.println("entered");
			logger.info(msg);
			String takapikey = "qldeV%2BL5Ff%2BFi%2BJisZxRFyc1KDitxcPmNkhuwOjk6c7xQDVITEe0oDrh3XFd98iqnW89ky8RMDhQkQIb48h3%2BQ%3D%3D";
			String sehyunapikey = "eVXAsaFVfjgGhAQfsYx28kYnz0nbDxSuCQlP9TnGl8ntcFd3V%2Byjhkh%2BUfuNwRDIZYBgDnL3Cm0BzM8Ezw73mQ%3D%3D";
			
			String periodurlStr = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?"
					+ "numOfRows=10&"
					+ "pageNo=1&"
					+ "MobileOS=ETC&"
					+ "MobileApp=AppTest&"
					+ "listYN=Y&"
					+ "arrange=A&"
					+ "contentTypeId=15&"
					+ "cat1=A02&"
					+ "cat2=A0207&"
					+ "cat3=A02070100&"
				/* + "keyword=%EA%B0%95%EC%9B%90" */
					+ "_type=json&"
					+ "ServiceKey="+takapikey;
			
			String categoryurlStr = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/categoryCode?"
					
							+ "MobileOS=ETC&"
							+ "MobileApp=AppTest&"
							+ "contentTypeId=15&"
							+ "cat1=A02&"
							+ "cat2=A0208&"
							+ "cat3=A02080300&"
							+ "_type=json&"
							+ "ServiceKey=" + takapikey;
			
			logger.info("----------------------------------");
			String result = apiexc(periodurlStr);
			//model.addAttribute("json", result);
			logger.info(result);
			logger.info("----------------------------------");
			
			return result;
			
			
		}
		
		
		public String apiexc(String urlStr) {
			String result = "";
			BufferedReader br = null;

			URL url;
			try {
				url = new URL(urlStr);
				HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
				urlConnection.setRequestMethod("GET");
				br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
				
				String line;
				while((line = br.readLine()) != null) {
					result += result + line + "\n";
				}
				
				
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
			
		}
		
		
}
