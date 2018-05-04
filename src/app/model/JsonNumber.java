package app.model;

import java.lang.Number;
import java.lang.Double;
import java.lang.Integer;
import app.model.JsonValue;

public class JsonNumber extends JsonValue {

	public JsonNumber() {
	}
	
	public JsonNumber(Integer x) {
		mValue = x;
	}
	
	public JsonNumber(Double x) {
		mValue = x;
	}
	
	public String toString() {
		return value().toString();
	}
	public int toInteger() {
		return mValue.intValue();
	}
	public double toDouble() {
		return mValue.doubleValue();
	}
	
	public Number value() {
		return mValue;
	}
	
	public JsonNumber setValue(String x) {
		if (x.contains(".") || x.contains("E") || x.contains("e")) {
			mValue = new Double(x);
		} else {
			mValue = new Integer(x);
		}
		return this;
	}
	
	public String toJson() {
		return toJson("");
	}
	public String toJson(String prefix) {
		return prefix+mValue.toString();
	}

	private Number mValue;
}
