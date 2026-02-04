package hexlet.code;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;

class FlatJsonTest {
    
    private static String flat1Path;
    private static String flat2Path;
    
    @BeforeAll
    static void setup() throws Exception {
        flat1Path = Paths.get("src", "test", "resources", "flat1.json")
                        .toAbsolutePath()
                        .toString();
        flat2Path = Paths.get("src", "test", "resources", "flat2.json")
                        .toAbsolutePath()
                        .toString();
    }
    
    @Test
    void testFlatJsonComparison() throws Exception {
        String result = Differ.generate(flat1Path, flat2Path);
        
        assertNotNull(result);
        assertFalse(result.isEmpty());
        
        // Проверяем структуру вывода
        assertTrue(result.startsWith("{"));
        assertTrue(result.endsWith("}"));
        
        // Проверяем что все ключи присутствуют в правильном порядке
        String[] lines = result.split("\n");
        
        // follow (removed)
        assertTrue(lines[1].contains("  - follow: false"));
        // host (unchanged)
        assertTrue(lines[2].contains("    host: hexlet.io"));
        // proxy (removed)
        assertTrue(lines[3].contains("  - proxy: 123.234.53.22"));
        // timeout changed (removed then added)
        assertTrue(lines[4].contains("  - timeout: 50"));
        assertTrue(lines[5].contains("  + timeout: 20"));
        // verbose (added)
        assertTrue(lines[6].contains("  + verbose: true"));
        
        // Проверяем алфавитный порядок
        int followIndex = result.indexOf("follow");
        int hostIndex = result.indexOf("host");
        int proxyIndex = result.indexOf("proxy");
        int timeoutIndex = result.indexOf("timeout");
        int verboseIndex = result.indexOf("verbose");
        
        assertTrue(followIndex < hostIndex);
        assertTrue(hostIndex < proxyIndex);
        assertTrue(proxyIndex < timeoutIndex);
        assertTrue(timeoutIndex < verboseIndex);
    }
    
    @Test
    void testSameFiles() throws Exception {
        String result = Differ.generate(flat1Path, flat1Path);
        
        assertNotNull(result);
        // Все ключи должны быть без + или -
        assertTrue(result.contains("    follow: false"));
        assertTrue(result.contains("    host: hexlet.io"));
        assertTrue(result.contains("    proxy: 123.234.53.22"));
        assertTrue(result.contains("    timeout: 50"));
        assertFalse(result.contains("  + "));
        assertFalse(result.contains("  - "));
    }
    
    @Test
    void testEmptySecondFile() throws Exception {
        // Создаём временный пустой файл
        String emptyPath = "src/test/resources/empty.json";
        Files.writeString(Paths.get(emptyPath), "{}");
        
        try {
            String result = Differ.generate(flat1Path, emptyPath);
            
            assertNotNull(result);
            // Все ключи должны быть удалены
            assertTrue(result.contains("  - follow: false"));
            assertTrue(result.contains("  - host: hexlet.io"));
            assertTrue(result.contains("  - proxy: 123.234.53.22"));
            assertTrue(result.contains("  - timeout: 50"));
            assertFalse(result.contains("  + "));
        } finally {
            Files.deleteIfExists(Paths.get(emptyPath));
        }
    }
    
    @Test
    void testEmptyFirstFile() throws Exception {
        // Создаём временный пустой файл
        String emptyPath = "src/test/resources/empty2.json";
        Files.writeString(Paths.get(emptyPath), "{}");
        
        try {
            String result = Differ.generate(emptyPath, flat2Path);
            
            assertNotNull(result);
            // Все ключи должны быть добавлены
            assertTrue(result.contains("  + host: hexlet.io"));
            assertTrue(result.contains("  + timeout: 20"));
            assertTrue(result.contains("  + verbose: true"));
            assertFalse(result.contains("  - "));
        } finally {
            Files.deleteIfExists(Paths.get(emptyPath));
        }
    }
}
