package app.model;

import java.lang.String;
import app.model.JsonValue;

public class JsonPair {

	public JsonPair() {
		mName = "";
		mValue = null;
	}

	public JsonPair(String key, JsonValue x) {
		mName = key;
		mValue = x;
	}

	public JsonPair setName(String x) {
		mName = x;
		return this;
	}
	public String name() {
		return mName;
	}
	
	public JsonPair setValue(JsonValue x) {
		mValue = x;
		return this;
	}
	public JsonValue value() {
		return mValue;
	}
	public JsonPair set(String name, JsonValue value) {
		mName = name;
		mValue = value;
		return this;
	}
	
	public String toJson() {
		return toJson("");
	}
	public String toJson(String prefix) {
		String s=prefix+"\""+mName+"\" : "+mValue.toJson();
		return s;
	}
	
	private String mName;
	private JsonValue mValue;
	
}
