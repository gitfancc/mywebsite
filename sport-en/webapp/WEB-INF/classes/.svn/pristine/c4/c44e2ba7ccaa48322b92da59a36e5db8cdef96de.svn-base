package com.appscomm.sport.cache;

import org.springframework.stereotype.Service;

import com.appscomm.sport.model.UserVO;
import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

/**
 * memcache缓存客户端，使用需先启动memcache客户端
 * @author yangyinqqiang
 *
 */
@Service("cacheClient")
public class MemcacheClient implements CacheClient{
	protected static MemCachedClient mcc = new MemCachedClient();

	static {

		// 设置缓存服务器列表，当使用分布式缓存的时，可以指定多个缓存服务器。
		String[] servers = { "127.0.0.1:11211"};

		// 设置服务器权重
		Integer[] weights = { 1 };

		// 创建一个Socked连接池实例
		SockIOPool pool = SockIOPool.getInstance();

		// 向连接池设置服务器和权重
		pool.setServers(servers);
		pool.setWeights(weights);

		pool.setNagle(false);
		pool.setSocketTO(3000);
		pool.setSocketConnectTO(0);

		pool.initialize();
	}
	
	public void setCache(String key, Object value){
		mcc.set(key, value);
	}
	
	public Object getCache(String key){
		return mcc.get(key);
	}

	public void deleteCache(String key){
		mcc.delete(key);
	}
	
	public static void main(String[] args) {
		UserVO u = new UserVO();
		u.setId(123L);
		u.setAccount("cevin");
		
		mcc.set("foo", "This is a test String123");
		mcc.set("user", u);
		UserVO u1 =(UserVO)mcc.get("user");
		System.out.println(u1.getAccount());
		String bar = mcc.get("user").toString();
		System.out.println(">>> " + bar);
	}
}
