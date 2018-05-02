package app.control;

import app.model.Article;
import app.model.JournalRef;
import app.model.AppDataStore;
import app.model.JsonObject;

public class AppJson {

	public AppJson() {
	}
	
	public JsonObject articleToJson(Article article) {
		JsonObject obj = new JsonObject();
		return obj;
	}
	
	public Article jsonToArticle(JsonObject obj) {
		Article a = new Article();
		return a;
	}
	
	public JsonObject appDataToJson(AppDataStore ads) {
		JsonObject obj = new JsonObject();
		return obj;
	}
	
	public AppDataStore jsonToAppData(JsonObject obj) {
		AppDataStore ads = new AppDataStore();
		return ads;
	}
}
