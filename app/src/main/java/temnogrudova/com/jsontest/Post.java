package temnogrudova.com.jsontest;

/**
 * Created by 123 on 20.07.2015.
 */
public class Post {
    String userId;
    String id;
    String title;
    String body;

    Post(String userId, String id, String title, String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }
}
