package hexlet.code;

import hexlet.code.parsers.JsonParser;
import hexlet.code.parsers.YamlParser;
import hexlet.code.parsers.Parser;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserTest {
    
    @Test
    void testParseJson() throws Exception {
        String jsonContent = """
            {
              "host": "hexlet.io",
              "timeout": 50,
              "proxy": "123.234.53.22",
              "follow": false
            }
            """;
        
        Parser parser = new JsonParser();
        Map<String, Object> data = parser.parse(jsonContent);
        
        assertEquals("hexlet.io", data.get("host"));
        assertEquals(50, data.get("timeout"));
        assertEquals(false, data.get("follow"));
        assertEquals("123.234.53.22", data.get("proxy"));
    }
    
    @Test
    void testParseYaml() throws Exception {
        String yamlContent = """
            host: hexlet.io
            timeout: 50
            proxy: "123.234.53.22"
            follow: false
            """;
        
        Parser parser = new YamlParser();
        Map<String, Object> data = parser.parse(yamlContent);
        
        assertEquals("hexlet.io", data.get("host"));
        assertEquals(50, data.get("timeout"));
        assertEquals(false, data.get("follow"));
        assertEquals("123.234.53.22", data.get("proxy"));
    }
}
