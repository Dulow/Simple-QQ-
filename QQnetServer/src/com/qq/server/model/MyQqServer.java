/*
 * QQ�������������ȴ�ĳ���ͻ�������
 */

package com.qq.server.model;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.qq.common.Massage;
import com.qq.common.User;

public class MyQqServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public MyQqServer() {
		try {
			//��9999����
			System.out.println("����...");
			ServerSocket ss= new ServerSocket(9999);
			//�������ȴ�����
			while(true) {
				Socket s =ss.accept();
				
				//���ܿͻ�����Ϣ
				 //BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
				 //String info = br.readLine();
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
				
				try {
					User u = (User)ois.readObject();
					Massage m=new Massage();
					//������ҪȥSQL����֤
					if(u.getPassword().equals("123456")) {
						//���سɹ���¼��Ϣ��
						
						m.setMestype("1");
						oos.writeObject(m);
						
						//��һ���߳�
						ServerConnectThread sct = new ServerConnectThread(s);
						
						ManagerClientThread.addClientThread(u.getUserid(), sct);
						//�����߳�
						sct.start();
						
						//֪ͨ���������û�
						sct.notifyother(u.getUserid());
						
					}else {
						m.setMestype("2");
						oos.writeObject(m);
						s.close();
					}
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}

}
