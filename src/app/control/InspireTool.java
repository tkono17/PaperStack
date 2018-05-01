package app.control;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.http.impl.client.CloseableHttpClient;

/**
 * @author tkohno
 *
 */
public class InspireTool {

	public InspireTool() {
		mBaseDir = "https://inspirehep.net";
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
					v.add(m.group());
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
		String nextpage = findURL_next(page0);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InspireTool tool = new InspireTool();
		tool.test1(args);
	}

	private String mBaseDir;
	
}
