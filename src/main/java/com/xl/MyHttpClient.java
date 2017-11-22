package com.xl;

import com.xl.crawl.URLCrawl;

public class MyHttpClient {

	public static void main(String[] args) throws Exception {

		URLCrawl uRLCrawl = new URLCrawl();
		uRLCrawl.crawlURL();
		uRLCrawl.print();
	}

}
