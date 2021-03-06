package com.kitri.hello3;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class HelloMain {

	public static void main(String[] agrs) {
//		HelloServiceKor helloServiceKor = new HelloServiceKor();
//		String msg = helloServiceKor.hellokor("노정탁");
		
//		HelloServiceEng helServiceEng = new HelloServiceEng();
//		String msg = helServiceEng.helloEng("Tak");
	
//		HelloService helloService = new HelloServiceEng();
//		HelloService helloService = new HelloServiceKor();
		
//		Spring 2.x
		// .은 클래스경로 /는 폴더 경로
//		Resource resource = new ClassPathResource(
//				"com/kitri/hello3/applicationContext.xml");
//		
//		BeanFactory beanFactory = new XmlBeanFactory(resource);
//		HelloService helloService = 
//				(HelloService)beanFactory.getBean("hs");
		System.out.println("Start Program!!!");
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"com/kitri/hello3/applicationContext.xml");
		System.out.println("Read Systemfile!!!");
		//형변환 없이 가져오는법(먼저 바꿔서 불러온 것)
		HelloService helloService = context.getBean("hs", HelloService.class);
		System.out.println("Get Service!!!");
		
		String msg = helloService.hello("노정탁");
		
		System.out.println(msg);
			

		
		
		
	}
	
	
}
