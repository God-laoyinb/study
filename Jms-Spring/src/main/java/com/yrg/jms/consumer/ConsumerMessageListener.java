/**   
* @Title: ConsumerMessageListener.java 
* @Package com.yrg.jms.consumer 
* @Description: TODO(用一句话描述该文件做什么) 
* @author yangrg  
* @date 2019年12月3日 下午2:59:22 
* @version V1.0   
*/
package com.yrg.jms.consumer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/** 
* @ClassName: ConsumerMessageListener 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author yangrg 
* @date 2019年12月3日 下午2:59:22 
*  
*/
public class ConsumerMessageListener implements MessageListener{

	//监听到消息要做的事情
	public void onMessage(Message message) {
		TextMessage textMessage = (TextMessage) message;
		try {
			System.out.println("接受消息："+textMessage.getText());
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
