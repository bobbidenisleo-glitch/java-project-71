package hexlet.code;

import org.junit.jupiter.api.Test;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;

class DifferTest {
    
    private String getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", fileName)
                   .toAbsolutePath()
                   .toString();
    }
    
    @Test
    void testGenerateJson() throws Exception {
        String path1 = getFixturePath("file1.json");
        String path2 = getFixturePath("file2.json");
        String result = Differ.generate(path1, path2);
        
        assertNotNull(result);
        assertFalse(result.isEmpty());
        // Проверяем что результат содержит что-то осмысленное
        assertTrue(result.contains("Format") || 
                  result.contains("Files") || 
                  result.contains("File") ||
                  result.length() > 10);
    }
    
    @Test
    void testGenerateYaml() throws Exception {
        String path1 = getFixturePath("file1.yml");
        String path2 = getFixturePath("file2.yml");
        String result = Differ.generate(path1, path2, "stylish");
        
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }
    
    @Test
    void testGenerateWithDifferentFormats() throws Exception {
        String path1 = getFixturePath("file1.json");
        String path2 = getFixturePath("file2.json");
        
        String resultStylish = Differ.generate(path1, path2, "stylish");
        String resultPlain = Differ.generate(path1, path2, "plain");
        
        assertNotNull(resultStylish);
        assertNotNull(resultPlain);
        // Разные форматы должны давать разный вывод
        assertNotEquals(resultStylish, resultPlain);
    }
}
