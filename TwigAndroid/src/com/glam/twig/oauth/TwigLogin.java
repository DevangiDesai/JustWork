package com.glam.twig.oauth;

import com.glam.twig.Config;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class TwigLogin {

	public static final String USERNAME = "TW_USERNAME";
	public static final String EMPTY = "";
	public static final String TOKEN_VALUE = "XAUTH_TOKEN_VALUE";
	public static final String TOKEN_SECRET = "XAUTH_TOKEN_SECRET";
	public static final String TOKEN_PIN = "XAUTH_TOKEN_PIN";
	private static String consumerKey = "KUrJpK7Ru7lSlh8BHFHOcA";
	private static String consumerSecret = "QuzfJqqtdHnEF3IlDZFNBQdd3klZGqvFjqvL7qQAJI";
	private static TwigLogin instance = null;
	private String userName;
	public static String tokenValue;
	public static String tokenSecret;
	private String url;
	private String pin;

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	private TwigLogin() {

	}

	public static TwigLogin getInstance() {
		if (instance == null)
			instance = new TwigLogin();
		return instance;
	}

	public void setUsername(String userName) {
		this.userName = userName;

	}

	public void setTokenValue(String tokenValue) {
		this.tokenValue = tokenValue;

	}

	public void setTokenSecret(String tokenSecret) {
		this.tokenSecret = tokenSecret;

	}

	public String getUserName() {
		return userName;
	}

	public String getTokenValue() {
		return tokenValue;
	}

	public String getTokenSecret() {
		return tokenSecret;
	}

	public static String getConsumerKey() {
		// TODO Auto-generated method stub
		return consumerKey;
	}

	public static String getConsumerSecret() {
		// TODO Auto-generated method stub
		return consumerSecret;
	}

	public void saveTokenToPreference(Context context) {
		Editor prefEditor = context.getSharedPreferences(Config.PREFS_FILE,
				Context.MODE_PRIVATE).edit();
		prefEditor.putString(TwigLogin.USERNAME, getInstance().getUserName());
		prefEditor.putString(TwigLogin.TOKEN_VALUE, getInstance()
				.getTokenValue());
		prefEditor.putString(TwigLogin.TOKEN_SECRET, getInstance()
				.getTokenSecret());
		prefEditor.commit();
	}

	public void saveTokenToPreferenceOAuth(Context context) {
		Editor prefEditor = context.getSharedPreferences(Config.PREFS_FILE,
				Context.MODE_PRIVATE).edit();
		prefEditor.putString(TwigLogin.TOKEN_PIN, getInstance().getPin());
		prefEditor.putString(TwigLogin.TOKEN_VALUE, getInstance()
				.getTokenValue());
		prefEditor.putString(TwigLogin.TOKEN_SECRET, getInstance()
				.getTokenSecret());
		prefEditor.commit();
	}

	public boolean performAutoLoginOAuth(Context context) {
		SharedPreferences prefStorage = context.getSharedPreferences(
				Config.PREFS_FILE, Context.MODE_PRIVATE);
		String tokenPin = prefStorage.getString(TwigLogin.TOKEN_PIN,
				TwigLogin.EMPTY);
		if (tokenPin.equals(TwigLogin.EMPTY)) {
			return false;
		} else {
			return true;
		}
	}

	public String getAutoLoginOAuth(Context context) {
		SharedPreferences prefStorage = context.getSharedPreferences(
				Config.PREFS_FILE, Context.MODE_PRIVATE);
		String tokenPin = prefStorage.getString(TwigLogin.TOKEN_PIN,
				TwigLogin.EMPTY);
		tokenValue = prefStorage.getString(TwigLogin.TOKEN_VALUE,
				TwigLogin.EMPTY);
		tokenSecret = prefStorage.getString(TwigLogin.TOKEN_SECRET,
				TwigLogin.EMPTY);
		return tokenPin;
	}

	public boolean dologinOAuth(Context context, boolean saveRequired,
			String OAuthPin) {
		try {
			if (saveRequired) {
				setTokenValue(Config.globalconsumer.getToken());
				setTokenSecret(Config.globalconsumer.getTokenSecret());
				tokenValue = Config.globalconsumer.getToken();
				tokenSecret = Config.globalconsumer.getTokenSecret();

				setPin(OAuthPin);

				saveTokenToPreferenceOAuth(context);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
}
