/**   
* @Title: RedisCache.java 
* @Package com.yrg.shiro.cache 
* @Description: TODO(用一句话描述该文件做什么) 
* @author yangrg  
* @date 2019年11月30日 下午10:34:25 
* @version V1.0   
*/
package com.yrg.shiro.cache;

import java.util.Collection;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import com.yrg.shiro.utils.JedisUtils;

import redis.clients.util.JedisURIHelper;


/** 
* @ClassName: RedisCache 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author yangrg 
* @date 2019年11月30日 下午10:34:25 
*  
*/

@Component
public class RedisCache<K,V > implements Cache<K, V> {

	@Resource
	private JedisUtils jedisUtils;
	
	private final String CACHE_PREFIX = "yrg-cache:";
	
	//获取key
	private byte[] getkey(K k) {
		if(k instanceof String) {
			return (CACHE_PREFIX+k).getBytes();
		}
		return SerializationUtils.serialize(k);
	}
	
	public V get(K arg0) throws CacheException {
		System.out.println("从redis中得到数据");
		byte[] value = jedisUtils.get(getkey(arg0));
		if(value!=null) {
			return (V) SerializationUtils.deserialize(value);
		}
		return null;
	}
	
	public V put(K arg0, V arg1) throws CacheException {
		byte[] key =getkey(arg0);
		byte[] value =SerializationUtils.serialize(arg1);
		jedisUtils.set(key, value);
		jedisUtils.expire(key, 600);
		return arg1;
	}
	
	public V remove(K arg0) throws CacheException {
		byte[] key = getkey(arg0);
		byte[] value = jedisUtils.get(key);
		jedisUtils.del(key);
		if(value != null) {
			return (V) SerializationUtils.deserialize(value);
		}
		return null;
	}
	
	public void clear() throws CacheException {
		
	}
	
	public int size() {
		
		return 0;
	}

	public Set<K> keys() {
		
		return null;
	}

	public Collection<V> values() {
		
		return null;
	}

}
