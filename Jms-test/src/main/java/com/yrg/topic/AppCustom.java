/**   
* @Title: AppCustom.java 
* @Package com.yrg.queue 
* @Description: TODO(用一句话描述该文件做什么) 
* @author yangrg  
* @date 2019年12月2日 下午2:41:16 
* @version V1.0   
*/
package com.yrg.topic;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/** 
* @ClassName: AppCustom 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author yangrg 
* @date 2019年12月2日 下午2:41:16 
*  
*/
public class AppCustom {
	
	private static final String url="tcp://47.100.137.33:61616";
	private static final String topicName="topic-test";
	
	public static void main(String[] args) throws JMSException {
		
		//1.创建连接工厂ConnectionFactory
		ConnectionFactory connectionFactory  = new ActiveMQConnectionFactory(url);
		
		//2.创建Connection
		Connection connection = connectionFactory.createConnection();
		
		//3.启动连接
		connection.start();
		
		//4.创建会话 
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);//第一个参数：是否启动事务；第二个参数：应答模式（设置了自动应答）
		
		//5.创建一个目标
		Destination destination = session.createTopic(topicName);
		
		//6.创建一个消费者
		MessageConsumer consumer= session.createConsumer(destination);
		
		//7.创建一个监听器
		consumer.setMessageListener(new MessageListener() {
			
			public void onMessage(Message message) {
				TextMessage textMessage = (TextMessage) message;
				try {
					System.out.println("接收消息："+textMessage.getText());
				} catch (JMSException e) {
					System.out.println("接受失败");
					e.printStackTrace();
				}
			}
		});
		
		//8.关闭连接
		//connection.close();//关闭连接会导致监听器退出，接受不到消息
	}
}
