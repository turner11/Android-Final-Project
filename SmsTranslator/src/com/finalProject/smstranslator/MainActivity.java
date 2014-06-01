package com.finalProject.smstranslator;




import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	EditText _txbText;
	Button _btnSend;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        this._txbText = (EditText)this.findViewById(R.id.txbText);
        this._btnSend = (Button)this.findViewById(R.id.btnSend);
        
        this._btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	String expression = _txbText.getText().toString();
            	AsyncTranslator asyncTrnaslator = new AsyncTranslator(); 
                asyncTrnaslator.execute(expression, null, null);
            }
        });
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public class AsyncTranslator extends AsyncTask<Object, Integer, String> {
    	
    	protected void onPreExecute()
    	{
    		
    	}


    	@Override
    	protected void onProgressUpdate(Integer... values)
    	{
    		

    	}
    	@Override
    	protected void onPostExecute(String result)
    	{
    		Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
    	}
    	
    	@Override
    	protected String doInBackground(Object... args) {
    		
    		String expression = (String)args[0];
    		Language sourcelanguage =(Language)args[1];
    		Language targetLanguage =(Language)args[2];
    		String retVal;
    		 try {
    			 retVal = GoogleTranslator.TranslateExpression(expression, sourcelanguage, targetLanguage);
    			} catch (Exception e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    				retVal = "Translation Failed";
    				//Toast.makeText(getApplicationContext(), "Failed to send SMS.", Toast.LENGTH_LONG).show();
    			}
    		return retVal;

    	}

    }
    
}