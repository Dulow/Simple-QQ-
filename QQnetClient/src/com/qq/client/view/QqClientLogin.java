/**
 * :功能:QQ登录界面
 * @author dell-dell
 *
 */
package com.qq.client.view;

import java.awt.Color;
import com.qq.client.tools.*;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import com.qq.client.model.QqClientConServer;
import com.qq.client.model.QqClientUser;
import com.qq.common.Massage;
import com.qq.common.MassageType;
import com.qq.common.User;

public class QqClientLogin extends JFrame implements ActionListener, KeyListener{
	//北部组件
	JLabel jbl1;
	
	//中部组件，中部三个JPanel，由一个选项卡窗口管理
	JTabbedPane jtp;
	
	JPanel jp2; //账户密码登录
	JPanel jp3; //手机号登录
	JPanel jp4; //邮箱登录
	
	JLabel jp2_jl1,jp2_jl2,jp2_jl3,jp2_jl4;
	JButton jp2_jb;
	JTextField jp2_jtf;
	JPasswordField jp2_jpf;
	JCheckBox jp2_jcb1,jp2_jcb2;
	
	//南部组件
	JPanel jp1;
	JButton jp1_jb1,jp1_jb2,jp1_jb3;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		QqClientLogin qqclientlogin = new QqClientLogin();
	}
	
	public QqClientLogin() {
		//处理北部
		jbl1 = new JLabel(new ImageIcon("image/zhu.png"));
		this.add(jbl1, "North");
		this.setSize(360,300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//处理中部
		jp2 = new JPanel(new GridLayout(3,3));
		jp2_jl1 = new JLabel("QQ号码",JLabel.CENTER);
		jp2_jl2 = new JLabel("QQ密码",JLabel.CENTER);
		jp2_jl3 = new JLabel("忘记号码",JLabel.CENTER);
		jp2_jl4 = new JLabel("申请密码保护",JLabel.CENTER);
		jp2_jl3.setForeground(Color.BLUE);
		jp2_jb = new JButton("清除号码");
		jp2_jtf = new JTextField();
		jp2_jpf = new JPasswordField();
		jp2_jcb1 = new JCheckBox("隐身登录");
		jp2_jcb2 = new JCheckBox("记住密码");
		
		//把空间按顺序加入JP2
		jp2.add(jp2_jl1);
		jp2.add(jp2_jtf);
		jp2.add(jp2_jb);
		jp2.add(jp2_jl2);
		jp2.add(jp2_jpf);
		jp2.add(jp2_jl3);
		jp2.add(jp2_jcb1);
		jp2.add(jp2_jcb2);
		jp2.add(jp2_jl4);
		
		jtp = new JTabbedPane();
		jtp.add("QQ号登陆",jp2);
		jp3 = new JPanel();
		jtp.add("手机号登录",jp3);	
		jp4 = new JPanel();
		jtp.add("邮箱登录",jp4);
		
		this.add(jtp, "Center");
		
		//处理南部
		jp1 = new JPanel(new FlowLayout());
		
		jp1_jb1 = new JButton("登录");
		jp1_jb1.addActionListener(this);
		jp1_jb2 = new JButton("取消");
		jp1_jb3 = new JButton("向导");
		
		//把三个放入JPanel
		jp1.add(jp1_jb1);
		jp1.add(jp1_jb2);
		jp1.add(jp1_jb3);
		
		this.add(jp1,"South");
		this.setVisible(true);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource() == jp1_jb1) {
			QqClientUser qcu = new QqClientUser();
			User n = new User();
			n.setUserid(jp2_jtf.getText().trim());
			n.setPassword(new String(jp2_jpf.getPassword()));
			
			if(qcu.checkUser(n)) {
				try {
					//创建好友列表
					QqFriendList qfl = new QqFriendList(n.getUserid());
					ManagerFriendList.addQqFriendList(n.getUserid(), qfl);
					
					ObjectOutputStream oos = new ObjectOutputStream(ManagerClientconServerThread.getClientConServerThread(n.getUserid()).getS().getOutputStream());
					
					//做一个massage包
					Massage m = new Massage();
					m.setMestype(MassageType.massage_get_onlinFriend);
					//说明我要的是这个qq号的好友列表
					m.setSender(n.getUserid());
					oos.writeObject(m);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//关闭界面
				this.dispose();
			}else {
				JOptionPane.showMessageDialog(this, "用户名密码错误");
			}
		}
	}

}
