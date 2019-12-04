/**   
* @Title: AppProducer.java 
* @Package com.yrg.jms.producer 
* @Description: TODO(用一句话描述该文件做什么) 
* @author yangrg  
* @date 2019年12月3日 上午11:23:13 
* @version V1.0   
*/
package com.yrg.jms.producer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yrg.jms.producer.ipml.ProducerServerImpl;

/** 
* @ClassName: AppProducer 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author yangrg 
* @date 2019年12月3日 上午11:23:13 
*  
*/
public class AppProducer {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:producer.xml");
		
		IProducerServer producerServer = context.getBean(ProducerServerImpl.class);
		for (int i = 0; i < 50; i++) {
			producerServer.sendMessage("test"+i);
		}
		context.close();	
	}
}
