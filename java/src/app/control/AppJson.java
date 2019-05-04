package app.control;

import java.util.List;

import app.model.Article;
import app.model.JournalRef;
import app.model.PaperStackData;
import app.model.JsonObject;
import app.model.JsonArray;
import app.model.JsonValue;
import app.model.JsonNumber;
import app.model.JsonString;

public class AppJson {

	public AppJson() {
	}
	
	public JsonObject articleToJson(Article article) {
		JsonObject obj = new JsonObject();
		obj.add("title", new JsonString(article.title() ) );
		obj.add("authors", new JsonString(article.authors() ) ); 
		obj.add("latexEU", new JsonString(article.latexEU()));
		return obj;
	}
	
	public Article jsonToArticle(JsonObject obj) {
		Article a = new Article();
		a.setTitle(obj.findMember("title").toString());
		a.setAuthors(obj.findMember("authors").toString());
		a.setInspireId(obj.findMember("inspireId").toInteger());
		return a;
	}
	
	public JsonObject appDataToJson(PaperStackData psdata) {
		JsonObject obj = new JsonObject();
		JsonString jo_updatedOn = new JsonString(psdata.modifiedOn().toString());
		JsonArray jo_articles = new JsonArray();
		for (Article a: psdata.articles() ) {
			jo_articles.add(articleToJson(a));
		}
		obj.add("ModifiedOn", jo_updatedOn);
		obj.add("articles", jo_articles);
		return obj;
	}
	
	public PaperStackData jsonToAppData(JsonObject obj) {
		PaperStackData psdata = new PaperStackData();
		List<Article> articles = psdata.articles();
		return psdata;
	}
}
