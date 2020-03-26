package Model;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;

public class Article {

    private String sourceName;
    private String author;
    private String title;
    private String description;
    private String publishedAt;

    public Article(JsonObject article) {
        this.sourceName = article.get("source").getAsJsonObject().get("name").getAsString();
        if (article.get("author")instanceof JsonNull) {
            this.author = "Unknown Author";
        } else {
            this.author = article.get("author").getAsString();
        }
        this.title = article.get("title").getAsString();
        this.description = article.get("description").getAsString();
        this.publishedAt = article.get("publishedAt").getAsString();
    }

    public void printArticle() {
        System.out.println(sourceName);
        System.out.println(author);
        System.out.println(title);
        System.out.println(description);
        System.out.println(publishedAt);

        System.out.println();
    }

}
