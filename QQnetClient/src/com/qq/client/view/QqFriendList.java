/*
 * function���ҵĺ����б�����İ���ˣ�������
 */
package com.qq.client.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.qq.client.tools.ClientConServerThread;
import com.qq.client.tools.ManagerQqChat;
import com.qq.common.Massage;

public class QqFriendList extends JFrame implements ActionListener,MouseListener{
	
	String ownerid;
	//�����һ�ſ�Ƭ
	JPanel jpfriend1;
	JPanel jpfriend2;
	JPanel jpfriend3;
	JButton jpfriend_jb1;
	JButton jpfriend_jb2;
	JButton jpfriend_jb3;
	JScrollPane jsp;
	
	//�ڶ��ſ�Ƭ
	JPanel jpmsr1;
	JPanel jpmsr2;
	JPanel jpmsr3;
	JButton jpmsr_jb1;
	JButton jpmsr_jb2;
	JButton jpmsr_jb3;
	JScrollPane jspmsr;
	
	//�����¿�Ƭ
	
	JLabel []jbls;
	
	//��JFrame ���óɿ�Ƭ����
	CardLayout cl;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//QqFriendList qfl = new QqFriendList();
	}
	
	//�������ߺ������
	public void updataFriend(Massage m) {
		String onlineFriend[] = m.getCon().split(" ");
		
		for(int i=0;i<onlineFriend.length;i++) {
			System.out.println("updating");
			System.out.println(onlineFriend[i]);
			jbls[Integer.parseInt(onlineFriend[i])-1].setEnabled(true);
		}
	}
	
	public QqFriendList(String ownweid) {
		this.ownerid = ownweid;
		//��һ�ſ�Ƭ�Ĵ���
		jpfriend1 = new JPanel(new BorderLayout());
		//�ٶ���50����
		jpfriend2 = new JPanel(new GridLayout(50,1,4,4));	
		jpfriend3 = new JPanel(new GridLayout(2, 1));
		
		jpfriend_jb1 = new JButton("�ҵĺ���");
		jpfriend_jb2 = new JButton("İ����");
		jpfriend_jb2.addActionListener(this);
		jpfriend_jb3 = new JButton("������");
		
		//��������ť����JPFRIEND3
		jpfriend3.add(jpfriend_jb2);
		jpfriend3.add(jpfriend_jb3);
		
		//��jpfriend2��ʼ��50����
		jbls= new JLabel[50];
		
		for(int i = 0;i<jbls.length;i++) {
			jbls[i] = new JLabel(i+1+"",new ImageIcon("image/touxiang.png"),JLabel.LEFT);
			jbls[i].setEnabled(false);
			if(jbls[i].getText().equals(ownweid)) {
				jbls[i].setEnabled(true);
			}
			jbls[i].addMouseListener(this);
			jpfriend2.add(jbls[i]);
		}
		
		jsp = new JScrollPane(jpfriend2);
		
		//����
		jpfriend1.add(jpfriend_jb1,"North");
		jpfriend1.add(jsp,"Center");
		jpfriend1.add(jpfriend3,"South");
		
		//����ڶ��ſ�Ƭ
		
		jpmsr1 = new JPanel(new BorderLayout());
		//�ٶ���50����
		jpmsr2 = new JPanel(new GridLayout(20,1,4,4));
		jpmsr3 = new JPanel(new GridLayout(2, 1));
		
		jpmsr_jb1 = new JButton("�ҵĺ���");
		jpmsr_jb1.addActionListener(this);
		jpmsr_jb2 = new JButton("İ����");
		jpmsr_jb3 = new JButton("������");
		
		//��������ť����JPmsr3
		jpmsr3.add(jpmsr_jb1);
		jpmsr3.add(jpmsr_jb2);
		
		//��jpmsr2��ʼ��20İ����
		JLabel []jblmsrs= new JLabel[20];
		
		for(int i = 0;i<jblmsrs.length;i++) {
			jblmsrs[i] = new JLabel(i+1+"",new ImageIcon("image/mstouxiang.png"),JLabel.LEFT);
			jpmsr2.add(jblmsrs[i]);
		}
		
		jspmsr = new JScrollPane(jpmsr2);
		
		//����
		jpmsr1.add(jpmsr_jb3,"South");
		jpmsr1.add(jspmsr,"Center");
		jpmsr1.add(jpmsr3,"North");
		
		cl = new CardLayout();
		this.setLayout(cl);
		this.add(jpfriend1,"1");
		this.add(jpmsr1, "2");
		this.setTitle(ownweid);
		this.setSize(300,600);
		this.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jpfriend_jb2) {
			cl.show(this.getContentPane(),"2");
		}
		
		if(e.getSource() == jpmsr_jb1) {
			cl.show(this.getContentPane(),"1");
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getClickCount() == 2) {
			String friendname = ((JLabel)e.getSource()).getText();
			//System.out.println("��ϣ����"+friendname+"����");
			QqChat qc =new QqChat(this.ownerid,friendname);
			//�������������������
			ManagerQqChat.addQqChat(this.ownerid+" "+friendname, qc);
			
			//ClientConServerThread ccst=new ClientConServerThread(s) ;
			//Thread t =new Thread(qc);
			//t.start();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel j1 = (JLabel)e.getSource();
		j1.setForeground(Color.RED);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel j1 = (JLabel)e.getSource();
		j1.setForeground(Color.BLACK);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
