package task3;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jsoup.Jsoup;
import task2.Comment;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

public class OpenTasks {
    public static List<Task> findOpenTasks(int id) throws IOException {
    String url = "https://jsonplaceholder.typicode.com/users/"+id+"/todos";
        String jsonResponse = Jsoup
                .connect(url)
                .ignoreContentType(true)
                .get()
                .body()
                .text();
        Type typeToken = TypeToken
                .getParameterized(List.class, Task.class)
                .getType();
        List<Task> tasks = new Gson().fromJson(jsonResponse, typeToken);
        List<Task> filteredTasks = tasks
                .stream()
                .filter(it -> it.isCompleted() == false)
                .collect(Collectors.toList());
        return  filteredTasks;
    }
}
