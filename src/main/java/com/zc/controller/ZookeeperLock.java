package com.zc.controller;

import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.Locker;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zc.util.ZookeeperUtil;

/** 
 * @Description: TODO
 * @author: zhangcheng
 * @date: 2019年3月18日 
 */

@Controller
public class ZookeeperLock {
	
	@ResponseBody
	@RequestMapping("zookeeperLock")
	public String zookeeperLock() {
		ZookeeperUtil zookeeperUtil = new ZookeeperUtil();
		CuratorFramework client = zookeeperUtil.createConnect();
		InterProcessMutex mutex = new InterProcessMutex(client, "/zookeeperLock");
		try(Locker locker = new Locker(mutex, 50, TimeUnit.SECONDS)){
			System.out.println(Thread.currentThread().getName()+"执行中。。。");
			Thread.sleep(20000);
			System.out.println(Thread.currentThread().getName()+"执行完毕！");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(mutex.isOwnedByCurrentThread()) {
				try {
					mutex.release();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return "执行完成";
	}

}
