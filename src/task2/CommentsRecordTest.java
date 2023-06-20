package task2;

import java.io.IOException;
import java.util.Scanner;

public class CommentsRecordTest {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter id.");
        int id = scanner.nextInt();
        scanner.close();

        CommentsRecord.writeCommentInFIle(id);
    }
}
