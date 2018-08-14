/*
 * 管理用户聊天界面
 */

package com.qq.client.tools;

import java.util.HashMap;

import com.qq.client.view.QqChat;

public class ManagerQqChat {

	private static HashMap hm =new HashMap<String ,QqChat>();
	
	public static void addQqChat(String LoginIdAnFriend,QqChat qqchat) {
		hm.put(LoginIdAnFriend, qqchat);
		}
	
	public static QqChat getQqChat(String LoginInAnFriend) {
		return (QqChat)hm.get(LoginInAnFriend);
	}
}
