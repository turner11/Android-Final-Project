package com.finalProject.smstranslator;


import com.finalProject.smstranslator.SMSHalper.SMSProvider;
import com.finalProject.smstranslator.SMSHalper.SMSMainAdapter;
import com.finalProject.smstranslator.SMSHalper.SMSMainDetails;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


public class MainActivity extends Activity {

	private static Context context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		context = getAppContext();
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

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

	public static Context getAppContext() {
		return context;
	}
	
	private void onItemClicked(String address){
		Intent intent = new Intent(this, ConversationActivity.class);
		intent.putExtra("address", address);
		startActivity(intent);
	}
	
}
