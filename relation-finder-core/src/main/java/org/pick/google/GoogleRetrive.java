package org.pick.google;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import com.google.gson.Gson;

public class GoogleRetrive {
	public String getRelatedConURL(String qeury) throws IOException {
		String google = "http://ajax.googleapis.com/ajax/services/search/web?v=1.0&num=20&q=";
		String search = qeury;
		String charset = "UTF-8";

		URL url = new URL(google + URLEncoder.encode(search, charset));
		Reader reader = new InputStreamReader(url.openStream(), charset);
		GoogleResults results = new Gson().fromJson(reader, GoogleResults.class);
		String firstURL = results.getResponseData().getResults().get(0).getUrl();
		int limiter = 10;
		if(results.getResponseData().getResults().size() < 10){
			limiter = results.getResponseData().getResults().size();
		}
		if(!firstURL.contains("en.wikipedia.org/")){
			for(int i=0; i < limiter;i++){
				if(results.getResponseData().getResults().get(i).getUrl().contains("en.wikipedia.org/")){
					firstURL = results.getResponseData().getResults().get(i).getUrl();
					break;
				}
			}
		}
		firstURL = firstURL.replace("%25E2%2580%2593", "-");
		return firstURL;
	}
}
