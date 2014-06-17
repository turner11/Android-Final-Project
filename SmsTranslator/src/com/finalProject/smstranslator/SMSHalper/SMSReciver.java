package com.finalProject.smstranslator.SMSHalper;

import com.finalProject.smstranslator.activitys.MainActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class SMSReciver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		Intent broad = new Intent();
		broad.setAction(MainActivity.SMSRECEVID);
        context.sendBroadcast(broad);		
	}

}
