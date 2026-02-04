package hexlet.code;

import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NestedStructureTest {
    
    private String getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", fileName)
                   .toAbsolutePath().toString();
    }
    
    private String readExpected(String fileName) throws Exception {
        var path = Paths.get("src", "test", "resources", "expected", fileName);
        return Files.readString(path).trim();
    }
    
    @Test
    public void testNestedJsonComparison() throws Exception {
        String file1 = getFixturePath("nested1.json");
        String file2 = getFixturePath("nested2.json");
        
        String result = Differ.generate(file1, file2, "stylish");
        String expected = readExpected("nested_stylish.txt");
        
        assertEquals(expected, result);
    }
    
    @Test
    public void testNestedYamlComparison() throws Exception {
        String file1 = getFixturePath("nested1.yml");
        String file2 = getFixturePath("nested2.yml");
        
        String result = Differ.generate(file1, file2, "stylish");
        String expected = readExpected("nested_stylish.txt");
        
        assertEquals(expected, result);
    }
    
    @Test
    public void testDefaultStylishFormat() throws Exception {
        String file1 = getFixturePath("nested1.json");
        String file2 = getFixturePath("nested2.json");
        
        // Без указания формата должен использоваться stylish
        String result1 = Differ.generate(file1, file2);
        String result2 = Differ.generate(file1, file2, "stylish");
        
        assertEquals(result1, result2);
    }
}
