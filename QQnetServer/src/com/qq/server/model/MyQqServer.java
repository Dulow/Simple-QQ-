/*
 * QQ服务器，监听等待某个客户端连接
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
			//在9999监听
			System.out.println("监听...");
			ServerSocket ss= new ServerSocket(9999);
			//阻塞，等待连接
			while(true) {
				Socket s =ss.accept();
				
				//接受客户端信息
				 //BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
				 //String info = br.readLine();
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
				
				try {
					User u = (User)ois.readObject();
					Massage m=new Massage();
					//讲道理要去SQL里验证
					if(u.getPassword().equals("123456")) {
						//返回成功登录信息报
						
						m.setMestype("1");
						oos.writeObject(m);
						
						//开一个线程
						ServerConnectThread sct = new ServerConnectThread(s);
						
						ManagerClientThread.addClientThread(u.getUserid(), sct);
						//启动线程
						sct.start();
						
						//通知其他在线用户
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
