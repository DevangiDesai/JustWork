package com.glam.twig.api;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;

import com.glam.twig.ActionTypes;
import com.glam.twig.bean.BaseBean;
import com.glam.twig.parser.BaseParser;
import com.glam.twig.parser.TwigParserFactory;
import com.glam.twig.parser.JSONParserType;

public class TwigApi extends GlamApi {

	private static TwigApi instance = null;

	private TwigApi() {

	}

	public static TwigApi getInstance() {
		if (instance == null)
			instance = new TwigApi();
		return instance;
	}

	public ArrayList<? extends BaseBean> doAction(ActionTypes actionTypes,
			HashMap<String, String> params, int method) {
		String url = "";
		switch (actionTypes) {
		case HOME:
			url = ApiStrings.HOME_URL;
			return getData(url, ActionTypes.HOME, method);
		case MENTION:
		case MESSAGE:
		}
		return null;
	}

	public ArrayList<? extends BaseBean> getData(String url,
			ActionTypes actionTypes, int method) {
		BaseParser parser = TwigParserFactory.getJSONParser(actionTypes);
		BufferedReader in = parser.fetch(url);
		return (ArrayList<? extends BaseBean>) parser.parseForList(in, method);
	}
}
