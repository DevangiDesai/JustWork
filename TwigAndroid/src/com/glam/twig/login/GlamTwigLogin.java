package com.glam.twig.login;

import java.util.ArrayList;

import oauth.signpost.OAuth;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.exception.OAuthNotAuthorizedException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

import com.glam.twig.AbstractActivity;
import com.glam.twig.Config;
import com.glam.twig.R;
import com.glam.twig.bean.BaseBean;
import com.glam.twig.home.HomeTimeline;
import com.glam.twig.oauth.TwigLogin;
import com.glam.twig.util.BaseMethods;
import com.glam.twig.util.JavaScriptInterface;

public class GlamTwigLogin extends AbstractActivity {
	private static String authUrl;
	private WebView mWebView;
	private JavaScriptInterface jsInterface;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		RelativeLayout menuContainer = (RelativeLayout) findViewById(R.id.menuContainer);
		RelativeLayout mainContainer = (RelativeLayout) findViewById(R.id.mainContainer);
		View v = View.inflate(this, R.layout.login, mainContainer);
		mWebView = (WebView) v.findViewById(R.id.webview);
		if (TwigLogin.getInstance().performAutoLoginOAuth(this)) {
			TwigLogin.getInstance().getAutoLoginOAuth(this);
			InitActivity();
			finish();
		}

		if (BaseMethods.CheckInternet(GlamTwigLogin.this)) {
			getRequestToken();
		}
		mWebView.requestFocus(View.FOCUS_DOWN);
		WebSettings mWebSettings = mWebView.getSettings();
		mWebSettings.setSavePassword(false);
		CookieManager cookieManager = CookieManager.getInstance();
		cookieManager.setAcceptCookie(false);

		// mWebView.setBackgroundColor(12);
		mWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
		mWebView.clearHistory();
		mWebView.clearFormData();
		mWebView.clearCache(true);
		mWebView.getSettings().setJavaScriptEnabled(true);
		jsInterface = new JavaScriptInterface();
		jsInterface.setContext(this);
		mWebView.addJavascriptInterface(jsInterface, "HTMLOUT");
		mWebView.setWebViewClient(new MyWebViewClient());
		mWebView.setWebChromeClient(new MyWebChromeClient());

		if (BaseMethods.CheckInternet(this)) {
			System.out.println("In webview");
			mWebView.loadUrl(authUrl);
		}

		super.onCreate(savedInstanceState);
	}

	@Override
	protected void setScreenData(ArrayList<? extends BaseBean> result) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setDataClick(int id) {
		// TODO Auto-generated method stub
	}

	public static void getRequestToken() {
		try {
			authUrl = Config.globalprovider.retrieveRequestToken(
					Config.globalconsumer, OAuth.OUT_OF_BAND);
			System.out.println(authUrl);
		} catch (OAuthMessageSignerException e) {
			e.printStackTrace();
		} catch (OAuthNotAuthorizedException e) {
			e.printStackTrace();
		} catch (OAuthExpectationFailedException e) {
			e.printStackTrace();
		} catch (OAuthCommunicationException e) {
			e.printStackTrace();
		}
	}

	public static void getAccessToken(String pin) {
		try {
			Config.globalprovider.retrieveAccessToken(Config.globalconsumer,
					pin);

		} catch (OAuthMessageSignerException e) {
			e.printStackTrace();
		} catch (OAuthNotAuthorizedException e) {
			e.printStackTrace();
		} catch (OAuthExpectationFailedException e) {
			e.printStackTrace();
		} catch (OAuthCommunicationException e) {
			e.printStackTrace();
		}
	}

	private class MyWebViewClient extends WebViewClient {

		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
		}

		@Override
		public void onPageFinished(WebView view, String url) {
			if (url.equals("https://twitter.com/oauth/authorize")) {
				mWebView.loadUrl("javascript:alert(document.getElementsByTagName('code')[0].innerHTML)");
			}
		}

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}

		public void onReceivedSslError(WebView view, SslErrorHandler handler,
				SslError error) {
			handler.proceed();
		}
	}

	final class MyWebChromeClient extends WebChromeClient {
		public boolean onJsAlert(WebView view, String url, String message,
				JsResult result) {
			JavaScriptInterface.setPin(message);
			getAccessToken(JavaScriptInterface.getPin());
			result.confirm();
			return true;
		}
	}

	public void InitActivity() {
		finish();
		Intent intent = null;
		intent = new Intent(this, HomeTimeline.class);
		startActivity(intent);
	}

}
