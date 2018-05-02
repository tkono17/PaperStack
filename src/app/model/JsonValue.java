package app.model;

import java.lang.String;

public class JsonValue {
	
	public JsonValue() {
	}
	
	public JsonValue setValue(String x) {
		return this;
	}
	
	public String toJson() {
		return "";
	}
	public String toJson(String prefix) {
		return prefix+toJson("");
	}
	
	
}
