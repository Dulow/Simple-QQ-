/*
 * ����ͨѶ�ͻ��������������ͨѶ���߳���
 */
package com.qq.client.tools;

import java.util.HashMap;

public class ManagerClientconServerThread {
	private static HashMap hm = new HashMap<String,ClientConServerThread>();
	
	//�Ѵ����õ�CCST����hm
	public static void addClientconServerThread(String qqid,ClientConServerThread ccst){
		hm.put(qqid, ccst);
		
	}
	
	//����ͨ��QQIDȡ�ø��߳�
	public static ClientConServerThread getClientConServerThread(String qqid) {
		return (ClientConServerThread)hm.get(qqid);
	}

}
