package app.model;

import java.lang.String;

public class JsonValue {
	
	public JsonValue() {
	}
	
	public JsonValue setValue(String x) {
		return this;
	}
	
	public String toString() {
		return "";
	}
	public int toInteger() {
		return 0;
	}
	public double toDouble() {
		return 0.0;
	}

	public String toJson() {
		return "";
	}

	public String toJson(String prefix) {
		return prefix+toJson("");
	}
	
	
}
