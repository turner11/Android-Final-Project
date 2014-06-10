package com.finalProject.smstranslator.SMSHalper;

import java.util.Comparator;
import java.util.Date;

public class SMSMainDetails implements Comparator<SMSMainDetails> {

	private int personId;
	private String address;
	private int count;
	private String firstMsg;
	private Date date;
	
	public int getPersonId() {
		return personId;
	}
	public void setPersonId(int personId) {
		this.personId = personId;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	public String getFirstMsg() {
		return firstMsg;
	}
	public void setFirstMsg(String firstMsg) {
		this.firstMsg = firstMsg;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public int compare(SMSMainDetails o1, SMSMainDetails o2) {
		return o1.getDate().compareTo(o2.getDate());
	}
}
