/**   
* @Title: RedisCacheManager.java 
* @Package com.yrg.shiro.cache 
* @Description: TODO(用一句话描述该文件做什么) 
* @author yangrg  
* @date 2019年11月30日 下午10:02:32 
* @version V1.0   
*/
package com.yrg.shiro.cache;

import javax.annotation.Resource;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

/** 
* @ClassName: RedisCacheManager 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author yangrg 
* @date 2019年11月30日 下午10:02:32 
*  
*/
public class RedisCacheManager implements CacheManager{

	@Resource
	private RedisCache redisCache;
	
	public <K, V> Cache<K, V> getCache(String arg0) throws CacheException {
		
		return redisCache;
	}

}
