package com.finalProject.smstranslator.activitys;

import java.util.ArrayList;

import com.finalProject.smstranslator.translate.Language;
import com.finalProject.smstranslator.translate.LanguagesHandler;
import com.finalProject.smstranslator.R;

import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;

// TODO: Auto-generated Javadoc
/**
 * The Class SettingsActivity.
 */
public class SettingsActivity extends PreferenceActivity implements OnSharedPreferenceChangeListener{

	/** The _lp from. */
	ListPreference _lpFrom;
	
	/** The _lp to. */
	ListPreference _lpTo;
	
	/**
	 *  Called when the activity is first created.
	 *
	 * @param savedInstanceState the saved instance state
	 */
	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	
	    addPreferencesFromResource(R.xml.settings);
	    
	    
	    //android:entries="@array/syncFrequency"
	    //android:entryValues="@array/syncFrequencyValues"
		this.InitListPreferences();
		this.getPreferenceScreen().getSharedPreferences()
	      .registerOnSharedPreferenceChangeListener(this);
	    

	}
	
	/**
	 * Initializes the list of preferences.
	 */
	@SuppressWarnings("deprecation")
	private void InitListPreferences() {
		this._lpFrom = (ListPreference)findPreference( getResources().getString(R.string.pref_LangFrom_Key));
		this._lpTo = (ListPreference)findPreference( getResources().getString(R.string.pref_LangTo_Key));
		
		ArrayList<Language> langs = new LanguagesHandler().GetLanguages();
		
		String[] names = new String[langs.size()];
		String[] symbols = new String[langs.size()];
		
		for (int i = 0; i < langs.size(); i++) {
			Language currLang = langs.get(i);
			SharedPreferences prefs =  PreferenceManager.getDefaultSharedPreferences(MainActivity.getAppContext());
			boolean getNativeName =prefs.getBoolean( getResources().getString(R.string.pref_Native_Name_Key), false); 
			names[i] = getNativeName ? currLang.getNativeName(): currLang.getEnglishName();
			symbols[i] = currLang.getSymbol();
		}
		
		
		
		this._lpFrom.setEntries(names.clone());
		this._lpFrom.setEntryValues(symbols.clone());
		this._lpFrom.setSummary(this._lpFrom.getEntry());
		
		this._lpTo.setEntries(names.clone());
		this._lpTo.setEntryValues(symbols.clone());
		this._lpTo.setSummary(this._lpTo.getEntry());
		
	}

	/* (non-Javadoc)
	 * @see android.content.SharedPreferences.OnSharedPreferenceChangeListener#onSharedPreferenceChanged(android.content.SharedPreferences, java.lang.String)
	 */
	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,String key) {
		@SuppressWarnings("deprecation")
		Preference pref = findPreference(key);

	    if (pref instanceof ListPreference) {
	        ListPreference listPref = (ListPreference) pref;
	        pref.setSummary(listPref.getEntry());
	    }else if(key == getResources().getString(R.string.pref_Native_Name_Key))
	    {
	    	InitListPreferences();
	    }
	}
	
	
	

}


