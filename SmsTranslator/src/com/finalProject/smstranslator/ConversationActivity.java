package com.finalProject.smstranslator;

import com.finalProject.smstranslator.SMSHalper.*;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.telephony.SmsManager;
import android.util.SparseArray;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * The Class ConversationActivity for viewing conversations.
 */
public class ConversationActivity extends Activity implements IOnTranslationCompleted{

	
	/** The view that will be translated. */
	View _translatedView;
	ProgressDialog _loading;
	SparseArray<SMSDetails> _translatedSms;
	String _currentAddress;
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_conversation);
		Bundle extra =  getIntent().getExtras();
		_currentAddress = extra.getString("address");
		
		updateConversation();
		
		TextView add = (TextView) findViewById(R.id.tv_address);

		ContactHelper ch = new ContactHelper(this);
		String name = ch.getContactName(_currentAddress);
		if(name == null)
			add.setText(_currentAddress);
		else
			add.setText(name);

	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.conversation, menu);
		return true;
	}

	/* (non-Javadoc)
	 * @see com.finalProject.smstranslator.IOnTranslationCompleted#onTranslationCompleted(java.lang.String)
	 */
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
		_loading.cancel();
	}

	/**
	 * Translate.
	 * 
	 * @param view
	 *            the view
	 */
	public void translate(View view){
		EditText txbMessage = (EditText) findViewById(R.id.txbMessage);
		String expression = txbMessage.getText().toString();
		this.triggerTranslation(txbMessage, expression);
	}



	/**
	 * Send sms.
	 * 
	 * @param view
	 *            the view
	 */
	public void sendSMS(View view){
		EditText txbMessage = (EditText) findViewById(R.id.txbMessage);
		String expression = txbMessage.getText().toString();
		SmsManager sms = SmsManager.getDefault();
		String phone = Uri.decode(_currentAddress).toString();
		sms.sendTextMessage(phone, null, expression, null, null);
		
		txbMessage.setText("");
		updateConversation();
	}

	/**
	 * Trigger translation.
	 * 
	 * @param activeControl
	 *            the active control
	 * @param expression
	 *            the expression
	 */
	private void triggerTranslation(View activeControl, String expression) {	
		_loading = ProgressDialog.show(ConversationActivity.this, "", "Translating. Please wait...", true);
		
		AsyncTranslator asyncTrnaslator = new AsyncTranslator(); 

		String symbolFrom = PreferencesManager.getLanguageFrom();
		String symbolTo = PreferencesManager.getLanguageTo();

		this._translatedView = activeControl;
		asyncTrnaslator.execute(expression,symbolFrom, symbolTo, this);
	}
	
	private void updateConversation(){

		SMSProvider provider = new SMSProvider(this);
		SMSAdapter adapter = new SMSAdapter(this, provider.getSMSDetails(_currentAddress));

		final ListView lv = (ListView) findViewById(R.id.conversationList);
		lv.setAdapter(adapter);
		lv.setSelection(adapter.getCount() - 1);
		
		lv.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> adapter, View view, int position, long id) {
				
				//SMSDetails smsDetails = (SMSDetails)lv.getItemAtPosition(position);				
				
				TextView txbBody = (TextView)view.findViewById(R.id.tv_body);
				String txt = (String) txbBody.getText();
				triggerTranslation(txbBody, txt);
			       
			    // Toast.makeText(getBaseContext(),"Click!",Toast.LENGTH_SHORT).show();
				return false;
			}
	        
	    });
	}
}
