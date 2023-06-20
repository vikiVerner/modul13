package task1;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

public class UserChanges {

    public static void main(String[] args) throws IOException {

        System.out.println("getAllData() = " + getAllData());
        System.out.println("getDataAccToId(1) = " + getDataAccToId(1));
        System.out.println("getDataAccToName(\"Antonette\") = " + getDataAccToName("Antonette"));
    }

    public static List<User> getAllData() throws IOException {
        String url = "https://jsonplaceholder.typicode.com/users";
        String jsonResponse = Jsoup
                .connect(url)
                .ignoreContentType(true)
                .get()
                .body()
                .text();

        Type typeToken = TypeToken
                .getParameterized(List.class, User.class)
                .getType();
        List<User> users = new Gson().fromJson(jsonResponse, typeToken);

        return users;
    }

    public static User getDataAccToId( int id) throws IOException {

        User userId = getAllData()
                .stream()
                .filter(it -> it.getId() == id)
                .findFirst()
                .orElseThrow();
        return userId;
    }

    public static List<User> getDataAccToName( String name) throws IOException {
        List<User> users = getAllData()
                .stream()
                .filter(it -> it.getUsername().equals(name))
                .collect(Collectors.toList());
        return users;
    }

}
