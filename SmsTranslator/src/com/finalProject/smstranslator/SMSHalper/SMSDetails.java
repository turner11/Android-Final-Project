package com.finalProject.smstranslator.SMSHalper;

import java.util.Date;

public class SMSDetails{
	private String body;
	private Date date;
	private int type;
	
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getDate() {
		return date;
	}
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
}