package hexlet.code;

import org.junit.jupiter.api.Test;
import java.nio.file.Paths;
import java.nio.file.Files;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {

    private String getFixturePath(String format, String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", format, fileName)
                .toAbsolutePath().toString();
    }

    private String getExpectedPath(String testName) {
        return Paths.get("src", "test", "resources", "expected", testName + ".txt")
                .toAbsolutePath().toString();
    }

    private String readExpected(String testName) throws Exception {
        return Files.readString(Paths.get(getExpectedPath(testName))).trim();
    }

    @Test
    public void testJsonStylish() throws Exception {
        String file1 = getFixturePath("json", "file1.json");
        String file2 = getFixturePath("json", "file2.json");
        String result = Differ.generate(file1, file2, "stylish");
        String expected = readExpected("json_stylish");
        assertEquals(expected, result);
    }

    @Test
    public void testJsonPlain() throws Exception {
        String file1 = getFixturePath("json", "file1.json");
        String file2 = getFixturePath("json", "file2.json");
        String result = Differ.generate(file1, file2, "plain");
        String expected = readExpected("json_plain");
        assertEquals(expected, result);
    }

    @Test
    public void testJsonJson() throws Exception {
        String file1 = getFixturePath("json", "file1.json");
        String file2 = getFixturePath("json", "file2.json");
        String result = Differ.generate(file1, file2, "json");
        String expected = readExpected("json_json");
        assertEquals(expected, result);
    }

    @Test
    public void testJsonDefault() throws Exception {
        String file1 = getFixturePath("json", "file1.json");
        String file2 = getFixturePath("json", "file2.json");
        String result = Differ.generate(file1, file2);
        String expected = readExpected("json_stylish"); // default = stylish
        assertEquals(expected, result);
    }

    @Test
    public void testYmlStylish() throws Exception {
        String file1 = getFixturePath("yml", "file1.yml");
        String file2 = getFixturePath("yml", "file2.yml");
        String result = Differ.generate(file1, file2, "stylish");
        String expected = readExpected("yml_stylish");
        assertEquals(expected, result);
    }

    @Test
    public void testYmlPlain() throws Exception {
        String file1 = getFixturePath("yml", "file1.yml");
        String file2 = getFixturePath("yml", "file2.yml");
        String result = Differ.generate(file1, file2, "plain");
        String expected = readExpected("yml_plain");
        assertEquals(expected, result);
    }

    @Test
    public void testYmlJson() throws Exception {
        String file1 = getFixturePath("yml", "file1.yml");
        String file2 = getFixturePath("yml", "file2.yml");
        String result = Differ.generate(file1, file2, "json");
        String expected = readExpected("yml_json");
        assertEquals(expected, result);
    }

    @Test
    public void testYmlDefault() throws Exception {
        String file1 = getFixturePath("yml", "file1.yml");
        String file2 = getFixturePath("yml", "file2.yml");
        String result = Differ.generate(file1, file2);
        String expected = readExpected("yml_stylish"); // default = stylish
        assertEquals(expected, result);
    }
}
