/**   
* @Title: AppConsumer.java 
* @Package com.yrg.jms.consumer 
* @Description: TODO(用一句话描述该文件做什么) 
* @author yangrg  
* @date 2019年12月3日 下午2:19:20 
* @version V1.0   
*/
package com.yrg.jms.consumer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/** 
* @ClassName: AppConsumer 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author yangrg 
* @date 2019年12月3日 下午2:19:20 
*  
*/
public class AppConsumer {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:consumer.xml");
	}
}
