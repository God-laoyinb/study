/**   
* @Title: IProducerServer.java 
* @Package com.yrg.jms.producer 
* @Description: TODO(用一句话描述该文件做什么) 
* @author yangrg  
* @date 2019年12月3日 上午9:40:52 
* @version V1.0   
*/
package com.yrg.jms.producer;

/** 
* @ClassName: IProducerServer 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author yangrg 
* @date 2019年12月3日 上午9:40:52 
*  
*/
public interface IProducerServer {
	
	void sendMessage(String message);
}
