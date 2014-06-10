package com.finalProject.smstranslator.SMSHalper;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.util.ArrayMap;

public class SMSProvider {
	private static final String SMS_All = "content://sms";

	private Context context;

	public SMSProvider(Context context){
		this.context = context;
	}

	public ArrayList<SMSMainDetails> getMainSMSDetails(){
		ArrayMap<String , SMSMainDetails> data = new ArrayMap<String, SMSMainDetails>();
		Uri allUri = Uri.parse(SMS_All);

		String [] reqCols = {SMSProviderContruct.ID, SMSProviderContruct.ADDRESS, SMSProviderContruct.PERSON_ID, 
				SMSProviderContruct.BODY, SMSProviderContruct.DATE};

		// Get Content Resolver object, which will deal with Content Provider
		ContentResolver cr = context.getContentResolver();
		Cursor curser = cr.query(allUri, reqCols, null, null, null);

		curser.moveToFirst();


		while (!curser.isAfterLast()) {
			String addr;
			if(curser.getString(1).startsWith("0"))
				addr = curser.getString(1).substring(1).trim();
			else if(curser.getString(1).startsWith("+"))
				addr = curser.getString(1).substring(4).trim();
			else
				addr = curser.getString(1);
			if(data.get(addr) == null){
				SMSMainDetails curr = new SMSMainDetails();
				curr.setAddress(curser.getString(1));
				curr.setFirstMsg(curser.getString(3));
				curr.setPersonId(curser.getInt(2));
				curr.setDate(new Date(curser.getLong(4)));
				curr.setCount(1);
				data.put(addr, curr);
			}else{
				data.get(addr).setCount(data.get(addr).getCount() + 1);
			}
			
			curser.moveToNext();
		}
		
		
		ArrayList<SMSMainDetails> d = new ArrayList<SMSMainDetails>(Arrays.asList(data.values().toArray(new SMSMainDetails[0])));
		Collections.sort(d, new SMSMainDetails());
		Collections.reverse(d);

		return d;
	} 

	public ArrayList<SMSDetails> getSMSDetails(String addres){
		if(addres.startsWith("0"))
			addres = addres.substring(1).trim();
		else if(addres.startsWith("+"))
			addres = addres.substring(4).trim();
		
		
		ArrayList<SMSDetails> data = new ArrayList<SMSDetails>();
		Uri allUri = Uri.parse(SMS_All);

		String [] reqCols = {SMSProviderContruct.ID, SMSProviderContruct.TYPE, 
				SMSProviderContruct.BODY, SMSProviderContruct.DATE};

		// Get Content Resolver object, which will deal with Content Provider
		ContentResolver cr = context.getContentResolver();
		Cursor curser = cr.query(allUri, reqCols, SMSProviderContruct.ADDRESS + " LIKE ?", new String[] { "%" + addres + "%"}, SMSProviderContruct.DATE);

		curser.moveToFirst();


		while (!curser.isAfterLast()) {
			SMSDetails curr = new SMSDetails();
			curr.setDate(new Date(curser.getLong(3)));
			curr.setBody(curser.getString(2));
			curr.setType(curser.getInt(1));
			data.add(curr);
			
			curser.moveToNext();
		}
				
		return data;
	}
}
