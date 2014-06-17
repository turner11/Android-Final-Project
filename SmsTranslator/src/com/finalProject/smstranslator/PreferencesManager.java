package com.finalProject.smstranslator;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.finalProject.smstranslator.activitys.MainActivity;
import com.finalProject.smstranslator.translate.LanguageLogics;

// TODO: Auto-generated Javadoc
/**
 * The Class PreferencesManager.
 */
public class PreferencesManager {

	/** The _prefs. */
	static SharedPreferences _prefs;
	
	/** The _context. */
	static Context _context;
	static
	{
		_context = MainActivity.getAppContext();
		_prefs = PreferenceManager.getDefaultSharedPreferences(_context);
	}
	
	/**
	 * Gets the language to.
	 *
	 * @return the language to
	 */
	public static String getLanguageTo() {
		boolean autoChooseTo  = _prefs.getBoolean(_context.getResources().getString(R.string.pref_Auto_LangTo_Key), true);
		String symbolTo;
		if (autoChooseTo) 
			//getting "To" language from device setting
			symbolTo = LanguageLogics.getLocalLanguageSymbol();
		else	
			//getting "To" language from preferences
			symbolTo = _prefs.getString(_context.getResources().getString(R.string.pref_LangTo_Key), "en");
		return symbolTo;
	}

	/**
	 * Gets the language from.
	 *
	 * @return the language from
	 */
	public static String getLanguageFrom() {
		boolean autoChooseFrom  = _prefs.getBoolean(_context.getResources().getString(R.string.pref_Auto_LangFromKey), true);
		String symbolFrom;
		if (autoChooseFrom) 
			//getting "From" language from device setting
			symbolFrom = LanguageLogics.getDeviceLanguageSymbol();				
		else
			//getting "From" language from preferences
			symbolFrom = _prefs.getString(_context.getResources().getString(R.string.pref_LangFrom_Key), "en");
		return symbolFrom;
	}
}
