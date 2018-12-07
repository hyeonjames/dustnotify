package com.dustnotify.http;

import com.fasterxml.jackson.databind.JsonNode;

@FunctionalInterface
public interface JNodeConvertFunc<T> {
	public T method(JsonNode node) throws Exception;
}
