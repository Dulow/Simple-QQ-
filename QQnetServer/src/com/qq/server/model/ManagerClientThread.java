package com.qq.server.model;

import java.util.HashMap;
import java.util.Iterator;

public class ManagerClientThread {

	public static HashMap hm = new HashMap<String,ServerConnectThread>();
	
	public static void addClientThread(String uid,ServerConnectThread ct) {
		hm.put(uid, ct)	;
		System.out.println(hm.values());
	}
	
	public static ServerConnectThread getClientThread(String uid) {
		
		return (ServerConnectThread)hm.get(uid);
	}
	
	//返回当前在线 的人的情况

	public static String getonlineUserid() {
		// TODO Auto-generated method stub
		//迭代器完成
		Iterator<String> it = hm.keySet().iterator();
		String res = "";
		while(it.hasNext()) {
			res = res + it.next().toString()+" ";
		}
		System.out.println(res);
		return res;
		
	}
}
