package com.kitri.hello4;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class HelloMain {

	public static void main(String[] agrs) {

		System.out.println("Start Program!!!");
		ApplicationContext context = 
				new AnnotationConfigApplicationContext(HelloServiceFactory.class);
		System.out.println("Read Systemfile!!!");
		HelloService helloService = context.getBean("hs", HelloService.class);
		System.out.println("Get Service!!!");
		
		String msg = helloService.hello("노정탁");
		
		System.out.println(msg);
		
	
	}
	
			
}
