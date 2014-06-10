package com.finalProject.smstranslator;

import java.util.ArrayList;
import android.preference.ListPreference;
import android.preference.PreferenceActivity;
import android.os.Bundle;

public class SettingsActivity extends PreferenceActivity {

	ListPreference _lpFrom;
	ListPreference _lpTo;
	/** Called when the activity is first created. */
	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	
	    addPreferencesFromResource(R.xml.settings);
	    
	    
	    //android:entries="@array/syncFrequency"
	    //android:entryValues="@array/syncFrequencyValues"
		this.InitListPreferences();

	}
	
	@SuppressWarnings("deprecation")
	private void InitListPreferences() {
		this._lpFrom = (ListPreference)findPreference( getResources().getString(R.string.pref_LangFrom_Key));
		this._lpTo = (ListPreference)findPreference( getResources().getString(R.string.pref_LangTo_Key));
		
		ArrayList<Language> langs = new LanguagesHandler().GetLanguages();
		
		String[] names = new String[langs.size()];
		String[] symbols = new String[langs.size()];
		
		for (int i = 0; i < langs.size(); i++) {
			Language currLang = langs.get(i);
			
			names[i] = currLang.getNativeName();
			symbols[i] = currLang.getSymbol();
		}
		
		
		
		this._lpFrom.setEntries(names.clone());
		this._lpFrom.setEntryValues(symbols.clone());
		
		this._lpTo.setEntries(names.clone());
		this._lpTo.setEntryValues(symbols.clone());
		
	}
	
	

}

