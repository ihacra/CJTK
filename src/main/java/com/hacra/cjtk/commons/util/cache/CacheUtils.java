package com.hacra.cjtk.commons.util.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * CacheManager
 * 单例模式-双检锁
 * 
 * @author Hacra
 * @version 2020/12/13
 */
public class CacheUtils {

	private volatile static CacheUtils cacheManager;
	private Map<String, Object> cacheMap;
	
	private CacheUtils() {
		cacheMap = new HashMap<String, Object>();
	}
	
	/**
	 * 获取唯一实例化对象
	 * @return
	 */
	public static CacheUtils getInstance() {
		if (cacheManager == null) {
			synchronized(CacheUtils.class) {
				if (cacheManager == null) {
					cacheManager = new CacheUtils();
				}
			}
		}
		return cacheManager;
	}
	
	/**
	 * 添加数据缓存
	 * 虚拟机内存小于5MB时清空缓存
	 * @param key
	 * @param val
	 */
	public void addCache(String key, Object val) {
		if (Runtime.getRuntime().freeMemory() < 5*1024*1024) {
			removeAllCache();
		} else {
			cacheMap.put(key, val);
		}
	}
	
	/**
	 * 获取数据缓存
	 * @param key
	 * @return
	 */
	public Object getCache(String key) {
		return cacheMap.get(key);
	}
	
	/**
	 * 清除数据缓存
	 * @param key
	 */
	public void removeCache(String key) {
		cacheMap.remove(key);
	}
	
	/**
	 * 清空全部缓存
	 */
	public void removeAllCache() {
		cacheMap.clear();
	}
}
