/*
 * function：我的好友列表，包括陌生人，黑名单
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
	//处理第一张卡片
	JPanel jpfriend1;
	JPanel jpfriend2;
	JPanel jpfriend3;
	JButton jpfriend_jb1;
	JButton jpfriend_jb2;
	JButton jpfriend_jb3;
	JScrollPane jsp;
	
	//第二张卡片
	JPanel jpmsr1;
	JPanel jpmsr2;
	JPanel jpmsr3;
	JButton jpmsr_jb1;
	JButton jpmsr_jb2;
	JButton jpmsr_jb3;
	JScrollPane jspmsr;
	
	//第三章卡片
	
	JLabel []jbls;
	
	//把JFrame 设置成卡片布局
	CardLayout cl;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//QqFriendList qfl = new QqFriendList();
	}
	
	//更新在线好友情况
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
		//第一张卡片的处理
		jpfriend1 = new JPanel(new BorderLayout());
		//假定有50好友
		jpfriend2 = new JPanel(new GridLayout(50,1,4,4));	
		jpfriend3 = new JPanel(new GridLayout(2, 1));
		
		jpfriend_jb1 = new JButton("我的好友");
		jpfriend_jb2 = new JButton("陌生人");
		jpfriend_jb2.addActionListener(this);
		jpfriend_jb3 = new JButton("黑名单");
		
		//把两个按钮加入JPFRIEND3
		jpfriend3.add(jpfriend_jb2);
		jpfriend3.add(jpfriend_jb3);
		
		//给jpfriend2初始化50好友
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
		
		//北边
		jpfriend1.add(jpfriend_jb1,"North");
		jpfriend1.add(jsp,"Center");
		jpfriend1.add(jpfriend3,"South");
		
		//处理第二张卡片
		
		jpmsr1 = new JPanel(new BorderLayout());
		//假定有50好友
		jpmsr2 = new JPanel(new GridLayout(20,1,4,4));
		jpmsr3 = new JPanel(new GridLayout(2, 1));
		
		jpmsr_jb1 = new JButton("我的好友");
		jpmsr_jb1.addActionListener(this);
		jpmsr_jb2 = new JButton("陌生人");
		jpmsr_jb3 = new JButton("黑名单");
		
		//把两个按钮加入JPmsr3
		jpmsr3.add(jpmsr_jb1);
		jpmsr3.add(jpmsr_jb2);
		
		//给jpmsr2初始化20陌生人
		JLabel []jblmsrs= new JLabel[20];
		
		for(int i = 0;i<jblmsrs.length;i++) {
			jblmsrs[i] = new JLabel(i+1+"",new ImageIcon("image/mstouxiang.png"),JLabel.LEFT);
			jpmsr2.add(jblmsrs[i]);
		}
		
		jspmsr = new JScrollPane(jpmsr2);
		
		//北边
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
			//System.out.println("你希望和"+friendname+"聊天");
			QqChat qc =new QqChat(this.ownerid,friendname);
			//把聊天界面加入管理类中
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
