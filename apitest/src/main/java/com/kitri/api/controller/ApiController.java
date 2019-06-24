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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api")
@ResponseBody
public class ApiController {

		private static final Logger logger = LoggerFactory.getLogger(ApiController.class);
	
		@RequestMapping(value="/enter.api", method =RequestMethod.GET)
		public String enter(@RequestParam(name = "key")String msg) {
			System.out.println("entered");
			logger.info(msg);
			String apikey = "qldeV%2BL5Ff%2BFi%2BJisZxRFyc1KDitxcPmNkhuwOjk6c7xQDVITEe0oDrh3XFd98iqnW89ky8RMDhQkQIb48h3%2BQ%3D%3D";
			String tourapikey = "";
			String result = apiexc(apikey);
			
			
			return result;
			
			
		}
	
		
		public String apiexc(String apikey) {
		String result = "";
		BufferedReader br = null;
		
		String urlStr = "http://www.culture.go.kr/openapi/rest/"
				+ "publicperformancedisplays/"
				+ "period?from=20190501&to=20190801&cPage=1&"
				+ "rows=10&place=&gpsxfrom=&gpsyfrom=&gpsxto=&"
				+ "gpsyto=&keyword=&sortStdr=1&serviceKey="+ apikey;
		URL url;
		try {
			url = new URL(urlStr);
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setRequestMethod("GET");
			br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
			
			String line;
			while((line = br.readLine()) != null) {
				result += result + line + "/n";
			}
			logger.info(result);
			
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
