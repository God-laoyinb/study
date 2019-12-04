/**   
* @Title: AppProducer.java 
* @Package com.yrg.queue 
* @Description: TODO(用一句话描述该文件做什么) 
* @author yangrg  
* @date 2019年12月2日 下午12:48:08 
* @version V1.0   
*/
package com.yrg.queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.ServerSession;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/** 
* @ClassName: AppProducer 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author yangrg 
* @date 2019年12月2日 下午12:48:08 
*  
*/
public class AppProducer {
	
	
	//private static final String url="tcp://47.100.137.33:61616";
	private static final String url="failover:(tcp://47.100.137.33:61617,tcp://47.100.137.33:61618)?randomize=true";
	private static final String queueName="queue-test";
	
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
		Destination destination = session.createQueue(queueName);
		
		//6.创建一个生厂者
		MessageProducer producer = session.createProducer(destination);
		
		for (int i = 0; i < 100; i++) {
			//7.创建消息
			TextMessage textMessage = session.createTextMessage("test:"+i);
			
			//8.发送消息
			producer.send(textMessage);
			
			System.out.println("发送消息："+textMessage.getText());
		}
		
		//9.关闭连接
		connection.close();
	}
}
