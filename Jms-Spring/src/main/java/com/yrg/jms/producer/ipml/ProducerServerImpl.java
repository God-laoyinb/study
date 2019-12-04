/**   
* @Title: ProducerServerImpl.java 
* @Package com.yrg.jms.producer.ipml 
* @Description: TODO(用一句话描述该文件做什么) 
* @author yangrg  
* @date 2019年12月3日 上午11:09:06 
* @version V1.0   
*/
package com.yrg.jms.producer.ipml;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.yrg.jms.producer.IProducerServer;

/** 
* @ClassName: ProducerServerImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author yangrg 
* @date 2019年12月3日 上午11:09:06 
*  
*/
public class ProducerServerImpl implements IProducerServer {

	@Autowired
	JmsTemplate jmsTemplate;
	
	//@Resource(name="queueDestination")
	@Resource(name="topicDestination")
	Destination destination ;
	
	
	public void sendMessage(final String message) {
		//使用jmsTemplate发送消息
		jmsTemplate.send(destination, new MessageCreator() {
			//创建一个消息
			public Message createMessage(Session session) throws JMSException {
				TextMessage textMessage = session.createTextMessage(message);
				return textMessage;
			}
		});
		System.out.println("发送消息："+message);
			
	}

}
