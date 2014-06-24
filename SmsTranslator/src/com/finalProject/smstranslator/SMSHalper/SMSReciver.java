package com.finalProject.smstranslator.SMSHalper;

import com.finalProject.smstranslator.activitys.MainActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

// TODO: Auto-generated Javadoc
/**
 * The Class SMSReciver.
 */
public class SMSReciver extends BroadcastReceiver{

	/* (non-Javadoc)
	 * @see android.content.BroadcastReceiver#onReceive(android.content.Context, android.content.Intent)
	 */
	@Override
	public void onReceive(Context context, Intent intent) {
		Intent broad = new Intent();
		broad.setAction(MainActivity.SMSRECEVID);
        context.sendBroadcast(broad);
	}

}
