/**   
* @Title: JedisUtils.java 
* @Package com.yrg.shiro.utils 
* @Description: TODO(用一句话描述该文件做什么) 
* @author yangrg  
* @date 2019年11月29日 下午4:27:57 
* @version V1.0   
*/
package com.yrg.shiro.utils;

import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jdk.nashorn.internal.ir.annotations.Reference;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/** 
* @ClassName: JedisUtils 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author yangrg 
* @date 2019年11月29日 下午4:27:57 
*  
*/
@Component
public class JedisUtils {

	@Autowired
	private JedisPool jedisPool;

	private Jedis getResource() {
		return jedisPool.getResource();
	}
	 
	/**
	* @Title: set 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param key
	* @param @param value
	* @param @return     
	* @return byte[]    
	* @throws
	 */
	public byte[] set(byte[] key, byte[] value) {
		Jedis jedis = getResource();
		try {
			jedis.set(key, value);
			return value;
		} finally {
			jedis.close();
		}
	}
	
	/** 
	* @Title: expire 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param key
	* @param @param i     
	* @return void    
	* @throws 
	*/
	public void expire(byte[] key, int i) {
		Jedis jedis = getResource();
		try {
			jedis.expire(key, i);
		}finally {
			jedis.close();
		}
	}

	/** 
	* @Title: get 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param value
	* @param @return     
	* @return byte []    
	* @throws 
	*/
	public byte[] get(byte[] key) {
		
		Jedis jedis = getResource();
		try {
			
			return jedis.get(key);
		} finally {
			jedis.close();
		}
	}

	/** 
	* @Title: del 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param key     
	* @return void    
	* @throws 
	*/
	public void del(byte[] key) {
		Jedis jedis = getResource();
		try {
			jedis.del(key);
		} finally {
			jedis.close();
		}
	}

	/** 
	* @Title: keys 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param sHIRO_SESSION_PREFIX
	* @param @return     
	* @return Set<byte []>    
	* @throws 
	*/
	public Set<byte[]> keys(String sHIRO_SESSION_PREFIX) {
		Jedis jedis = getResource();
		try {
			
			return jedis.keys((sHIRO_SESSION_PREFIX+"*").getBytes());
		} finally {
			jedis.close();
		}
	}

	
	
	
	
}
