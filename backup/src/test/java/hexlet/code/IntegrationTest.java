package hexlet.code;

import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.File;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IntegrationTest {
    
    private String getResourcePath(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        return file.getAbsolutePath();
    }
    
    @Test
    public void testFullWorkflow() throws Exception {
        // Используем относительный путь от classpath
        String file1 = "file1.json";
        String file2 = "file2.json";
        
        // Получаем абсолютные пути
        String file1Path = getResourcePath(file1);
        String file2Path = getResourcePath(file2);
        
        System.out.println("File1 path: " + file1Path);
        System.out.println("File2 path: " + file2Path);
        
        // Проверяем существование файлов
        assertTrue(Files.exists(Paths.get(file1Path)), "File1 should exist: " + file1Path);
        assertTrue(Files.exists(Paths.get(file2Path)), "File2 should exist: " + file2Path);
        
        String result = Differ.generate(file1Path, file2Path, "stylish");
        assertNotNull(result);
        assertFalse(result.isEmpty());
        System.out.println("Result length: " + result.length());
    }
    
    @Test
    public void testYamlWorkflow() throws Exception {
        // Используем относительный путь от classpath
        String file1 = "file1.yml";
        String file2 = "file2.yml";
        
        // Получаем абсолютные пути
        String file1Path = getResourcePath(file1);
        String file2Path = getResourcePath(file2);
        
        System.out.println("File1 path: " + file1Path);
        System.out.println("File2 path: " + file2Path);
        
        // Проверяем существование файлов
        assertTrue(Files.exists(Paths.get(file1Path)), "File1 should exist: " + file1Path);
        assertTrue(Files.exists(Paths.get(file2Path)), "File2 should exist: " + file2Path);
        
        String result = Differ.generate(file1Path, file2Path, "stylish");
        assertNotNull(result);
        assertFalse(result.isEmpty());
        System.out.println("Result length: " + result.length());
    }
}
