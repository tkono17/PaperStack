package app.model;

public class JsonLiteral {
	
	public static final String kTrue="true";
	public static final String kFalse="false";
	public static final String kNull="null";
	
	public JsonLiteral(String x) {
		setValue(x);
	}
	
	public String toString() {
		return value();
	}
	
	public String value() {
		return mValue;
	}
	
	public JsonLiteral setValue(String x) {
		if (x==kTrue || x==kFalse || x==kNull) {
			mValue = x;
		} else {
			mValue = "";
		}
		return this;
	}
	
	public String toJson() {
		return toJson("");
	}
	public String toJson(String prefix) {
		return prefix+mValue;
	}
	private String mValue;
	
}
