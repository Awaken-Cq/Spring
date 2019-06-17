package com.kitri.hello2;

public class HelloServiceEng implements HelloService {



	@Override
	public String hello(String name) {
		
		return "Hello, " +  name + " \r\nWelcome:)";
	}
	
}