package task1;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import javax.lang.model.util.Elements;
import javax.swing.text.Document;
import javax.swing.text.Element;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class WorkingWithHttpClient {
    private static final String TEST_URL = "https://jsonplaceholder.typicode.com/users";
    private static final Gson gson = new Gson();

    public static void main(String[] args) {
        WorkingWithHttpClient client = new WorkingWithHttpClient();

        // Читання JSON з файлу
        String jsonFilePath = "C:\\Users\\alexa\\IdeaProjects\\modul13\\src\\task1\\user.json";
        String jsonContent = "";
        try {
            jsonContent = new String(Files.readAllBytes(Paths.get(jsonFilePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Парсинг користувача з JSON
        User user = gson.fromJson(jsonContent, User.class);

        // Виклик методу createUser
        try {
            User createdUser = client.createUser(user);
            System.out.println("Created User: " + gson.toJson(createdUser));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public User createUser(User user) throws IOException {
        Connection.Response response = Jsoup.connect(TEST_URL)
                .header("Content-Type", "application/json")
                .requestBody(gson.toJson(user))
                .method(Connection.Method.POST)
                .ignoreContentType(true)
                .execute();

        return gson.fromJson(response.body(), User.class);
    }

    public User updateUser(User user, int id) throws IOException {
        String updateUrl = TEST_URL + "/" + id;
        Connection.Response response = Jsoup.connect(updateUrl)
                .header("Content-Type", "application/json")
                .requestBody(gson.toJson(user))
                .method(Connection.Method.PUT)
                .ignoreContentType(true)
                .execute();

        return gson.fromJson(response.body(), User.class);
    }

    public void deleteUser(int id) throws IOException {
        String deleteUrl = TEST_URL + "/" + id;
        Jsoup.connect(deleteUrl)
                .method(Connection.Method.DELETE)
                .execute();
    }

    public List<User> getUsers() throws IOException {
        Connection.Response response = Jsoup.connect(TEST_URL)
                .ignoreContentType(true)
                .execute();

        Type listType = new TypeToken<List<User>>() {}.getType();
        return gson.fromJson(response.body(), listType);
    }

    public User getUserById(int id) throws IOException {
        String userUrl = TEST_URL + "/" + id;
        Connection.Response response = Jsoup.connect(userUrl)
                .ignoreContentType(true)
                .execute();

        return gson.fromJson(response.body(), User.class);
    }

    public List<User> getUsersByUsername(String username) throws IOException {
        String searchUrl = TEST_URL + "?username=" + username;
        Connection.Response response = Jsoup.connect(searchUrl)
                .ignoreContentType(true)
                .execute();

        Type listType = new TypeToken<List<User>>() {}.getType();
        return gson.fromJson(response.body(), listType);
    }
}
