/*
 * function:����������ҳ��
 */
package com.qq.server.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.qq.server.model.MyQqServer;

public class MyServerFame extends JFrame implements ActionListener {
	
	JButton jb1;
	JButton jb2;
	JPanel jp1;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyServerFame msf = new MyServerFame();
	}
	
	public MyServerFame() {
		
		jp1 = new JPanel();
		jb1 = new JButton("����������");
		jb1.addActionListener(this);
		jb2 = new JButton("�رշ�����");
		
		jp1.add(jb1);
		jp1.add(jb2);
		
		this.add(jp1);
		this.setSize(500,400);
		this.setTitle("������");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource().equals(jb1)) {
			new MyQqServer();
		}
	}

}
