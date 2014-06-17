package com.finalProject.smstranslator.SMSHalper;

import java.util.Comparator;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class SMSMainDetails.
 */
public class SMSMainDetails implements Comparator<SMSMainDetails> {

	/** The person id. */
	private int personId;
	
	/** The address. */
	private String address;
	
	/** The count. */
	private int count;
	
	/** The first msg. */
	private String firstMsg;
	
	/** The date. */
	private Date date;
	
	/**
	 * Gets the person id.
	 *
	 * @return the person id
	 */
	public int getPersonId() {
		return personId;
	}
	
	/**
	 * Sets the person id.
	 *
	 * @param personId the new person id
	 */
	public void setPersonId(int personId) {
		this.personId = personId;
	}
	
	/**
	 * Gets the address.
	 *
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * Sets the address.
	 *
	 * @param address the new address
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * Gets the count.
	 *
	 * @return the count
	 */
	public int getCount() {
		return count;
	}
	
	/**
	 * Sets the count.
	 *
	 * @param count the new count
	 */
	public void setCount(int count) {
		this.count = count;
	}
	
	/**
	 * Gets the first msg.
	 *
	 * @return the first msg
	 */
	public String getFirstMsg() {
		return firstMsg;
	}
	
	/**
	 * Sets the first msg.
	 *
	 * @param firstMsg the new first msg
	 */
	public void setFirstMsg(String firstMsg) {
		this.firstMsg = firstMsg;
	}
	
	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	
	/**
	 * Sets the date.
	 *
	 * @param date the new date
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	
	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(SMSMainDetails o1, SMSMainDetails o2) {
		return o1.getDate().compareTo(o2.getDate());
	}
}
