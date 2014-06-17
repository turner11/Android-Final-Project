package com.finalProject.smstranslator.translate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

// TODO: Auto-generated Javadoc
/**
 * 
 * A class for retrieving translation from google translate web service.
 *
 */
public class GoogleTranslator {


	/**   	 The template for translation requests from google. */
	final static String GOOGLE_TRANSLATE_URL_TEMPLATE = //"http://translate.google.com/translate_a/t?client=p&hl=%s&sl=%s&tl=%s&ie=UTF-8&oe=UTF-8&multires=1&oc=2&otf=1&ssel=0&tsel=0&pc=1&sc=1&q=%s";
	"http://translate.google.com.tw/translate_a/t?client=t&hl=en&sl=%s&tl=%s&ie=UTF-8&oe=UTF-8&multires=1&oc=1&otf=2&ssel=0&tsel=0&sc=1&q=%s";

	//"http://translate.google.com/translate_a/t?client=t&hl=iw&sl=auto&tl=iw&ie=UTF-8&oe=UTF-8&multires=1&oc=1&prev=conf&psl=en&ptl=iw&otf=1&it=sel.8772&ssel=3&tsel=6&uptl=iw&alttl=en&sc=1&q=dog"

	//"http://translate.google.com/translate_a/t?client=p&text={0}&hl={1}&sl=en&tl={2}&ie=UTF-8&oe=UTF-8&multires=1&otf=2&ssel=2&tsel=2&sc=1";  

	/**
	 *  
	 * 	 Translates a sentence.
	 *
	 * @param expression The sentence.
	 * @param languageFromSymbol the language from symbol
	 * @param languageToSymbol the language to symbol
	 * @return The translation
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws URISyntaxException the URI syntax exception
	 */
	public static String TranslateExpression(String expression, String languageFromSymbol, String languageToSymbol) throws IOException, URISyntaxException 
	{
	
		String urlStr = GoogleTranslator.GetUrlBySymbols(expression, languageFromSymbol, languageToSymbol);
		URL url = new URL(urlStr);
		
		URLConnection urlConnection = url.openConnection();
		urlConnection.setRequestProperty("User-Agent", "Something Else");
		
		InputStream inputStream = urlConnection.getInputStream();
		InputStreamReader inputreader= new InputStreamReader(inputStream);
		BufferedReader br = new BufferedReader(inputreader);
		
		String result = br.readLine();
		br.close();

		String	trans = GetTranslationFromReply(result);


		return trans;
	}

	/**
	 *  
	 * 	 Gets the translation from googles reply.
	 *
	 * @param reply Googls reply.
	 * @return the string
	 */
	private static String GetTranslationFromReply(String reply)
	{
		String result = reply.substring(2, reply.indexOf("]]") + 1);
		StringBuilder sb = new StringBuilder();
		String[] splits = result.split("(?<!\\\\)\"");
		for(int i = 1; i < splits.length; i += 8)
			sb.append(splits[i]);
		String translation =  sb.toString().replace("\\n", "\n").replaceAll("\\\\(.)", "$1");
		return translation;
	}

	/**
	 *  
	 * Gets the URL by symbols.
	 *
	 * @param expression The expression to translate.
	 * @param sourceSymbol The symbol of source language.
	 * @param targetSymbol The symbol of to source language.
	 * @return the url
	 * @throws URISyntaxException the URI syntax exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private static String GetUrlBySymbols(String expression, String sourceSymbol, String targetSymbol) throws URISyntaxException, IOException
	{
		// for a parameter called SL in the URL...
		//String slParam = sourceSymbol == null || sourceSymbol.trim() == "" ? "auto" : sourceSymbol;

		//this is for passing the expression in encodeURIcomponent format for non English expressions
		//String encodedSentene = Uri.EscapeUriString(expression);
		
		
		//String url = String.format(GoogleTranslator.GOOGLE_TRANSLATE_URL_TEMPLATE, sourceSymbol, slParam, targetSymbol, expression);
		String encodedExpression = URLEncoder.encode(expression, "UTF-8");
		String url = String.format(GoogleTranslator.GOOGLE_TRANSLATE_URL_TEMPLATE, sourceSymbol, targetSymbol, encodedExpression);
		//URL tempURL = new URL(url);
		
		//String encodedUrl = tempURL.toURI().toString();		
		return url;
	}

}
