package com.zc.util;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/** 
 * @Description: TODO
 * @author: zhangcheng
 * @date: 2019年3月18日 
 */
public class ZookeeperUtil {
	
	/**
	 * 连接zookeeper
	 * @param host
	 * @return
	 * @throws InterruptedException 
	 * @throws Exception
	 */
	public CuratorFramework createConnect() {
		// 建立连接客户端
	    CuratorFramework client = CuratorFrameworkFactory.builder()
							    	.connectString("47.100.52.42:2181")
							    	.sessionTimeoutMs(1000*60*4) 	// 会话超时时间 单位毫秒 4分钟
							    	.connectionTimeoutMs(1000*60*3) 	// 连接超时时间 单位毫秒 3分钟
						         	.retryPolicy(new ExponentialBackoffRetry(1000, 3)) // 重试次数
						         	.build();
	    // 连接
	    client.start();
	    // 返回客户端
	    System.out.println("连接成功==========================>");
	    return client;
	}

}
