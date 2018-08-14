/**
 * :����:QQ��¼����
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
	//�������
	JLabel jbl1;
	
	//�в�������в�����JPanel����һ��ѡ����ڹ���
	JTabbedPane jtp;
	
	JPanel jp2; //�˻������¼
	JPanel jp3; //�ֻ��ŵ�¼
	JPanel jp4; //�����¼
	
	JLabel jp2_jl1,jp2_jl2,jp2_jl3,jp2_jl4;
	JButton jp2_jb;
	JTextField jp2_jtf;
	JPasswordField jp2_jpf;
	JCheckBox jp2_jcb1,jp2_jcb2;
	
	//�ϲ����
	JPanel jp1;
	JButton jp1_jb1,jp1_jb2,jp1_jb3;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		QqClientLogin qqclientlogin = new QqClientLogin();
	}
	
	public QqClientLogin() {
		//������
		jbl1 = new JLabel(new ImageIcon("image/zhu.png"));
		this.add(jbl1, "North");
		this.setSize(360,300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//�����в�
		jp2 = new JPanel(new GridLayout(3,3));
		jp2_jl1 = new JLabel("QQ����",JLabel.CENTER);
		jp2_jl2 = new JLabel("QQ����",JLabel.CENTER);
		jp2_jl3 = new JLabel("���Ǻ���",JLabel.CENTER);
		jp2_jl4 = new JLabel("�������뱣��",JLabel.CENTER);
		jp2_jl3.setForeground(Color.BLUE);
		jp2_jb = new JButton("�������");
		jp2_jtf = new JTextField();
		jp2_jpf = new JPasswordField();
		jp2_jcb1 = new JCheckBox("�����¼");
		jp2_jcb2 = new JCheckBox("��ס����");
		
		//�ѿռ䰴˳�����JP2
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
		jtp.add("QQ�ŵ�½",jp2);
		jp3 = new JPanel();
		jtp.add("�ֻ��ŵ�¼",jp3);	
		jp4 = new JPanel();
		jtp.add("�����¼",jp4);
		
		this.add(jtp, "Center");
		
		//�����ϲ�
		jp1 = new JPanel(new FlowLayout());
		
		jp1_jb1 = new JButton("��¼");
		jp1_jb1.addActionListener(this);
		jp1_jb2 = new JButton("ȡ��");
		jp1_jb3 = new JButton("��");
		
		//����������JPanel
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
					//���������б�
					QqFriendList qfl = new QqFriendList(n.getUserid());
					ManagerFriendList.addQqFriendList(n.getUserid(), qfl);
					
					ObjectOutputStream oos = new ObjectOutputStream(ManagerClientconServerThread.getClientConServerThread(n.getUserid()).getS().getOutputStream());
					
					//��һ��massage��
					Massage m = new Massage();
					m.setMestype(MassageType.massage_get_onlinFriend);
					//˵����Ҫ�������qq�ŵĺ����б�
					m.setSender(n.getUserid());
					oos.writeObject(m);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//�رս���
				this.dispose();
			}else {
				JOptionPane.showMessageDialog(this, "�û����������");
			}
		}
	}

}
