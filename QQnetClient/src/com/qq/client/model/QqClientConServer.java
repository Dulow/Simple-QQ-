/*
 * �ͻ������ӷ������Ĳ���;
 */
package com.qq.client.model;
import java.io.*;
import java.net.*;

import com.qq.client.tools.ClientConServerThread;
import com.qq.client.tools.ManagerClientconServerThread;
import com.qq.common.Massage;
import com.qq.common.User;

public class QqClientConServer {
	
	public boolean sendLoginInfoToServer(Object o) {
		
		boolean b = false;
		
		try {
			
			Socket s = new Socket("127.0.0.1",9999);
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());		
			
			oos.writeObject(o);
			
			 ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			 Massage ms = (Massage)ois.readObject();
			 
			 //��֤�û���¼�ĵط�
			 if(ms.getMestype().equals("1")) {
				 
				 //������qq�źͷ���������ͨѶ���ӵ��߳�
				 ClientConServerThread ccst = new ClientConServerThread(s);
				 //������ͨѶ�߳�
				 ccst.start();
				 ManagerClientconServerThread.addClientconServerThread(((User)o).getUserid(), ccst);
				 b = true;
			 }
			
		} catch (Exception e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return b;
	}
	
	public void SendInfoToServer(Object o) {
		try {
			
			Socket s = new Socket("127.0.0.1",9999);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
