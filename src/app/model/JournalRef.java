package app.model;

public class JournalRef {

	public JournalRef() {
		mValue = "";
	}
	public JournalRef(String x) {
		mValue = x;
	}

	public JournalRef setValue(String x) {
		mValue = x;
		return this;
	}
	
	public String value() {
		return mValue;
	}
	
	protected String mValue;
}
