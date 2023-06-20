package task3;

import java.io.IOException;
import java.util.Scanner;

public class OpenTasksTest {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter user id.");
        int id = scanner.nextInt();
        scanner.close();
        System.out.println("Opened tasks: " + OpenTasks.findOpenTasks(id));

    }
}
