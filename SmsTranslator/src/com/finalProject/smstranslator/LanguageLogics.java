package com.finalProject.smstranslator;


import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.telephony.TelephonyManager;

/**
 * 
 * A class for handling retrieval of default languages
 *
 */
public class LanguageLogics {
	/**
	 * Gets the language symbol for the device's default language (e.g. for English: en)
	 * @return the language symbol for the device's default language 
	 */
	public static String getDeviceLanguageSymbol()
	{
		Locale lcl = Locale.getDefault();
		String langSymbol = lcl.getLanguage();
		return langSymbol;
	}

	/**
	 * Gets the language symbol for current location's language(e.g. for English: en)
	 * Will first attempt to get it from GPS
	 * If it fails it will attempt to get from SIM carrier.
	 * If this fails, It will take it from the Location settings
	 * @return the language symbol for the current location's language 
	 */
	public static String getLocalLanguageSymbol()
	{
		String langSymbol = null;

		//get from GPS
		langSymbol = LanguageLogics.GetLocationFromGps();
		if (langSymbol == null) {
			//Fall back to sim carrier
			langSymbol = LanguageLogics.getCountryBySimCarrier();
		}	
		//fall back to preferences
		if (langSymbol == null) {
			langSymbol = LanguageLogics.GetLanguageFromLocationSettings();
		}

		return langSymbol;
	}

	@SuppressLint("InlinedApi")
	private static String GetLocationFromGps() {

		String langSymbol = null;
		try {


			Context context = MainActivity.getAppContext();	
			LocationManager locMgr = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
			Criteria criteria = new Criteria();
			criteria.setAccuracy(Criteria.ACCURACY_LOW);
			criteria.setAltitudeRequired(false);
			criteria.setPowerRequirement(Criteria.POWER_MEDIUM);
			criteria.setCostAllowed(false);

			String bestProvider= locMgr.getBestProvider(criteria, false);


			Location LastKnownLocation = locMgr.getLastKnownLocation(bestProvider);
			if (LastKnownLocation != null) {


				Context baseContext = MainActivity.getAppContext();
				List<Address> addresses = null;
				Geocoder geoCoder = new Geocoder(baseContext, Locale.getDefault());

				addresses = geoCoder.getFromLocation(LastKnownLocation.getLatitude(), LastKnownLocation.getLongitude(), 1);

				if (addresses != null && addresses.size() > 0) {
					Address address = addresses.get(0);
					String countrySymbol = address.getCountryCode();
					langSymbol  = LanguageLogics.GetLanguageSymbolByCountryCode(countrySymbol);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return langSymbol;
	}

	private static String getCountryBySimCarrier() {
		Context context = MainActivity.getAppContext();	

		TelephonyManager tm = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE); 
		String countryCode = tm.getSimCountryIso(); 
		String langSymbol = LanguageLogics.GetLanguageSymbolByCountryCode(countryCode);
		return langSymbol;
	}

	/**
	 * Gets the language from location settings.
	 * 
	 * @return the string
	 */
	private static String GetLanguageFromLocationSettings() {
		Context context = MainActivity.getAppContext();
		Locale locale = context.getResources().getConfiguration().locale;
		//String contry = locale.getCountry();
		String langSymbol = locale.getLanguage();
		return langSymbol;
	}

	/**
	 * Gets the language symbol by country code.
	 * 
	 * @param countryCode
	 *            the country code
	 * @return the language symbol
	 */
	private static String GetLanguageSymbolByCountryCode(String countryCode) {
		Map<String, String> map = new HashMap<String, String>(){/**
		 * 
		 */
			private static final long serialVersionUID = -3675404406759526058L;

			{
				put("ZA","af");
				put("ET","am");
				put("AE","ar");
				put("BH","ar");
				put("DZ","ar");
				put("EG","ar");
				put("IQ","ar");
				put("JO","ar");
				put("KW","ar");
				put("LB","ar");
				put("LY","ar");
				put("MA","ar");                 
				put("OM","ar");
				put("QA","ar");
				put("SA","ar");
				put("SY","ar");
				put("TN","ar");
				put("YE","ar");                 
				put("AZ","az");                
				put("RU","ba");
				put("BY","be");
				put("BG","bg");
				put("BD","bn");
				put("IN","bn");
				put("CN","bo");
				put("FR","br");
				put("BA","bs");                 
				put("ES","ca");
				put("FR","co");
				put("CZ","cs");
				put("GB","cy");
				put("DK","da");
				put("AT","de");
				put("CH","de");
				put("DE","de");
				put("LI","de");
				put("LU","de");                
				put("MV","dv");
				put("GR","el");                
				put("AU","en");
				put("BZ","en");
				put("CA","en");
				put("GB","en");
				put("IE","en");
				put("IN","en");
				put("JM","en");
				put("MY","en");
				put("NZ","en");                
				put("SG","en");
				put("TT","en");
				put("US","en");
				put("ZA","en");
				put("ZW","en");
				put("AR","es");
				put("BO","es");
				put("CL","es");
				put("CO","es");
				put("CR","es");
				put("DO","es");
				put("EC","es");
				put("ES","es");
				put("GT","es");
				put("HN","es");
				put("MX","es");
				put("NI","es");
				put("PA","es");
				put("PE","es");
				put("PR","es");
				put("PY","es");
				put("SV","es");
				put("US","es");
				put("UY","es");
				put("VE","es");
				put("EE","et");
				put("ES","eu");
				put("IR","fa");
				put("FI","fi");                 
				put("FO","fo");
				put("BE","fr");
				put("CA","fr");
				put("CH","fr");
				put("FR","fr");
				put("LU","fr");
				put("MC","fr");
				put("NL","fy");
				put("IE","ga");
				put("GB","gd");
				put("ES","gl");  
				put("NG","ha");
				put("IL","he");
				put("IN","hi");
				put("BA","hr");
				put("HR","hr");                
				put("HU","hu");
				put("AM","hy");
				put("ID","id");
				put("NG","ig");
				put("CN","ii");
				put("IS","is");
				put("CH","it");
				put("IT","it");
				put("CA","iu");                
				put("JP","ja");
				put("GE","ka");
				put("KZ","kk");
				put("GL","kl");
				put("KH","km");  
				put("KR","ko");
				put("KG","ky");
				put("LU","lb");
				put("LA","lo");
				put("LT","lt");
				put("LV","lv");
				put("NZ","mi");
				put("MK","mk");                 
				put("MN","mn");  
				put("BN","ms");
				put("MY","ms");
				put("MT","mt");
				put("NO","nb");
				put("NP","ne");
				put("BE","nl");
				put("NL","nl");                                 
				put("FR","oc");
				put("PL","pl");
				put("AF","ps");
				put("BR","pt");
				put("PT","pt");  
				put("CH","rm");
				put("RO","ro");
				put("RU","ru");
				put("RW","rw");                                
				put("FI","se");                 
				put("SE","se");
				put("LK","si");
				put("SK","sk");
				put("SI","sl");   
				put("AL","sq");                
				put("FI","sv");
				put("SE","sv");
				put("KE","sw");                 
				put("TH","th");
				put("TM","tk");
				put("ZA","tn");
				put("TR","tr");
				put("RU","tt");              
				put("CN","ug");
				put("UA","uk");
				put("PK","ur");                 
				put("VN","vi");
				put("SN","wo");
				put("ZA","xh");
				put("NG","yo");
				put("CN","zh");
				put("HK","zh");
				put("MO","zh");
				put("SG","zh");
				put("TW","zh");
				put("ZA","zu");
				put("PH","fil");
			}};

			String countryCodeUpper = countryCode.toUpperCase();
			String symbol = null;
			if (map.containsKey(countryCodeUpper)) {
				symbol = map.get(countryCodeUpper);
			}

			return symbol;
	}
}
