package hexlet.code;

import hexlet.code.formatters.StylishFormatter;
import hexlet.code.model.DiffNode;
import hexlet.code.model.DiffType;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class StylishFormatterTest {
    
    @Test
    void testFormat() {
        List<DiffNode> diff = Arrays.asList(
            new DiffNode("follow", DiffType.REMOVED, false, null),
            new DiffNode("host", DiffType.UNCHANGED, "hexlet.io", "hexlet.io"),
            new DiffNode("proxy", DiffType.REMOVED, "123.234.53.22", null),
            new DiffNode("timeout", DiffType.CHANGED, 50, 20),
            new DiffNode("verbose", DiffType.ADDED, null, true)
        );
        
        StylishFormatter formatter = new StylishFormatter();
        String result = formatter.format(diff);
        
        System.out.println("=== Форматированный вывод ===");
        System.out.println(result);
        
        assertNotNull(result);
        assertTrue(result.contains("  - follow: false"));
        assertTrue(result.contains("    host: hexlet.io"));
        assertTrue(result.contains("  - proxy: 123.234.53.22"));
        assertTrue(result.contains("  - timeout: 50"));
        assertTrue(result.contains("  + timeout: 20"));
        assertTrue(result.contains("  + verbose: true"));
        assertTrue(result.startsWith("{"));
        assertTrue(result.endsWith("}"));
    }
}
