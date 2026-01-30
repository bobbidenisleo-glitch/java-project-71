package hexlet.code;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.nio.file.Path;
import java.nio.file.Files;
import static org.junit.jupiter.api.Assertions.*;

class IntegrationTest {
    
    @TempDir
    Path tempDir;
    
    @Test
    void testFullWorkflow() throws Exception {
        // Создаём тестовые файлы
        String json1 = "{\"name\": \"John\", \"age\": 30}";
        String json2 = "{\"name\": \"Jane\", \"age\": 25}";
        
        Path file1 = tempDir.resolve("file1.json");
        Path file2 = tempDir.resolve("file2.json");
        
        Files.writeString(file1, json1);
        Files.writeString(file2, json2);
        
        // Тестируем Differ
        String result = Differ.generate(file1.toString(), file2.toString());
        assertNotNull(result);
        assertTrue(result.contains("File 1 data"));
        assertTrue(result.contains("File 2 data"));
        
        // Тестируем с форматом
        String resultWithFormat = Differ.generate(
            file1.toString(), 
            file2.toString(), 
            "plain"
        );
        assertNotNull(resultWithFormat);
        assertTrue(resultWithFormat.contains("Format: plain"));
    }
    
    @Test
    void testYamlWorkflow() throws Exception {
        // Создаём YAML файлы
        String yaml1 = "name: John\nage: 30";
        String yaml2 = "name: Jane\nage: 25";
        
        Path file1 = tempDir.resolve("file1.yml");
        Path file2 = tempDir.resolve("file2.yml");
        
        Files.writeString(file1, yaml1);
        Files.writeString(file2, yaml2);
        
        // Тестируем
        String result = Differ.generate(file1.toString(), file2.toString());
        assertNotNull(result);
        assertTrue(result.contains("File 1 data"));
    }
}
