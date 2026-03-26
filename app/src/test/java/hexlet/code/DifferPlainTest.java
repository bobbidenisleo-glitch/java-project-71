package hexlet.code;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DifferPlainTest {
    
    @TempDir
    Path tempDir;

    @Test
    void testGeneratePlainFormat() throws Exception {
        Path file1 = tempDir.resolve("file1.json");
        Path file2 = tempDir.resolve("file2.json");
        
        Files.writeString(file1, """
                {
                    "key1": "value1",
                    "key2": 45,
                    "key3": {
                        "nested": "value"
                    }
                }
                """);
        
        Files.writeString(file2, """
                {
                    "key1": "updated",
                    "key2": null,
                    "key4": "new"
                }
                """);

        String result = Differ.generate(file1.toString(), file2.toString(), "plain");
        
        String[] lines = result.split("\n");
        
        assertEquals("Property 'key1' was updated. From 'value1' to 'updated'", lines[0]);
        assertEquals("Property 'key2' was updated. From 45 to null", lines[1]);
        assertEquals("Property 'key3' was removed", lines[2]);
        assertEquals("Property 'key4' was added with value: 'new'", lines[3]);
    }

    @Test
    void testGeneratePlainUnknownFormat() {
        Exception exception = org.junit.jupiter.api.Assertions.assertThrows(Exception.class, () -> {
            Path file1 = tempDir.resolve("file1.json");
            Path file2 = tempDir.resolve("file2.json");
            
            Files.writeString(file1, "{}");
            Files.writeString(file2, "{}");
            
            Differ.generate(file1.toString(), file2.toString(), "unknown");
        });
        
        String message = exception.getMessage();
        assertTrue(message.contains("Unknown format") || 
                   message.contains("Unsupported format") ||
                   message.contains("unknown"));
    }
}
