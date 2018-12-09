package com.dustnotify.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

public class HttpConn {
	public String get(String targetUrl, HashMap<String,Object> param) {
		URL url = null;
	    HttpURLConnection conn = null;
	    String jsonData = "";
	    BufferedReader br = null;
	    StringBuffer sb = null;
	    String returnText = "";
	 
	    try {
	    	StringBuilder ub = new StringBuilder();
	    	for(String key : param.keySet()) {
	    		String obj = URLEncoder.encode((param.get(key).toString()), "UTF-8");
	    		ub.append("&");
	    		ub.append(key);
	    		ub.append("=");
	    		ub.append(obj);
	    	}
	    	
	        url = new URL( targetUrl + "?" + ub.substring(1) );
	
	        System.out.println("HTTP REQ GET: " + url.toString());
	        conn = (HttpURLConnection) url.openConnection();
	        
	        conn.setRequestMethod("GET");
	        conn.connect();
	 
	        br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
	 
	        sb = new StringBuffer();
	 
	        while ((jsonData = br.readLine()) != null) {
	            sb.append(jsonData);
	        }
	 
	        returnText = sb.toString();
	 
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (br != null) br.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    return returnText;
	}
}
