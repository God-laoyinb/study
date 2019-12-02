/**   
* @Title: RedisSessionDao.java 
* @Package com.yrg.shiro.session 
* @Description: TODO(用一句话描述该文件做什么) 
* @author yangrg  
* @date 2019年11月29日 下午3:26:06 
* @version V1.0   
*/
package com.yrg.shiro.session;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.util.SerializationUtils;

import com.yrg.shiro.utils.JedisUtils;

import jdk.nashorn.internal.ir.annotations.Reference;

/** 
* @ClassName: RedisSessionDao 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author yangrg 
* @date 2019年11月29日 下午3:26:06 
*  
*/
public class RedisSessionDao extends AbstractSessionDAO{

	@Resource
	private JedisUtils jedisUtils;
	
	private String SHIRO_SESSION_PREFIX ="yrg session";
	
	private  byte[] getkey(String key) {
		return (SHIRO_SESSION_PREFIX+key).getBytes();
	}
	
	private void savesession(Session session) {
		if(session!= null&&session.getId()!=null) {
			byte[] key = getkey(session.getId().toString());
			byte[] value = SerializationUtils.serialize(session);
			jedisUtils.set(key,value);
			jedisUtils.expire(key,600 );
		}
	}
	
	@Override
	protected Serializable doCreate(Session session) {
		Serializable sessionId = generateSessionId(session);//通过session获取session id
		assignSessionId(session, sessionId);//将sessionid和session进行捆绑
		savesession(session);
		return sessionId;
	}
	
	@Override
	protected Session doReadSession(Serializable sessionId) {
		System.out.println("read session");
		if(sessionId == null) {
			return null;
		}
		byte[] key = getkey(sessionId.toString());
		byte[] value = jedisUtils.get(key);
			
		return (Session) SerializationUtils.deserialize(value);
	}
	
	public void update(Session session) throws UnknownSessionException {
	
			savesession(session);
	
	}

	public void delete(Session session) {
		if(session ==null || session.getId() ==null) {
			return;
		}
		byte[] key =getkey(session.getId().toString());
		jedisUtils.del(key);
	}

	public Collection<Session> getActiveSessions() {
		Set<byte[]> keys = jedisUtils.keys(SHIRO_SESSION_PREFIX);
		Set<Session> sessions  = new HashSet<Session>();
		if(CollectionUtils.isEmpty(keys)) {
			return sessions;
		}
		for(byte[] key:keys) {
			Session session =(Session)SerializationUtils.deserialize(jedisUtils.get(key));
			sessions.add(session);
		}
		return sessions;
	}

	

	

}
