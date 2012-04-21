package com.glam.twig.login;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.RelativeLayout;


import com.glam.twig.AbstractActivity;
import com.glam.twig.R;
import com.glam.twig.bean.BaseBean;

public class GlamTwigLogin extends AbstractActivity {
	private WebView mWebView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		RelativeLayout menuContainer = (RelativeLayout) findViewById(R.id.menuContainer);
		RelativeLayout mainContainer = (RelativeLayout) findViewById(R.id.mainContainer);
		View.inflate(this, R.layout.login, mainContainer);
		mWebView = (WebView) findViewById(R.id.webview);

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

}
