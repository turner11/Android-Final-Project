package com.finalProject.smstranslator;

import android.os.AsyncTask;

/**
 * 
 * A class for getting a translation in am asynchronous manner
 *
 */
public class AsyncTranslator extends AsyncTask<Object, Integer, String> {

	public static final boolean DEV_MODE = true;
	
	IOnTranslationCompleted _onTranslationCompleted;
	
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
		this._onTranslationCompleted.onTranslationCompleted(result);		
	}

	@Override
	protected String doInBackground(Object... args) {
		/*The convention of location of arguments in this application:
		 * 0 Expression to translate
		 * 1 The language symbol to translate from.
		 * 2 The language symbol to translate to.
		 * 3 An instance of IOnTranslationCompleted
		 */
		String expression = (String)args[0];
				
		String sourcelanguageSymbol =(String )args[1];
		String targetLanguageSymbol =(String )args[2];
		this._onTranslationCompleted = (IOnTranslationCompleted)args[3];
		
		String retVal;
		try {
			retVal = GoogleTranslator.TranslateExpression(expression, sourcelanguageSymbol, targetLanguageSymbol);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			retVal = "Translation Failed";

			if (DEV_MODE) {
				retVal = retVal +": " + e.getMessage();
			}
		}
		return retVal;

	}

}
