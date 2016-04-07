package com.qq.server.view;

import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.qq.server.model.MyQqServer;

public class MyServerFrame extends JFrame implements ActionListener,MouseListener {
	JPanel jp1;
	JButton jb1,jb2;
	MyQqServer ms;
	Thread th;
	public static void main(String [] args){
		MyServerFrame mysf=new MyServerFrame();
	}
	public MyServerFrame(){
		jp1=new JPanel();
		jb1=new JButton("启动服务器");
		jb2=new JButton("关闭服务器");
		jb1.addMouseListener(this);
		jb2.addMouseListener(this);
		jp1.add(jb1);
		jp1.add(jb2);
		this.add(jp1);
		this.setSize(500,400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置关闭方法
		this.setVisible(true);//显示窗体
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jb1){
			ms=new MyQqServer();
		}
		else{
			if(ms!=null){
				ms=null;
			}
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getClickCount()==1){
			if(e.getSource()==jb1){
				new Thread(){
					public void run(){
						ms=new MyQqServer();
					}
					}.start();
			}
			else{
				JOptionPane.showMessageDialog(this, "退出");
					System.exit(0);
			}
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
