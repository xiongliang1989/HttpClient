package com.xl.crawl;

import java.net.URI;
import java.util.LinkedList;

import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class URLCrawl {
	
	LinkedList<String> alllists = new LinkedList<String>();

	public void crawlURL() throws Exception{
		alllists = crawlCurrentURL("www.baidu.com");
	}
	
	public LinkedList<String> crawlCurrentURL(String url) throws Exception {

		// 创建HttpClient对象
		CloseableHttpClient httpclient = HttpClients.createDefault();

		// 通过URIBuilder类创建URI
		URI uri = new URIBuilder().setScheme("http").setHost(url).build();

		HttpGet get = new HttpGet(uri); // 使用Get方法提交

		// 请求的参数配置，分别设置连接池获取连接的超时时间，连接上服务器的时间，服务器返回数据的时间
		RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(3000).setConnectTimeout(3000)
				.setSocketTimeout(3000).build();

		// 配置信息添加到Get请求中
		get.setConfig(config);
		// 通过httpclient的execute提交 请求 ，并用CloseableHttpResponse接受返回信息
		CloseableHttpResponse response = httpclient.execute(get);

		String content = EntityUtils.toString(response.getEntity(), "utf-8");

		// 使用Jsoup解析网页内容
		Document document = Jsoup.parse(content);

		LinkedList<String> lists = new LinkedList<String>();
		Elements imgs = document.select("img");
		for (Element element : imgs) {
			lists.add(element.attr("src"));
		}

		Elements as = document.select("a");
		for (Element element : as) {
			lists.add(element.attr("href"));
		}

		for (String list : lists) {
			System.out.println(list);
		}
		
		return lists;
	}
	
	public void print(){
		for (String list : alllists) {
			System.out.println(list);
		}
	}
}
