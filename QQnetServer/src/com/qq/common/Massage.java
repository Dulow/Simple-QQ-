package com.qq.common;

public class Massage implements java.io.Serializable{
	
	private String mestype;
	
	private String sender;
	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getGetter() {
		return getter;
	}

	public void setGetter(String getter) {
		this.getter = getter;
	}

	public String getCon() {
		return con;
	}

	public void setCon(String con) {
		this.con = con;
	}

	public String getSendtime() {
		return sendtime;
	}

	public void setSendtime(String sendtime) {
		this.sendtime = sendtime;
	}

	private String getter;
	private String con;
	private String sendtime;

	public String getMestype() {
		return mestype;
	}

	public void setMestype(String mestype) {
		this.mestype = mestype;
	}
}
