package com.sigma.appium.core;

import java.net.MalformedURLException;
import java.net.URL;

/*
 * Wraps the information needed about the appium server, info such as IP address and port number.
 * In case of parallel testing there could be a need for creating different instances for the server.
 * */

public class Server {


	String url = "http://0.0.0.0:4723/wd/hub";

	public Server(String serverUrl) {
		if (serverUrl != null)
			url = serverUrl;
	}

	public URL getUrl() {
		URL uri = null;
		try {
			uri = new URL(url);
		} catch (MalformedURLException e) {
			System.out.println("Failed to create url");
			e.printStackTrace();
		}
		return uri;
	}

}
