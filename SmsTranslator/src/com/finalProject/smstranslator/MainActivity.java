package com.finalProject.smstranslator;

<<<<<<< HEAD

import com.finalProject.smstranslator.SMSHalper.SMSProvider;
import com.finalProject.smstranslator.SMSHalper.SMSMainAdapter;
import com.finalProject.smstranslator.SMSHalper.SMSMainDetails;

=======
>>>>>>> 330c7199e160f22798eefc1eb78cd4ec11177a22
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


public class MainActivity extends Activity  {


<<<<<<< HEAD
	private static Context context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
=======

	private static Context context;
	private static Context baseContext;

	EditText _txbText;
	Button _btnSend;

	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MainActivity.context = getApplicationContext();
		MainActivity.baseContext = getBaseContext();
>>>>>>> 330c7199e160f22798eefc1eb78cd4ec11177a22
		setContentView(R.layout.activity_main);
		context = getAppContext();
		SMSProvider provider = new SMSProvider(this);
		SMSMainAdapter ad = new SMSMainAdapter(this, provider.getMainSMSDetails());
		
		ListView lv = (ListView) findViewById(R.id.smsDetails);
		lv.setAdapter(ad);
		
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
<<<<<<< HEAD
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				SMSMainDetails entry = (SMSMainDetails) parent.getItemAtPosition(position);
				onItemClicked(entry.getAddress());
			}
		});
			
	}

=======
			public void onClick(View v) {

				String expression = _txbText.getText().toString();
				AsyncTranslator asyncTrnaslator = new AsyncTranslator(); 
				
				String symbolFrom = PreferencesManager.getLanguageFrom();
				String symbolTo = PreferencesManager.getLanguageTo();
				asyncTrnaslator.execute(expression,symbolFrom, symbolTo);
			}

			
		});

		

	}

	/**
	 * Gets the context of this application. This is mainly for POJO classes that require a context.
	 * @return the context of this application.
	 */
	public static Context getAppContext() {
		return MainActivity.context;
	}

	/**
	 * Gets the base context of this application. This is mainly for POJO classes that require a base context.
	 * 
	 * @return the base context
	 */
	public static Context getAppBaseContext() {
		return MainActivity.baseContext;
	}

>>>>>>> 330c7199e160f22798eefc1eb78cd4ec11177a22
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
<<<<<<< HEAD
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
	
=======
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_settings:
			Intent intent = new Intent(this, SettingsActivity.class);
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}




>>>>>>> 330c7199e160f22798eefc1eb78cd4ec11177a22
}
