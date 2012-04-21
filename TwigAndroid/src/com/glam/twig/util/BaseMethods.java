package com.glam.twig.util;

import android.content.Context;
import android.net.ConnectivityManager;

public class BaseMethods {

	public static boolean CheckInternet(Context context) {
		ConnectivityManager connec = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		android.net.NetworkInfo wifi = connec
				.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		android.net.NetworkInfo mobile = connec
				.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

		if (wifi.isConnected()) {
			return true;
		} else if (!mobile.isConnected()) {
			return false;
		} else if (mobile.isConnected()) {
			return true;
		}
		return false;
	}

}
