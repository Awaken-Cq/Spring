package com.kitri.hello4;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
//@Configuration : 나는 이 자바 파일을 설정파일로 쓰겠다 라는 의미.
@Configuration
public class HelloServiceFactory {

	@Bean(name = {"hs","helloservice"})
	@Scope(value = "prototype")
	public HelloService getHelloService() {
		
		return new HelloServiceKor();
	}
	
	
}
