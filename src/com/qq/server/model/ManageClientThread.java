package com.qq.server.model;

import java.util.HashMap;
import java.util.Iterator;

public class ManageClientThread {
	public static HashMap hm=new HashMap<String,SerConClientThread>();
	public static void addClientThread(String uid,SerConClientThread ct){
		hm.put(uid, ct);
	}
	public static SerConClientThread getClientThread(String uid){
		return (SerConClientThread)hm.get(uid);
	}
	public static String getAllOnLineUserid(){
		Iterator it=hm.keySet().iterator();
		String res="";
		while(it.hasNext()){
			res+=it.next().toString()+" ";
		}
		return res;
	}
}
