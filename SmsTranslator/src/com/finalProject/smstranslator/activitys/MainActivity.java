package com.finalProject.smstranslator.activitys;


import com.finalProject.smstranslator.R;
import com.finalProject.smstranslator.SMSHalper.SMSProvider;
import com.finalProject.smstranslator.SMSHalper.SMSMainAdapter;
import com.finalProject.smstranslator.SMSHalper.SMSMainDetails;

import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


// TODO: Auto-generated Javadoc
/**
 * The Class MainActivity.
 */
public class MainActivity extends Activity {

	/** The smsrecevid. */
	public static String SMSRECEVID="com.finalProject.smstranslator.SMSRECEVEDINFO";
	
	/** The context. */
	private static Context context;
	
	/** The receiver. */
	private BroadcastReceiver receiver;
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		MainActivity.context = getApplicationContext();
		updateView();
		receiver = new BroadcastReceiver() {
			
			@Override
			public void onReceive(Context context, Intent intent) {
				updateView();
			}
		};
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		registerReceiver(receiver, new IntentFilter(MainActivity.SMSRECEVID));
		//updateView();
		super.onResume();
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onPause()
	 */
	@Override
	protected void onPause() {
		unregisterReceiver(receiver);
		super.onPause();
	}
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.action_settings:
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

	/**
	 * Gets the app context.
	 *
	 * @return the app context
	 */
	public static Context getAppContext() {
		return context;
	}
	
	/**
	 * On item clicked.
	 *
	 * @param address the address
	 */
	private void onItemClicked(String address){
		Intent intent = new Intent(this, ConversationActivity.class);
		intent.putExtra("address", address);
		startActivity(intent);
	}
	
	/**
	 * Update view.
	 */
	private void updateView(){
		SMSProvider provider = new SMSProvider(this);
		SMSMainAdapter ad = new SMSMainAdapter(this, provider.getMainSMSDetails());
		
		ListView lv = (ListView) findViewById(R.id.smsDetails);
		lv.setAdapter(ad);
		
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				SMSMainDetails entry = (SMSMainDetails) parent.getItemAtPosition(position);
				onItemClicked(entry.getAddress());
			}
		});
	}
}
