package com.qq.server.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;

import com.qq.common.Message;

public class SerConClientThread extends Thread{
	Socket s;
	private SerConClientThread sc;
	private String getter;
	public SerConClientThread(Socket s,String getter){
		this.getter=getter;
		this.s=s;
	}
	public void notifyOther(String iam){
		HashMap hm=ManageClientThread.hm;
		Iterator it=hm.keySet().iterator();
		while(it.hasNext()){
			Message m=new Message();
			m.setCon(iam);
			m.setMesType("5");
			String onLineUserId=it.next().toString();
			try{
				ObjectOutputStream oos=new ObjectOutputStream(ManageClientThread.getClientThread(onLineUserId).s.getOutputStream());
				m.setGetter(onLineUserId);
				oos.writeObject(m);
			}catch(Exception e){
				
			}
		}
	}
	public void notifyOther2(){
		ManageClientThread.hm.remove(getter);
		HashMap hm=ManageClientThread.hm;
		
		Iterator it=hm.keySet().iterator();
		while(it.hasNext()){
			
			Message m=new Message();
			m.setCon("");
			m.setMesType("6");
			m.setGetter(getter);
			String onLineUserId=it.next().toString();
			try{
				ObjectOutputStream oos=new ObjectOutputStream(ManageClientThread.getClientThread(onLineUserId).s.getOutputStream());
				m.setGetter(onLineUserId);
				oos.writeObject(m);
			}catch(Exception e){
				
			}
		}
	}
	public void run(){
		while(true){
			try {
				ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
				Message m=(Message)ois.readObject();
				getter=m.getSender();
				if(m.getMesType().equals("3")){
					sc=ManageClientThread.getClientThread(m.getGetter());
					ObjectOutputStream oos=new ObjectOutputStream(sc.s.getOutputStream());
					oos.writeObject(m);
				}
				else if(m.getMesType().equals("4")){
					String res=ManageClientThread.getAllOnLineUserid();
					Message m2=new Message();
					m2.setMesType("5");
					m2.setCon(res);
					m2.setGetter(m.getSender());
					ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
					oos.writeObject(m2);
				}
			} 
			catch(NullPointerException e1){
			}
			catch (Exception e) {
				this.notifyOther2();
				break;
			} 
		}
	}
}
