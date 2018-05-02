package app.model;

import java.util.List;
import java.util.ArrayList;
import app.model.JsonValue;

public class JsonArray extends JsonValue {

	public JsonArray() {
		mElements = new ArrayList<JsonValue>();
	}
	
	public JsonArray add(JsonValue x) {
		mElements.add(x);
		return this;
	}
	
	public String toJson() {
		return toJson("");
	}
	public String toJson(String prefix) {
		String s = prefix + "[\n";
		int i=0;
		for (JsonValue x: mElements) {
			s += x.toJson(prefix+"  ");
			if (i < (mElements.size()-1) ) s += ",";
			s += "\n";
		}
		s += prefix + "]\n";
		return s;
	}
	
	private List<JsonValue> mElements;
}
