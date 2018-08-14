/*
 * 管理好友，陌生人，黑名单类
 */
package com.qq.client.tools;

import java.util.HashMap;

import com.qq.client.view.QqFriendList;

public class ManagerFriendList {
	private static HashMap hm = new HashMap<String,QqFriendList>();
	
	public static void addQqFriendList(String qqid,QqFriendList qqfriendlist) {
		hm.put(qqid,qqfriendlist);
	}
	
	public static QqFriendList getQqFriendList(String qqid) {
		return (QqFriendList)hm.get(qqid);
	}
}
