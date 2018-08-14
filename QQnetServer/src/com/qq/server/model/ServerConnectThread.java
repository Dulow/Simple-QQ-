/*
 * function:��������ĳ���ͻ��˵�ͨ���߳�
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
	
	//�ô��߳�֪ͨ�����û�
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
			//���߳̿��Խ��ܿͻ�����Ϣ
			try {
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				Massage m=(Massage)ois.readObject(); 
				
				System.out.println(m.getSender()+" "+m.getGetter()+ " "+m.getMestype());
				
				
				
				//�ӿͻ���ȡ�õ���Ϣ���������жϣ���������
				if(m.getMestype().equals(MassageType.massage_comm_mes)) {
					
					//���ת��
					//ȥ�Ľ����˵Ľ����߳�
					ServerConnectThread sct = ManagerClientThread.getClientThread(m.getGetter());
					ObjectOutputStream oos = new ObjectOutputStream(sct.s.getOutputStream());
					oos.writeObject(m);
				}else if(m.getMestype().equals(MassageType.massage_get_onlinFriend)) {
					//�ѷ��������ߵĺ��ѷ��ظ��ͻ�
					String res =ManagerClientThread.getonlineUserid();
					System.out.println(res+" "+m.getSender()+"Ҫ���ĺ���");
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
