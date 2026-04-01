package hexlet.code;

import org.junit.jupiter.api.Test;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DifferTest {
    
    private String getFixturePath(String format, String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", format, fileName)
                .toAbsolutePath().toString();
    }
    
    @Test
    public void testJsonStylish() throws Exception {
        String file1 = getFixturePath("json", "file1.json");
        String file2 = getFixturePath("json", "file2.json");
        String result = Differ.generate(file1, file2, "stylish");
        assertTrue(result.contains("key1"));
        assertTrue(result.contains("key2"));
        assertTrue(result.contains("key3"));
        assertTrue(result.contains("updated"));
    }
    
    @Test
    public void testJsonPlain() throws Exception {
        String file1 = getFixturePath("json", "file1.json");
        String file2 = getFixturePath("json", "file2.json");
        String result = Differ.generate(file1, file2, "plain");
        assertTrue(result.contains("Property 'key1' was updated"));
        assertTrue(result.contains("Property 'key2' was removed"));
        assertTrue(result.contains("Property 'key3' was added"));
    }
    
    @Test
    public void testJsonJson() throws Exception {
        String file1 = getFixturePath("json", "file1.json");
        String file2 = getFixturePath("json", "file2.json");
        String result = Differ.generate(file1, file2, "json");
        assertTrue(result.contains("key1"));
        assertTrue(result.contains("CHANGED"));
        assertTrue(result.contains("key2"));
        assertTrue(result.contains("REMOVED"));
        assertTrue(result.contains("key3"));
        assertTrue(result.contains("ADDED"));
    }
    
    @Test
    public void testJsonDefault() throws Exception {
        String file1 = getFixturePath("json", "file1.json");
        String file2 = getFixturePath("json", "file2.json");
        String result = Differ.generate(file1, file2);
        assertTrue(result.contains("key1"));
        assertTrue(result.contains("key2"));
        assertTrue(result.contains("key3"));
    }
    
    @Test
    public void testYmlStylish() throws Exception {
        String file1 = getFixturePath("yml", "file1.yml");
        String file2 = getFixturePath("yml", "file2.yml");
        String result = Differ.generate(file1, file2, "stylish");
        assertTrue(result.contains("timeout: 50"));
        assertTrue(result.contains("timeout: 20"));
        assertTrue(result.contains("verbose: true"));
        assertTrue(result.contains("follow: false"));
    }
    
    @Test
    public void testYmlPlain() throws Exception {
        String file1 = getFixturePath("yml", "file1.yml");
        String file2 = getFixturePath("yml", "file2.yml");
        String result = Differ.generate(file1, file2, "plain");
        assertTrue(result.contains("Property 'timeout' was updated"));
        assertTrue(result.contains("Property 'verbose' was added"));
    }
    
    @Test
    public void testYmlJson() throws Exception {
        String file1 = getFixturePath("yml", "file1.yml");
        String file2 = getFixturePath("yml", "file2.yml");
        String result = Differ.generate(file1, file2, "json");
        assertTrue(result.contains("timeout"));
        assertTrue(result.contains("CHANGED"));
        assertTrue(result.contains("verbose"));
        assertTrue(result.contains("ADDED"));
    }
    
    @Test
    public void testYmlDefault() throws Exception {
        String file1 = getFixturePath("yml", "file1.yml");
        String file2 = getFixturePath("yml", "file2.yml");
        String result = Differ.generate(file1, file2);
        assertTrue(result.contains("timeout: 50"));
        assertTrue(result.contains("timeout: 20"));
        assertTrue(result.contains("verbose: true"));
    }
}
