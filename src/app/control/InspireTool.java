package app.control;

import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.http.impl.client.CloseableHttpClient;

import app.model.JsonObject;
import app.model.JsonNumber;
import app.model.JsonString;
import app.model.JsonArray;
import app.model.JsonPair;
import app.model.CanonicalJsonParser;
import app.model.PaperStackData;
import app.model.Article;
import app.model.JournalRef;

/**
 * @author tkohno
 *
 */
public class InspireTool {

	public InspireTool() {
		mSiteURL = "https://inspirehep.net";
	}
	
	private String getURI(String uri) {
		String s = "";
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(uri);
		try {
			CloseableHttpResponse response = httpclient.execute(httpget);
			System.out.println("Http get status code: " + response.getStatusLine().getStatusCode());
			s = EntityUtils.toString(response.getEntity(), "UTF-8");
		} catch (IOException e) {
		} finally {
		}
		return s;
	}

	public List<String> findURLs_hlxe(String inspirepage) {
		List<String> v = new ArrayList<String>();
		Pattern re_hlxe = Pattern.compile(".*href=\"(.+)\".*");
		String[] lines = inspirepage.split("\n");
		for (String line: lines) {
			if (line.contains("LaTeX(EU)")) {
				//System.out.println("Matched LaTeX(EU): " + line);
				Matcher m = re_hlxe.matcher(line);
				if (m.matches()) {
					//System.out.println("Match group 1:" + m.group(1));
					v.add(m.group(1));
				}
			}
		}
		return v;
	}
	
	public String findURL_next(String inspirepage) {
		String s="";
		Pattern re1 = Pattern.compile(".*<a href=\"([^\"]+)\".*><img src=\"/img/sn\\.gif\".+");

		String[] lines = inspirepage.split("\n");
		for (String line: lines) {
			if (line.contains("sn.gif")) {
				//System.out.println("Next page: " + line);
				Matcher m = re1.matcher(line);
				if (m.matches() ) {
					//System.out.println("Next URL: " + m.group(1));
					s = m.group(1);
					if (s.startsWith("/")) {
						s = mSiteURL+s;
					}
				}
			}
		}
		return s;
	}
	
	public void test1(String[] args) {
		String uri = "https://inspirehep.net/search?ln=ja&p=find+a+takanori+kono&of=hb&sf=earliestdate&so=d";
		String page0 = getURI(uri);
	//	System.out.println(page0);
		List<String> hlxelinks = findURLs_hlxe(page0);
		System.out.println(hlxelinks.size() + " entries found");
		for (String x: hlxelinks) {
			System.out.println("HLXE page: " + x);
		}
		String nextpage = findURL_next(page0);
		System.out.println("Next page: " + nextpage);
	}

	public void test2(String[] args) {
		JsonObject doc;
		String fname_json = "test2.json";
		
		File file_json = new File("C:\\Users\\tkohno\\Desktop\\test2.json");
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file_json));
			String line="";
			CanonicalJsonParser parser = new CanonicalJsonParser();
			doc = new JsonObject();
			JsonObject jobj = null;
			//
			doc.add("string", new JsonString("ABC"));
			doc.add("float", new JsonNumber(0.012));
			doc.add("int", new JsonNumber(345));
			writer.write(doc.toJson());
			System.out.println("doc="+doc.toJson());
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int writeDataToJson(PaperStackData ads, String fname) {
		int ok=0;
		return ok;
	}
	public int readDataFromJson(PaperStackData ads, String fname) {
		int ok=0;
		return ok;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InspireTool tool = new InspireTool();
		tool.test2(args);
	}

	private String mSiteURL;
	
}
