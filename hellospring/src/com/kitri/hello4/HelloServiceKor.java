package com.kitri.hello4;

public class HelloServiceKor implements HelloService {
	
	public HelloServiceKor() {
		System.out.println("Call HelloServiceKor Constructor");
	}


	@Override
	public String hello(String name) {
		
		return name  + "님 안녕하세요:)";
	}
	
	
}