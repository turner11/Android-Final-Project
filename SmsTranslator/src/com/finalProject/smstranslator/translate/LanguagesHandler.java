package com.finalProject.smstranslator.translate;

import java.io.IOException;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import com.finalProject.smstranslator.R;
import com.finalProject.smstranslator.activitys.MainActivity;

import android.content.res.Resources;
import android.content.res.XmlResourceParser;


// TODO: Auto-generated Javadoc
/**
 * The Class LanguagesHandler.
 */
public class LanguagesHandler {

	// We don't use namespaces
	/** The Constant ns. */
	private static final String ns = null;

	/**
	 * Gets the languages.
	 *
	 * @return the array list
	 */
	public ArrayList<Language> GetLanguages()  {
		try{
			Resources res = MainActivity.getAppContext().getResources();
			XmlResourceParser languagesXml = res.getXml(R.xml.languages);			

			try {

				//languagesXml.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
				languagesXml.next();
				languagesXml.next();
				return readFeed(languagesXml);
			} finally {
				languagesXml.close();
			}
		}catch (XmlPullParserException ex)
		{
			ex.toString();
		}catch (IOException e) {
			e.toString();
		}
		
		//if we got here, there was an error
		return new ArrayList<Language>();
	}


	/**
	 * Read feed.
	 *
	 * @param parser the parser
	 * @return the array list
	 * @throws XmlPullParserException the xml pull parser exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private ArrayList<Language> readFeed(XmlPullParser parser) throws XmlPullParserException, IOException {
		ArrayList<Language> languages = new ArrayList<Language>();

		parser.require(XmlPullParser.START_TAG, ns, "Languages");
		while (parser.next() != XmlPullParser.END_TAG) {
			if (parser.getEventType() != XmlPullParser.START_TAG) {
				continue;
			}
			String name = parser.getName();
			// Starts by looking for the language tag
			if (name.equals("language")) {
				languages.add(readEntry(parser));
			} else {
				skip(parser);
			}
		}  
		return languages;
	}

	// Parses the contents of an Language. If it encounters an element, hands it off
	// to their respective "read" methods for processing. Otherwise, skips the tag.
	/**
	 * Read entry.
	 *
	 * @param parser the parser
	 * @return the language
	 * @throws XmlPullParserException the xml pull parser exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private Language readEntry(XmlPullParser parser) throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, ns, "language");

		String symbol = null;
		String nativeName = null;
		String englishName = null;	    
		boolean isRightToLeft = false;

		while (parser.next() != XmlPullParser.END_TAG) {
			if (parser.getEventType() != XmlPullParser.START_TAG) {
				continue;
			}
			String elementName = parser.getName();
			if (elementName.equals("Symbol")) {
				symbol = readElement(parser,elementName);
			} else if (elementName.equals("NativeName")) {
				nativeName = readElement(parser,elementName);
			} else if (elementName.equals("EnglishName")) {
				englishName = readElement(parser,elementName);

			} else if (elementName.equals("IsRightToLeft")) {
				String strIsRightToLeft = readElement(parser,elementName);
				isRightToLeft = Boolean.parseBoolean(strIsRightToLeft);
			}else {
				skip(parser);
			}
		}
		Language lang =new Language(symbol,nativeName,englishName);
		lang.setIsRightToLeft(isRightToLeft);
		return lang;
	}

	// Processes title tags in the feed.
	/**
	 * Read element.
	 *
	 * @param parser the parser
	 * @param tagName the tag name
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws XmlPullParserException the xml pull parser exception
	 */
	private String readElement(XmlPullParser parser, String tagName) throws IOException, XmlPullParserException {
		parser.require(XmlPullParser.START_TAG, ns, tagName);
		String title = readText(parser);
		parser.require(XmlPullParser.END_TAG, ns, tagName);
		return title;
	}

	/**
	 * Skip.
	 *
	 * @param parser the parser
	 * @throws XmlPullParserException the xml pull parser exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
		if (parser.getEventType() != XmlPullParser.START_TAG) {
			throw new IllegalStateException();
		}
		int depth = 1;
		while (depth != 0) {
			switch (parser.next()) {
			case XmlPullParser.END_TAG:
				depth--;
				break;
			case XmlPullParser.START_TAG:
				depth++;
				break;
			}
		}
	}

	// For the tags title and summary, extracts their text values.
	/**
	 * Read text.
	 *
	 * @param parser the parser
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws XmlPullParserException the xml pull parser exception
	 */
	private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
		String result = "";
		if (parser.next() == XmlPullParser.TEXT) {
			result = parser.getText();
			parser.nextTag();
		}
		return result;
	}


}


