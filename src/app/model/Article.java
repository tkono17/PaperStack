package app.model;

import java.lang.String;
import java.util.Date;
import java.util.List;
import app.model.ArticleLabel;
import app.model.JournalRef;

public class Article {
	
	public Article() {
		
	}
	
	public Article setTitle(String title) {
		mTitle = title;
		return this;
	}
	public String title() {
		return mTitle;
	}
	public Article setAuthors(String authors) {
		mAuthors = authors;
		return this;
	}
	public Article setJournalRef(String x) {
		mJournalRef = new JournalRef(x);
		return this;
	}
	
	public String DOI() {
		return mDOI;
	}
	public int inspireId() {
		return mInspireId;
	}
	public String arxivId() {
		return mArxivId;
	}
	
	private String mTitle;
	private String mAuthors;
	private JournalRef mJournalRef;
	private String mDOI;
	private int mInspireId;
	private String mArxivId;
	private Date mPublishedDate;
	private List<ArticleLabel> mLabels;
	
}
