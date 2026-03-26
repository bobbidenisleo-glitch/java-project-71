package hexlet.code.formatters;

import hexlet.code.model.DiffNode;
import hexlet.code.model.DiffType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class JsonFormatterTest {
    
    @Test
    void testFormatJson() throws Exception {
        List<DiffNode> diff = new ArrayList<>();
        diff.add(new DiffNode("key1", DiffType.ADDED, null, "value1"));
        diff.add(new DiffNode("key2", DiffType.REMOVED, "value2", null));
        diff.add(new DiffNode("key3", DiffType.CHANGED, "old", "new"));
        
        JsonFormatter formatter = new JsonFormatter();
        String result = formatter.format(diff);
        
        assertNotNull(result);
        assertTrue(result.contains("key1"));
        assertTrue(result.contains("ADDED"));
        assertTrue(result.contains("value1"));
        assertTrue(result.contains("key2"));
        assertTrue(result.contains("REMOVED"));
        assertTrue(result.contains("key3"));
        assertTrue(result.contains("CHANGED"));
        assertTrue(result.contains("old"));
        assertTrue(result.contains("new"));
    }
}
