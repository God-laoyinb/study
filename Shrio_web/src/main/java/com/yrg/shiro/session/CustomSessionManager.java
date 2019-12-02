/**   
* @Title: CustomSessionManager.java 
* @Package com.yrg.shiro.session 
* @Description: TODO(用一句话描述该文件做什么) 
* @author yangrg  
* @date 2019年11月30日 下午9:07:35 
* @version V1.0   
*/
package com.yrg.shiro.session;

import java.io.Serializable;

import javax.servlet.ServletRequest;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionKey;

/** 
* @ClassName: CustomSessionManager 
* @Description: TODO 从request中读取，不要每次都从redis中读取
* @author yangrg 
* @date 2019年11月30日 下午9:07:35 
*  
*/
public class CustomSessionManager extends DefaultWebSessionManager {
	
	@Override
	protected Session retrieveSession(SessionKey sessionKey) throws UnknownSessionException {
		Serializable sessionId = getSessionId(sessionKey); //获取sessionId
		ServletRequest request = null;
		if(sessionKey instanceof WebSessionKey) {//判断sessionKey是否为WebSessinKey
			request = ((WebSessionKey)sessionKey).getServletRequest(); //从sessionKey中获取request
		}
		//request中获取session
		if(request !=null && sessionId !=null) {
		Session session= (Session) request.getAttribute(sessionId.toString());//从request中获取session
			if(session!=null) {
				return session;
			}
		}
		//从redis中获取session
		Session session = super.retrieveSession(sessionKey);
		if(request!=null && sessionId !=null) {
			request.setAttribute(sessionId.toString(), session);//将session设置到session中
		}
		return session;
	}
}
