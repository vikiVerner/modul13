package task1;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;


public class UserAddUpdateDelete {
    public static void main(String[] args){
        addUser();
        User user = new User();
        user.setId(1);
        user.setName("Leanne Graham");
        user.setUsername("Bret");
        user.setEmail("Sincere@april.biz");
        updateUser(user);
        deleteUser(1);
    }
    public static void addUser() {
        try {
            String baseUrl = "https://jsonplaceholder.typicode.com/users";
            User user = new User();
            user.setId(11);
            user.setName("Leanne Graham");
            user.setUsername("Bret");
            user.setEmail("Sincere@april.biz");

            User.Address address = new User.Address();
            address.setStreet("Kulas Light");
            address.setSuite("Apt. 556");
            address.setCity("Gwenborough");
            address.setZipcode("92998-3874");

            User.Geo geo = new User.Geo();
            geo.setLat("-37.3159");
            geo.setLng("81.1496");

            address.setGeo(geo);
            user.setAddress(address);

            user.setPhone("1-770-736-8031 x56442");
            user.setWebsite("hildegard.org");

            User.Company company = new User.Company();
            company.setName("Romaguera-Crona");
            company.setCatchPhrase("Multi-layered client-server neural-net");
            company.setBs("harness real-time e-markets");

            user.setCompany(company);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(user);

            Connection.Response response = Jsoup.connect(baseUrl)
                    .method(Connection.Method.POST)
                    .header("Content-Type", "application/json; charset=UTF-8")
                    .requestBody(json)
                    .execute();

            int statusCode = response.statusCode();
            System.out.println("Response Code: " + statusCode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateUser(User user){
        try {
            String baseUrl = "https://jsonplaceholder.typicode.com/users/";
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(user);

            Connection.Response response = Jsoup.connect(baseUrl+user.getId())
                    .method(Connection.Method.PUT)
                    .header("Content-Type", "application/json; charset=UTF-8")
                    .requestBody(json)
                    .execute();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteUser(int id) {
        String baseUrl = "https://jsonplaceholder.typicode.com/users/";
        try {
            Connection.Response response = Jsoup.connect(baseUrl+id)
                    .method(Connection.Method.DELETE)
                    .execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
