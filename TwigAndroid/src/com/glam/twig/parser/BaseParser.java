package com.glam.twig.parser;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import oauth.signpost.basic.HttpURLConnectionRequestAdapter;
import oauth.signpost.http.HttpRequest;

import com.glam.twig.Config;
import com.glam.twig.bean.BaseBean;
import com.glam.twig.oauth.TwigLogin;

public abstract class BaseParser {

	public BufferedReader fetch(String url) {
		HttpURLConnection request;
		try {
			System.out.println("$$$$$$$$$$$$$ URL :: " + url);
			URL mainURL = new URL(url);
			request = (HttpURLConnection) mainURL.openConnection();
			Config.globalconsumer.setTokenWithSecret(TwigLogin.tokenValue,
					TwigLogin.tokenSecret);
			HttpRequest req = Config.globalconsumer.sign(request);
			HttpURLConnection con = ((HttpURLConnectionRequestAdapter) req)
					.unwrap();
			con.connect();
			int responseCode = con.getResponseCode();
			if (responseCode == 200) {
				InputStream is = con.getInputStream();
				BufferedReader in = new BufferedReader(new InputStreamReader(
						is, Config.UTF8));

				return in;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public abstract ArrayList<? extends BaseBean> parseForList(BufferedReader in, int method);

	public abstract boolean parseForBoolean(BufferedReader in);

	public abstract BaseBean parseForBean(BufferedReader in);

}
