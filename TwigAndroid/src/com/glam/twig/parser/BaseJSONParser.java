package com.glam.twig.parser;

import java.io.BufferedReader;
import java.util.ArrayList;

import com.glam.twig.bean.BaseBean;

public abstract class BaseJSONParser extends BaseParser {

	/*
	 * (non-Javadoc)
	 * @see com.glam.ippin.parser.BaseParser#parseForBoolean(java.io.BufferedReader)
	 * 
	 */
	@Override
	public boolean parseForBoolean(BufferedReader in) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * Parses the JSON data and returns an ArrayList of BaseIppinBean
	 * (non-Javadoc)
	 * @see com.glam.ippin.parser.BaseParser#parseForList(java.io.BufferedReader)
	 * @param in - pass a BufferedReader
	 */

	@Override
	public ArrayList<? extends BaseBean> parseForList(BufferedReader in, int method) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseBean parseForBean(BufferedReader in) {
		// TODO Auto-generated method stub
		return null;
	}

}
