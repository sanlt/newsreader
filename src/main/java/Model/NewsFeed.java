package Model;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class NewsFeed {

  private JsonElement jse;

  public NewsFeed(){
    try {
      URL newsURL;

      newsURL = new URL("http://newsapi.org/v2/everything?q=bitcoin&from=2020-02-26&sortBy=publishedAt&apiKey=" + ApiKey.apiKey);
      System.out.println(newsURL);

      // Encode given URL -- could throw UnsupportedEncodingException

      // Open the URL
      InputStream is = newsURL.openStream(); // throws an IOException
      BufferedReader br = new BufferedReader(new InputStreamReader(is));

      // Read the result into a JSON Element
      jse = new JsonParser().parse(br);
      System.out.println(jse);

      // Close the connection
      is.close();
      br.close();
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public String getTitle(int i){
    return jse.getAsJsonObject().get("articles").getAsJsonArray().get(i).getAsJsonObject().get("description").getAsString();
  }

  public static void main(String [] args)
	{
		NewsFeed x = new NewsFeed();
		System.out.println(x.getTitle(0));

	}


}
