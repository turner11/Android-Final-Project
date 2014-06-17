package com.finalProject.smstranslator;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

// TODO: Auto-generated Javadoc
/**
 * The Class ContactHelper.
 */
public class ContactHelper {

	/** The context. */
	private Context context;
	
	/**
	 * Instantiates a new contact helper.
	 *
	 * @param context the context
	 */
	public ContactHelper(Context context) {
		this.context = context;
	}
	
	/**
	 * Gets the contact name.
	 *
	 * @param number the number
	 * @return the contact name
	 */
	public String getContactName(String number) {

		String name = null;

		// define the columns I want the query to return
		String[] projection = new String[] {
				ContactsContract.PhoneLookup.DISPLAY_NAME,
				ContactsContract.PhoneLookup._ID};

		// encode the phone number and build the filter URI
		Uri contactUri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(number));

		// query time
		Cursor cursor = context.getContentResolver().query(contactUri, projection, null, null, null);

		if(cursor != null) {
			if (cursor.moveToFirst()) {
				name =  cursor.getString(cursor.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME));
			} 
		}
		return name;
	}
}
