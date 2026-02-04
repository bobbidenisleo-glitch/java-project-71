package hexlet.code;

import hexlet.code.parsers.JsonParser;
import hexlet.code.parsers.YamlParser;
import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    
    @Test
    public void testParseJson() throws Exception {
        String json = "{\"host\": \"hexlet.io\", \"timeout\": 50}";
        JsonParser parser = new JsonParser();
        Map<String, Object> result = parser.parse(json);
        assertEquals("hexlet.io", result.get("host"));
        assertEquals(50, result.get("timeout"));
    }
    
    @Test
    public void testParseYaml() throws Exception {
        String yaml = "host: hexlet.io\ntimeout: 50";
        YamlParser parser = new YamlParser();
        Map<String, Object> result = parser.parse(yaml);
        assertEquals("hexlet.io", result.get("host"));
        assertEquals(50, result.get("timeout"));
    }
}
