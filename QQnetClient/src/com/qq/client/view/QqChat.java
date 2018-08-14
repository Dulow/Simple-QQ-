/*
 * function:���������
 * ��Ϊ�ͻ���Ҫ���ڶ�ȡ״̬�������߳�
 */
package com.qq.client.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.qq.client.model.QqClientConServer;
import com.qq.client.tools.ManagerClientconServerThread;
import com.qq.common.Massage;
import com.qq.common.MassageType;

public class QqChat extends JFrame implements ActionListener{
	
	JTextField jtf;
	JTextArea jta;
	JButton jb;
	JPanel jp;
	String ownerid;
	String friendid;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//QqChat qc = new QqChat();
	}
	
	public QqChat(String ownerid,String friendname) {
		jtf = new JTextField(20);
		this.ownerid = ownerid;
		this.friendid = friendname;
		jta = new JTextArea();
		jb = new JButton("Send"); 
		jb.addActionListener(this);
		jp = new JPanel();
		jp.add(jtf);
		jp.add(jb,"Left");
		
		this.add(jta,"Center");
		this.add(jp, "South");
		this.setIconImage((new ImageIcon("image/icon.png")).getImage());
		this.setTitle("�����ں�"+friendname+"����");
		this.setSize(350, 300);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == jb) {
			//����û�������Ͱ�ť
			Massage  m = new Massage();
			m.setSender(this.ownerid);
			m.setGetter(this.friendid);
			m.setCon(jtf.getText());
			m.setSendtime(new java.util.Date().toString());
			m.setMestype(MassageType.massage_comm_mes);
			//���͸�������
			try {
				ObjectOutputStream oos = new ObjectOutputStream(ManagerClientconServerThread.getClientConServerThread(ownerid).getS().getOutputStream());
				oos.writeObject(m);
			}catch (Exception e1) {
				// TODO: handle exception
				e1.printStackTrace();
			}
			
		}
	}
	
	//дһ��������ʾ��Ϣ
	public void showMasage(Massage m) {
		String info = m.getSender() + "��" + m.getGetter()+"˵"+m.getCon()+"\r\n";
		this.jta.append(info);
	}

	/*@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			//��ȡ���������ȴ�
			ObjectInputStream ois = new ObjectInputStream(QqClientConServer.s.getInputStream());
			Massage m = (Massage)ois.readObject();
			//���
			String info = m.getSender() + "��" + m.getGetter()+"˵"+m.getCon()+"\r\n";
			this.jta.append(info);
		}
	}*/

}
