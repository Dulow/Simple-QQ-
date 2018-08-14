/*
 * 客户端连接服务器的部分;
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
			 
			 //验证用户登录的地方
			 if(ms.getMestype().equals("1")) {
				 
				 //创建该qq号和服务器保持通讯连接的线程
				 ClientConServerThread ccst = new ClientConServerThread(s);
				 //启动该通讯线程
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
