package com.glam.twig.parser;

import com.glam.twig.ActionTypes;
import com.glam.twig.parser.JSON.TimeLineJSONParser;


public class TwigParserFactory {
	public static BaseParser getJSONParser(ActionTypes type) {
		switch (type) {

		case HOME:
			return new TimeLineJSONParser();
			
		}
		return null;
	}

	public static BaseParser getXMLParser(XMLParserType type) {
		return null;
	}

}
