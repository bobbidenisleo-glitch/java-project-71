import hexlet.code.Differ;
import java.nio.file.Files;
import java.nio.file.Paths;

public class UpdateExpected {
    public static void main(String[] args) throws Exception {
        String file1 = "app/src/test/resources/nested1.json";
        String file2 = "app/src/test/resources/nested2.json";
        String result = Differ.generate(file1, file2, "stylish");
        Files.writeString(Paths.get("app/src/test/resources/expected/nested_stylish.txt"), result);
        System.out.println("Updated nested_stylish.txt");
    }
}
