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
	
	//���ص�ǰ���� ���˵����

	public static String getonlineUserid() {
		// TODO Auto-generated method stub
		//���������
		Iterator<String> it = hm.keySet().iterator();
		String res = "";
		while(it.hasNext()) {
			res = res + it.next().toString()+" ";
		}
		System.out.println(res);
		return res;
		
	}
}
