package app.control;

import java.util.List;
import app.model.Article;
import app.model.JournalRef;
import app.model.AppDataStore;
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
		return obj;
	}
	
	public Article jsonToArticle(JsonObject obj) {
		Article a = new Article();
		a.setTitle(obj.findMember("title").toString());
		a.setAuthors(obj.findMember("authors").toString());
		a.setInspireId(obj.findMembers("inspireId").toInteger());
		return a;
	}
	
	public JsonObject appDataToJson(AppDataStore ads) {
		JsonObject obj = new JsonObject();
		return obj;
	}
	
	public AppDataStore jsonToAppData(JsonObject obj) {
		AppDataStore ads = new AppDataStore();
		List<Article> articles = ads.articles();
		return ads;
	}
}
