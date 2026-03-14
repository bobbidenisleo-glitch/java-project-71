package hexlet.code.formatters;

import hexlet.code.model.DiffNode;
import hexlet.code.model.DiffType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PlainFormatterTest {
    
    private List<DiffNode> diff;
    private PlainFormatter formatter;

    @BeforeEach
    void setUp() {
        diff = new ArrayList<>();
        formatter = new PlainFormatter();
    }

    private DiffNode createNode(String key, DiffType type, Object oldValue, Object newValue) {
        return new DiffNode(key, type, oldValue, newValue);
    }

    @Test
    void testFormatAdded() throws Exception {
        DiffNode node = createNode("key1", DiffType.ADDED, null, "value1");
        diff.add(node);

        String result = formatter.format(diff);
        assertEquals("Property 'key1' was added with value: 'value1'", result);
    }

    @Test
    void testFormatRemoved() throws Exception {
        DiffNode node = createNode("key2", DiffType.REMOVED, "value1", null);
        diff.add(node);

        String result = formatter.format(diff);
        assertEquals("Property 'key2' was removed", result);
    }

    @Test
    void testFormatChanged() throws Exception {
        DiffNode node = createNode("key3", DiffType.CHANGED, "old value", "new value");
        diff.add(node);

        String result = formatter.format(diff);
        assertEquals("Property 'key3' was updated. From 'old value' to 'new value'", result);
    }

    @Test
    void testFormatWithComplexValues() throws Exception {
        Map<String, String> complexMap = new HashMap<>();
        complexMap.put("nested", "value");
        
        DiffNode node = createNode("complex", DiffType.CHANGED, complexMap, List.of(1, 2, 3));
        diff.add(node);

        String result = formatter.format(diff);
        assertEquals("Property 'complex' was updated. From [complex value] to [complex value]", result);
    }

    @Test
    void testFormatWithNull() throws Exception {
        DiffNode node = createNode("nullable", DiffType.CHANGED, 45, null);
        diff.add(node);

        String result = formatter.format(diff);
        assertEquals("Property 'nullable' was updated. From 45 to null", result);
    }

    @Test
    void testFormatMultipleLines() throws Exception {
        diff.add(createNode("key1", DiffType.ADDED, null, "value1"));
        diff.add(createNode("key2", DiffType.REMOVED, "value1", null));
        diff.add(createNode("key3", DiffType.CHANGED, "old", "new"));

        String result = formatter.format(diff);
        String[] lines = result.split("\n");
        
        assertEquals(3, lines.length);
        assertEquals("Property 'key1' was added with value: 'value1'", lines[0]);
        assertEquals("Property 'key2' was removed", lines[1]);
        assertEquals("Property 'key3' was updated. From 'old' to 'new'", lines[2]);
    }

    @Test
    void testFormatIgnoresUnchanged() throws Exception {
        diff.add(createNode("unchanged", DiffType.UNCHANGED, "value", "value"));

        String result = formatter.format(diff);
        assertTrue(result.isEmpty());
    }

    @Test
    void testFormatNumbersAndBooleans() throws Exception {
        DiffNode node = createNode("settings", DiffType.CHANGED, 200, true);
        diff.add(node);

        String result = formatter.format(diff);
        assertEquals("Property 'settings' was updated. From 200 to true", result);
    }
}
