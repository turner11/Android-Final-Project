package com.finalProject.smstranslator;

import com.finalProject.smstranslator.SMSHalper.*;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListView;
import android.widget.TextView;

public class ConversationActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_conversation);
		
		Bundle extra =  getIntent().getExtras();
		String address = extra.getString("address");
		
		SMSProvider provider = new SMSProvider(this);
		SMSAdapter adapter = new SMSAdapter(this, provider.getSMSDetails(address));
				
		ListView lv = (ListView) findViewById(R.id.conversationList);
		lv.setAdapter(adapter);
		lv.setSelection(adapter.getCount() - 1);
		
		TextView add = (TextView) findViewById(R.id.tv_address);
		
		ContactHelper ch = new ContactHelper(this);
		String name = ch.getContactName(address);
		if(name == null)
			add.setText(address);
		else
			add.setText(name);
			
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.conversation, menu);
		return true;
	}

}
