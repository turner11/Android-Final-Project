package com.finalProject.smstranslator;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends Activity  {



	private static Context context;
	private static Context baseContext;

	EditText _txbText;
	Button _btnSend;

	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MainActivity.context = getApplicationContext();
		MainActivity.baseContext = getBaseContext();
		setContentView(R.layout.activity_main);

		this._txbText = (EditText)this.findViewById(R.id.txbText);
		this._btnSend = (Button)this.findViewById(R.id.btnSend);

		this._btnSend.setOnClickListener(new View.OnClickListener() {
			@Override
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
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




}
