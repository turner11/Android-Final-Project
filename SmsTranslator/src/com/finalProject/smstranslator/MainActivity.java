package com.finalProject.smstranslator;


import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends Activity {

	

	private static Context context;

	EditText _txbText;
	Button _btnSend;
	
	SharedPreferences _prefs; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MainActivity.context = getApplicationContext();
		setContentView(R.layout.activity_main);

		this._txbText = (EditText)this.findViewById(R.id.txbText);
		this._btnSend = (Button)this.findViewById(R.id.btnSend);

		this._btnSend.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				String expression = _txbText.getText().toString();
				AsyncTranslator asyncTrnaslator = new AsyncTranslator(); 
				
				String symbolFrom = _prefs.getString(getResources().getString(R.string.pref_LangFrom_Key), "en");
				String symbolTo = _prefs.getString(getResources().getString(R.string.pref_LangTo_Key), "en");
				
				
				asyncTrnaslator.execute(expression,symbolFrom, symbolTo);
			}
		});

		 this._prefs = PreferenceManager.getDefaultSharedPreferences(this);
		  
		 
	       


	}

	public static Context getAppContext() {
		return MainActivity.context;
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
