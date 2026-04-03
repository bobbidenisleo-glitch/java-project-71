import hexlet.code.Differ;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GenerateExpected {
    public static void main(String[] args) throws Exception {
        String format = args[0];
        String file1 = args[1];
        String file2 = args[2];
        String outputFile = args[3];
        
        String result = Differ.generate(file1, file2, format);
        Files.writeString(Paths.get(outputFile), result);
        System.out.println("Generated: " + outputFile);
    }
}
