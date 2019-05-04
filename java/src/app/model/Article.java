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
	public String authors() {
		return mAuthors;
	}
	public Article setJournalRef(String x) {
		mJournalRef = new JournalRef(x);
		return this;
	}
	public JournalRef journalRef() {
		return mJournalRef;
	}
	
	public Article setDOI(String x) {
		mDOI = x;
		return this;
	}
	public String DOI() {
		return mDOI;
	}
	public Article setInspireId(int x) {
		mInspireId = x;
		return this;
	}
	public int inspireId() {
		return mInspireId;
	}
	public Article setArxivId(String x) {
		mArxivId = x;
		return this;
	}
	public String arxivId() {
		return mArxivId;
	}
	public Article setLatexEU(String x) {
		mLatexEU = x;
		return this;
	}
	public String latexEU() {
		return mLatexEU;
	}
	
	private String mTitle;
	private String mAuthors;
	private JournalRef mJournalRef;
	private String mDOI;
	private int mInspireId;
	private String mArxivId;
	private String mLatexEU;
	private Date mPublishedDate;
	private List<ArticleLabel> mLabels;
	
}
