package com.finalProject.smstranslator;

import android.os.AsyncTask;
import android.widget.Toast;

public class AsyncTranslator extends AsyncTask<Object, Integer, String> {

	public static final boolean DEV_MODE = true;
	
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
		Toast.makeText(MainActivity.getAppContext(), result, Toast.LENGTH_LONG).show();
	}

	@Override
	protected String doInBackground(Object... args) {

		String expression = (String)args[0];
		/*Language sourcelanguage =(Language)args[1];
		Language targetLanguage =(Language)args[2];*/
		
		String sourcelanguageSymbol =(String )args[1];
		String targetLanguageSymbol =(String )args[2];
		
		String retVal;
		try {
			retVal = GoogleTranslator.TranslateExpression(expression, sourcelanguageSymbol, targetLanguageSymbol);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			retVal = "Translation Failed";

			if (DEV_MODE) {
				retVal = retVal +" " + e.getMessage();
			}
		}
		return retVal;

	}

}