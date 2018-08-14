/*
 * 管理通讯客户端与服务器保持通讯的线程类
 */
package com.qq.client.tools;

import java.util.HashMap;

public class ManagerClientconServerThread {
	private static HashMap hm = new HashMap<String,ClientConServerThread>();
	
	//把创建好的CCST放入hm
	public static void addClientconServerThread(String qqid,ClientConServerThread ccst){
		hm.put(qqid, ccst);
		
	}
	
	//可以通过QQID取得该线程
	public static ClientConServerThread getClientConServerThread(String qqid) {
		return (ClientConServerThread)hm.get(qqid);
	}

}
