package app.model;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import app.model.Article;

public class AppDataStore {

	public AppDataStore() {
		mArticles = new ArrayList<Article>();
	}
	
	public AppDataStore addArticle(Article x) {
		mArticles.add(x);
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
