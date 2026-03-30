package hexlet.code;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DifferTest {
    
    @TempDir
    Path tempDir;
    
    @Test
    public void testGenerateJsonComparison() throws Exception {
        Path file1 = tempDir.resolve("file1.json");
        Path file2 = tempDir.resolve("file2.json");
        
        Files.writeString(file1, """
                {
                    "key1": "value1",
                    "key2": 45
                }
                """);
        
        Files.writeString(file2, """
                {
                    "key1": "updated",
                    "key3": "new"
                }
                """);
        
        String result = Differ.generate(file1.toString(), file2.toString());
        assertTrue(result.contains("key1"));
        assertTrue(result.contains("updated"));
        assertTrue(result.contains("key2"));
        assertTrue(result.contains("key3"));
        assertTrue(result.contains("new"));
    }
    
    @Test
    public void testGenerateYamlComparison() throws Exception {
        Path file1 = tempDir.resolve("file1.yml");
        Path file2 = tempDir.resolve("file2.yml");
        
        Files.writeString(file1, """
                host: hexlet.io
                timeout: 50
                follow: false
                """);
        
        Files.writeString(file2, """
                host: hexlet.io
                timeout: 20
                verbose: true
                follow: false
                """);
        
        String result = Differ.generate(file1.toString(), file2.toString());
        assertTrue(result.contains("timeout: 50"));
        assertTrue(result.contains("timeout: 20"));
        assertTrue(result.contains("verbose: true"));
    }
    
    @Test
    public void testGenerateWithPlainFormat() throws Exception {
        Path file1 = tempDir.resolve("file1.json");
        Path file2 = tempDir.resolve("file2.json");
        
        Files.writeString(file1, """
                {
                    "key1": "value1"
                }
                """);
        
        Files.writeString(file2, """
                {
                    "key1": "updated"
                }
                """);
        
        String result = Differ.generate(file1.toString(), file2.toString(), "plain");
        assertTrue(result.contains("Property 'key1' was updated"));
        assertTrue(result.contains("'value1'"));
        assertTrue(result.contains("'updated'"));
    }
    
    @Test
    public void testGenerateWithJsonFormat() throws Exception {
        Path file1 = tempDir.resolve("file1.json");
        Path file2 = tempDir.resolve("file2.json");
        
        Files.writeString(file1, """
                {
                    "key1": "value1"
                }
                """);
        
        Files.writeString(file2, """
                {
                    "key1": "updated"
                }
                """);
        
        String result = Differ.generate(file1.toString(), file2.toString(), "json");
        assertTrue(result.contains("key1"));
        assertTrue(result.contains("CHANGED"));
        assertTrue(result.contains("value1"));
        assertTrue(result.contains("updated"));
    }
    
    @Test
    public void testGenerateWithStylishFormat() throws Exception {
        Path file1 = tempDir.resolve("file1.json");
        Path file2 = tempDir.resolve("file2.json");
        
        Files.writeString(file1, """
                {
                    "key1": "value1"
                }
                """);
        
        Files.writeString(file2, """
                {
                    "key1": "updated"
                }
                """);
        
        String result = Differ.generate(file1.toString(), file2.toString(), "stylish");
        assertTrue(result.contains("key1"));
        assertTrue(result.contains("value1"));
        assertTrue(result.contains("updated"));
        assertTrue(result.contains("-") && result.contains("+"));
    }
    
    @Test
    public void testGenerateWithNestedStructures() throws Exception {
        Path file1 = tempDir.resolve("file1.json");
        Path file2 = tempDir.resolve("file2.json");
        
        Files.writeString(file1, """
                {
                    "chars1": ["a", "b", "c"],
                    "numbers1": [1, 2, 3]
                }
                """);
        
        Files.writeString(file2, """
                {
                    "chars1": ["a", "b", "c"],
                    "numbers1": [4, 5, 6]
                }
                """);
        
        String result = Differ.generate(file1.toString(), file2.toString());
        assertTrue(result.contains("chars1"));
        assertTrue(result.contains("numbers1"));
    }
}
