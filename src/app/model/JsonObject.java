package app.model;

import java.util.List;
import java.util.ArrayList;
import app.model.JsonPair;

public class JsonObject extends JsonPair {

	public JsonObject() {
		mMembers = new ArrayList<JsonPair>();
	}
	
	public JsonObject add(String key, JsonValue x) {
		mMembers.add(new JsonPair(key, x));
		return this;
	}

	public JsonObject add(JsonPair x) {
		mMembers.add(x);
		return this;
	}
	
	public List<JsonPair> members() {
		return mMembers;
	}
	
	public JsonValue findMember(String key) {
		JsonValue y=null;
		for (JsonPair p: mMembers) {
			if (p.name() == key) {
				y = p.value();
				break;
			}
		}
		return y;
	}
	
	public String toJson() {
		return toJson("");
	}
	public String toJson(String prefix) {
		String s=prefix+"{\n";
		int i=0;
		for (JsonPair x: mMembers) {
			s += x.toJson(prefix+"  ");
			if (i < (mMembers.size()-1) ) s += ",";
			s += "\n";
			i++;
		}
		s += prefix+"}\n";
		return s;
	}
	private List<JsonPair> mMembers;
	
}
