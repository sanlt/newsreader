package Model;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class NewsFeed {

    private JsonElement jse;

    public NewsFeed() {
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

            // Close the connection
            is.close();
            br.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JsonArray getArticles() {
        return jse.getAsJsonObject().get("articles").getAsJsonArray();
    }

    public String getTitle(int i) {
        return getArticles().get(i).getAsJsonObject().get("title").getAsString();
    }

    public static void main(String[] args) {
        NewsFeed x = new NewsFeed();
        // System.out.println(x.getTitle(0));


        List<Article> articleObjects = new ArrayList<>();
        x.getArticles().forEach((Object article) -> {
            Article art = new Article((JsonObject) article);
            articleObjects.add(art);
            art.printArticle();
        });


    }


}
