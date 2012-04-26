package com.glam.twig.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.widget.TextView;

public class JavaScriptInterface
{
	private static Activity context;
	private static TextView textView;
	static String pin = "empty";

	public static String getPin()
	{
		return pin;
	}

	public static void setPin(final String pin)
	{
		
		JavaScriptInterface.pin = pin;
		context.runOnUiThread(new Runnable() {
			
			@Override
			public void run()
			{
				textView.setText("Login Page");
			}
		});
	}

	public void showHTML(String html)
	{
		if (html != null)
		{
			String s = getPinFromHtml(html);
			setPin(s);
		}
	}

	private String getPinFromHtml(String html)
	{
		String s = "";

		Pattern p = Pattern.compile("<code>(.*?)</code>", Pattern.DOTALL);
		Matcher m = p.matcher(html);
		if (m.find())
		{
			s = m.group(1);
		}
		return s;

	}

	public void setContext(Activity context)
	{
		this.context = context;
		// TODO Auto-generated method stub
		
	}

	public void setTextView(TextView view)
	{
		textView = view;
		// TODO Auto-generated method stub
		
	}

}
