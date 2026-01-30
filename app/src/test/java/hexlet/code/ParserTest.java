package hexlet.code;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import java.util.Map;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;

class ParserTest {
    
    private static String getTestResourcePath(String fileName) {
        return Paths.get("src", "test", "resources", fileName)
                   .toAbsolutePath()
                   .toString();
    }
    
    @Test
    void testParseJson() throws Exception {
        String path = getTestResourcePath("file1.json");
        Map<String, Object> data = Parser.parse(path);
        assertNotNull(data);
        assertEquals("hexlet.io", data.get("host"));
        assertEquals(50, data.get("timeout"));
        assertEquals("123.234.53.22", data.get("proxy"));
        assertEquals(false, data.get("follow"));
    }
    
    @Test
    void testParseYaml() throws Exception {
        String path = getTestResourcePath("file1.yml");
        Map<String, Object> data = Parser.parse(path);
        assertNotNull(data);
        assertEquals("hexlet.io", data.get("host"));
        assertEquals(50, data.get("timeout"));
    }
}
