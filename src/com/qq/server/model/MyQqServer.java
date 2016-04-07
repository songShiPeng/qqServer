package com.qq.server.model;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;

import com.qq.common.Message;
import com.qq.common.QQUser;

public class MyQqServer {
	
	public MyQqServer(){
		try{
			ServerSocket ss=new ServerSocket(9999);
			while(true){
				Socket s=ss.accept();
				ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
				System.out.println("000");
				QQUser u=(QQUser)ois.readObject();
				if(u.getUserId()==null)
					continue;
				Message m=new Message();
				ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
				if(u.getPasswd().equals("123")){
					m.setMesType("1");
					oos.writeObject(m);
					if(ManageClientThread.hm.get(m.getSender())==null);
					SerConClientThread scct=new SerConClientThread(s,m.getSender());
					ManageClientThread.addClientThread(u.getUserId(),scct);
					scct.start();
					//发给他他的好友在线列表
					/**
					HashMap hm=ManageClientThread.hm;
					Iterator it=hm.keySet().iterator();
					StringBuffer mmm=new StringBuffer("");
					for(;it.hasNext();){
						mmm.append(it.next().toString()+" ");
					}
					m.setCon(mmm.toString());
					m.setMesType("5");
					m.setGetter(u.getUserId());
					System.out.println("mmm"+mmm);
					ObjectOutputStream oos2=new ObjectOutputStream(ManageClientThread.getClientThread(u.getUserId()).s.getOutputStream());
					oos2.writeObject(m);*/
					scct.notifyOther(u.getUserId());
				}
				else{
					m.setMesType("2");
					oos.writeObject(m);
					s.close();
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
