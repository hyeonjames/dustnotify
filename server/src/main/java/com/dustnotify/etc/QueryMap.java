package com.dustnotify.etc;

import java.util.HashMap;

public class QueryMap extends HashMap<String,Object>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8089775320541050641L;

	public QueryMap set(String key, Object obj) {
		put(key, obj);
		return this;
	}
}
