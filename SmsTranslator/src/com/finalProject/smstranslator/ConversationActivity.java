package com.finalProject.smstranslator;

import com.finalProject.smstranslator.SMSHalper.*;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ConversationActivity extends Activity implements IOnTranslationCompleted{

	View _translatedView;
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
	
	public void onTranslationCompleted(String result)
	{
		if (this._translatedView.getId() == R.id.btnSend) {
			Toast.makeText(MainActivity.getAppContext(), "Sent: "+result, Toast.LENGTH_LONG).show();
		}
		else if (this._translatedView instanceof EditText) {
			EditText txb = (EditText) this._translatedView;
			txb.setText(result);
		   
		}
		else if (this._translatedView instanceof TextView) {
		    TextView textView = (TextView) this._translatedView;
		    textView.setText(result);
		}
	}
	
	public void translate(View view){
		
		EditText txbMessage = (EditText) findViewById(R.id.txbMessage);
		String expression = txbMessage.getText().toString();
		this.triggerTranslation(txbMessage, expression);
		
		
	}

	
	
	public void sendSMS(View view){
		ImageButton btnSend = (ImageButton) findViewById(R.id.btnSend);
		EditText txbMessage = (EditText) findViewById(R.id.txbMessage);
		String expression = txbMessage.getText().toString();
		this.triggerTranslation(btnSend, expression);
	}
	
private void triggerTranslation(View activeControl, String expression) {	
		
		AsyncTranslator asyncTrnaslator = new AsyncTranslator(); 
		
		String symbolFrom = PreferencesManager.getLanguageFrom();
		String symbolTo = PreferencesManager.getLanguageTo();
		
		this._translatedView = activeControl;
		asyncTrnaslator.execute(expression,symbolFrom, symbolTo,this);
	}

}
