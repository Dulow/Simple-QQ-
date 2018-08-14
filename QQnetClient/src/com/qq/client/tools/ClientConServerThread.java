/*
 * �ͻ��˺ͷ���������ͨѶ�Ľ���
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
			//��ͣ��ȡ�ӷ������˷�������Ϣ
			try {
				ObjectInputStream ois =new ObjectInputStream(s.getInputStream());
				Massage m = (Massage)ois.readObject();
				System.out.println("��ȡ����������������Ϣ"+m.getSender()+"��"+m.getGetter()+"������  "
				+m.getCon()+"type"+m.getMestype()+"\r\n");
				
				if(m.getMestype().equals(MassageType.massage_comm_mes)) {
					//�Ѵӷ�������õ���Ϣ��ʾ���������
					QqChat qc = ManagerQqChat.getQqChat(m.getGetter()+" "+m.getSender());
					//��ʾ
					qc.showMasage(m);
				}else if(m.getMestype().equals(MassageType.massage_ret_onlinFriend)) {
					System.out.println("����retfl");
					String con = m.getCon();
					System.out.println(con);
					String friends[] = con.split(" ");
					String getter = m.getGetter();
					//�޸���Ӧ�ĺ����б�
					QqFriendList qqfriendlist = ManagerFriendList.getQqFriendList(getter);
					//�������ߺ���
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
