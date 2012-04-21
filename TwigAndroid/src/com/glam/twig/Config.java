package com.glam.twig;

import java.nio.charset.Charset;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.basic.DefaultOAuthProvider;

public class Config {
	public static final Charset UTF8 = Charset.forName("UTF-8");

	public static final OAuthConsumer globalconsumer = new DefaultOAuthConsumer(
			"KUrJpK7Ru7lSlh8BHFHOcA",
			"QuzfJqqtdHnEF3IlDZFNBQdd3klZGqvFjqvL7qQAJI");// KUrJpK7Ru7lSlh8BHFHOcA
															// ,
															// QuzfJqqtdHnEF3IlDZFNBQdd3klZGqvFjqvL7qQAJI
	public static final OAuthProvider globalprovider = new DefaultOAuthProvider(
			"http://twitter.com/oauth/request_token",
			"http://twitter.com/oauth/access_token",
			"http://twitter.com/oauth/authorize");

	public static final String flurryID = "LKAHAIVDVGFL57HDHLG1";// office
	public static final String PREFS_FILE = "glamtwig_pref";

}
