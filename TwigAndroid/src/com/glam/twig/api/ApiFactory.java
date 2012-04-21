package com.glam.twig.api;

public class ApiFactory {
	public static GlamApi getApi(ApiTypes type) {
		switch (type) {
		case TWIG:
			return TwigApi.getInstance();
		case FOURSQUARE:


		}
		return null;
	}
}
