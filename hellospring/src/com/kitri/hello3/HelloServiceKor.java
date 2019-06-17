package com.kitri.hello3;

public class HelloServiceKor implements HelloService {
	
	
	
	//Spring에서 객체를 생성하는 2가지 방법
	//기존 : 1)생성자에 new를 하거나 2)싱글톤 패턴을 만들어서 호출
	
	private HelloDao helloDao;
	
	public HelloServiceKor(HelloDao helloDao) {
		this.helloDao = helloDao;
	}
	
//	public void setHelloDao(HelloDao helloDao) {
//		this.helloDao = helloDao;
//	}


//	public HelloServiceKor() {
//		System.out.println("Call HelloServiceKor Constructor");
//	}


	@Override
	public String hello(String name) {
		//helloDao는 helloservice에 의존한다. 
		//이를 spring이 내부적으로 처리해주는데 이를 DI라고한다.
		return name  + "님 안녕하세요:)\n" + helloDao.getGreeting();
	}
	
	
}
