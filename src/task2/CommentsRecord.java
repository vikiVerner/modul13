package task2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.jsoup.Jsoup;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class CommentsRecord {
    public static void writeCommentInFIle(int id) throws IOException {
        int postId = findPostId(id);
        String url = "https://jsonplaceholder.typicode.com/posts/"+postId+"/comments";
        String jsonResponse = Jsoup
                .connect(url)
                .ignoreContentType(true)
                .get()
                .body()
                .text();
        Type typeToken = TypeToken
                .getParameterized(List.class, Comment.class)
                .getType();
        List<Comment> comments = new Gson().fromJson(jsonResponse, typeToken);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(comments);
        OutputStream fileOutputStream = new FileOutputStream("C:\\Users\\alexa\\IdeaProjects\\modul13\\src\\task2\\user-"+id+"-post-"+postId+"-comments.json");
        byte[] buffer = json.getBytes();
        fileOutputStream.write(buffer,0, buffer.length);
        fileOutputStream.close();

    }

    public static int findPostId(int id) throws IOException {
        String url = "https://jsonplaceholder.typicode.com/users/"+id+"/posts";
        String jsonResponse = Jsoup
                .connect(url)
                .ignoreContentType(true)
                .get()
                .body()
                .text();
        Type typeToken = TypeToken
                .getParameterized(List.class, Post.class)
                .getType();
        List<Post> posts = new Gson().fromJson(jsonResponse, typeToken);
        Post searchedPost = posts
                .stream()
                .max(Comparator.comparing(Post::getId))
                .orElseThrow();
        return searchedPost.getId();
    }
   /* public static String getAllData() throws IOException {

        String jsonResponse = Jsoup
                .connect(url)
                .ignoreContentType(true)
                .get()
                .body()
                .text();

        return jsonResponse;
    }*/
}
