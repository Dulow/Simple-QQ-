/*
 * 客户端和服务器保持通讯的进程
 */

package com.qq.client.tools;

import java.io.ObjectInputStream;
import java.net.Socket;

import com.qq.client.view.QqChat;
import com.qq.client.view.QqFriendList;
import com.qq.common.Massage;
import com.qq.common.MassageType;

public class ClientConServerThread extends Thread {

	private Socket s;
	
	public Socket getS() {
		return s;
	}

	public void setS(Socket s) {
		this.s = s;
	}

	public ClientConServerThread(Socket s) {
		this.s = s;
	}
	
	public void run() {
		while(true) {
			//不停读取从服务器端发来的消息
			try {
				ObjectInputStream ois =new ObjectInputStream(s.getInputStream());
				Massage m = (Massage)ois.readObject();
				System.out.println("读取到服务器发来的消息"+m.getSender()+"给"+m.getGetter()+"内容是  "
				+m.getCon()+"type"+m.getMestype()+"\r\n");
				
				if(m.getMestype().equals(MassageType.massage_comm_mes)) {
					//把从服务器获得的消息显示到聊天界面
					QqChat qc = ManagerQqChat.getQqChat(m.getGetter()+" "+m.getSender());
					//显示
					qc.showMasage(m);
				}else if(m.getMestype().equals(MassageType.massage_ret_onlinFriend)) {
					System.out.println("处理retfl");
					String con = m.getCon();
					System.out.println(con);
					String friends[] = con.split(" ");
					String getter = m.getGetter();
					//修改响应的好友列表
					QqFriendList qqfriendlist = ManagerFriendList.getQqFriendList(getter);
					//更新在线好友
					if(qqfriendlist != null) {
						qqfriendlist.updataFriend(m);
					}
				}
				
				
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}
