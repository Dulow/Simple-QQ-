/*
 * function:服务器和某个客户端的通信线程
 */

package com.qq.server.model;

import java.io.IOException;
import com.qq.server.model.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;

import com.qq.common.Massage;
import com.qq.common.MassageType;

public class ServerConnectThread extends Thread{
	
	Socket s;
	
	public ServerConnectThread(Socket s) {
		
		this.s= s;
		
	}
	
	//让带线程通知其他用户
	public void notifyother(String iam) {
		HashMap hm = ManagerClientThread.hm;
		Iterator it = hm.keySet().iterator();
		while(it.hasNext()) {
			
			String onlineUserId= it.next().toString();
			Massage m = new Massage();
			m.setMestype(MassageType.massage_ret_onlinFriend);	
			
			try {
				
				ObjectOutputStream oos =new ObjectOutputStream(ManagerClientThread.getClientThread(onlineUserId).s.getOutputStream());
				m.setGetter(onlineUserId);
				oos.writeObject(m);
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}
	}
	
	public void run() {
		
		while(true) {
			//该线程可以接受客户端信息
			try {
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				Massage m=(Massage)ois.readObject(); 
				
				System.out.println(m.getSender()+" "+m.getGetter()+ " "+m.getMestype());
				
				
				
				//从客户端取得的消息进行类型判断，并做处理
				if(m.getMestype().equals(MassageType.massage_comm_mes)) {
					
					//完成转发
					//去的接收人的接受线程
					ServerConnectThread sct = ManagerClientThread.getClientThread(m.getGetter());
					ObjectOutputStream oos = new ObjectOutputStream(sct.s.getOutputStream());
					oos.writeObject(m);
				}else if(m.getMestype().equals(MassageType.massage_get_onlinFriend)) {
					//把服务器上线的好友返回给客户
					String res =ManagerClientThread.getonlineUserid();
					System.out.println(res+" "+m.getSender()+"要他的好友");
					Massage m2 = new Massage();
					m2.setMestype(MassageType.massage_ret_onlinFriend);
					m2.setCon(res);
					m2.setGetter(m.getSender());
					ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
					oos.writeObject(m2);
				}
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
