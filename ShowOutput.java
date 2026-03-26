import hexlet.code.Differ;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ShowOutput {
    public static void main(String[] args) throws Exception {
        String file1 = "app/src/test/resources/nested1.json";
        String file2 = "app/src/test/resources/nested2.json";
        String actual = Differ.generate(file1, file2, "stylish");
        System.out.println(actual);
    }
}
