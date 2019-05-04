package app.model;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

public class PaperStackData {

	public PaperStackData() {
		mFileName = "";
		mModifiedOn = new Date();
		mArticles = new ArrayList<Article>();
	}
	
	public PaperStackData setFileName(String x) {
		mFileName = x;
		return this;
	}
	public String fileName() {
		return mFileName;
	}

	public Date modifiedOn() {
		return mModifiedOn;
	}
	public PaperStackData addArticle(Article x) {
		mArticles.add(x);
		return this;
	}
	public List<Article> articles() {
		return mArticles;
	}
	
	public PaperStackData update() {
		mModifiedOn = new Date();
		return this;
	}
	public Article findArticleByInspireId(int id) {
		Article y = null;
		for (Article x: mArticles) {
			if (x.inspireId() == id) {
				y = x;
				break;
			}
		}
		return y;
	}

	private String mFileName;
	private Date mModifiedOn;
	private List<Article> mArticles;
	
}
