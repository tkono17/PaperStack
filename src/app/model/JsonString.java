package app.model;

import java.lang.String;
import app.model.JsonValue;

public class JsonString extends JsonValue {

	public static final String kQuotationMark="\\\"";
	public static final String kSlash="\\/";
	public static final String kBackSlash="\\\\";
	public static final String kBackspace="\\b";
	public static final String kFormFeed="\\f";
	public static final String kNewline="\\n";
	public static final String kCarriageReturn="\\r";
	public static final String kHorizontalTab="\\t";
	public static final String kUnicodeChar="\\u";

	public JsonString() {
		mValue = "";
	}
	
	public JsonString(String x) {
		setString(x);
	}
	
	public String toString() {
		return value();
	}
	
	public String getString() {
		return FromJson(mValue);
	}
	public JsonString setString(String x) {
		mValue = ToJson(x);
		return this;
	}

	public String value() {
		return mValue;
	}
	public JsonString setValue(String x) {
		mValue = x;
		return this;
	}

	public String toJson() {
		return toJson("");
	}
	public String toJson(String prefix) {
		String s = prefix+"\""+mValue+"\"";
		return s;
	}
	
	public static String FromJson(String x) {
		String y="";
		String z="";
		String sx="";
		int i;
		
		for (i=0; i<x.length(); ++i) {
			z = "";
			if ( (i+1) < x.length()) {
				sx = x.substring(i, i+1);
				if (sx==kQuotationMark) {
					z = String.valueOf('\"');
					i ++;
				} else if (sx==kBackSlash) {
					z = String.valueOf('\\');
					i ++;
				} else if (sx==kBackspace) {
					z = String.valueOf('\b');
					i ++;
				} else if (sx==kFormFeed) {
					z = String.valueOf('\f');
					i ++;
				} else if (sx==kNewline) {
					z = String.valueOf('\n');
					i ++;
				} else if (sx==kCarriageReturn) {
					z = String.valueOf('\\');
					i ++;
				} else if (sx==kHorizontalTab) {
					z = String.valueOf('\\');
					i ++;
				} else {
					z = String.valueOf(x.charAt(i));
				}
			} else {
				z = String.valueOf(x.charAt(i));
			}
			y += z;
		}
		return y;
	}
	
	public static String ToJson(String x) {
		int i;
		String y="";
		String z = "";
		
		for (i=0; i<x.length(); ++i) {
			z = String.valueOf(x.charAt(i));
			switch (x.charAt(i)) {
			case '\"':
				z = kQuotationMark;
				break;
			case '/':
				z = kSlash;
				break;
			case '\\':
				z = kBackSlash;
				break;
			case '\b':
				z = kBackspace;
				break;
			case '\f':
				z = kFormFeed;
				break;
			case '\n':
				z = kNewline;
				break;
			case '\r':
				z = kCarriageReturn;
				break;
			case '\t':
				z = kHorizontalTab;
				break;
			default:
				z = String.valueOf(x.charAt(i));
			}
			y += z;
		}
		return y;
	}
	
	private String mValue;
	
}
