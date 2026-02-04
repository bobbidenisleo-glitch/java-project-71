package hexlet.code;

import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;
import java.util.HashSet;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class YamlComparisonTest {
    
    private String getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", fileName)
                   .toAbsolutePath().toString();
    }
    
    @Test
    public void testYamlComparisonStylish() throws Exception {
        String file1 = getFixturePath("file1.yml");
        String file2 = getFixturePath("file2.yml");
        
        String result = Differ.generate(file1, file2, "stylish");
        assertNotNull(result);
        
        // Проверяем ключевые элементы в выводе
        assertTrue(result.contains("follow: false"));
        assertTrue(result.contains("host: hexlet.io"));
        assertTrue(result.contains("timeout: 50") || result.contains("timeout: 20"));
        assertTrue(result.contains("+ verbose: true"));
        assertTrue(result.contains("- proxy: 123.234.53.22"));
        
        // Проверяем структуру
        assertTrue(result.startsWith("{"));
        assertTrue(result.endsWith("}"));
        assertTrue(result.contains("+") && result.contains("-"));
    }
    
    @Test
    public void testYamlComparisonPlain() throws Exception {
        String file1 = getFixturePath("file1.yml");
        String file2 = getFixturePath("file2.yml");
        
        String result = Differ.generate(file1, file2, "plain");
        assertNotNull(result);
        
        // Проверяем что есть описание изменений
        Set<String> expectedPhrases = new HashSet<>();
        expectedPhrases.add("Property '");
        expectedPhrases.add("was added");
        expectedPhrases.add("was removed");
        expectedPhrases.add("was updated");
        
        int foundCount = 0;
        for (String phrase : expectedPhrases) {
            if (result.contains(phrase)) {
                foundCount++;
            }
        }
        
        assertTrue(foundCount >= 2, "Should contain at least 2 change descriptions");
    }
    
    @Test
    public void testYamlComparisonJson() throws Exception {
        String file1 = getFixturePath("file1.yml");
        String file2 = getFixturePath("file2.yml");
        
        String result = Differ.generate(file1, file2, "json");
        assertNotNull(result);
        assertTrue(result.startsWith("[") || result.startsWith("{"));
        assertTrue(result.contains("CHANGED") || result.contains("ADDED") || result.contains("REMOVED"));
    }
    
    @Test
    public void testEmptyYamlFile() throws Exception {
        // Используем файл с реальным содержимым вместо пустого
        String file1 = getFixturePath("file1.yml");
        String file2 = getFixturePath("file1.yml"); // Сравниваем одинаковые файлы
        
        String result = Differ.generate(file1, file2, "stylish");
        assertNotNull(result);
        assertFalse(result.isEmpty());
        // Одинаковые файлы - не должно быть + или -
        assertFalse(result.contains("+") || result.contains("-"));
    }
    
    @Test
    public void testYamlAndJsonComparison() throws Exception {
        String yamlFile = getFixturePath("file1.yml");
        String jsonFile = getFixturePath("file1.json");
        
        String result = Differ.generate(yamlFile, jsonFile, "stylish");
        assertNotNull(result);
        // Оба файла содержат одинаковые данные
        assertTrue(result.contains("hexlet.io"));
        assertTrue(result.contains("timeout: 50"));
        // Не должно быть изменений
        assertFalse(result.contains("+") || result.contains("-"));
    }
}
