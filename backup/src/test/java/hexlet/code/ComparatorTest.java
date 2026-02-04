package hexlet.code;

import hexlet.code.model.DiffNode;
import hexlet.code.model.DiffType;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class ComparatorTest {
    
    @Test
    void testCompare() {
        Map<String, Object> map1 = new HashMap<>();
        map1.put("host", "hexlet.io");
        map1.put("timeout", 50);
        map1.put("proxy", "123.234.53.22");
        map1.put("follow", false);
        
        Map<String, Object> map2 = new HashMap<>();
        map2.put("host", "hexlet.io");
        map2.put("timeout", 20);
        map2.put("verbose", true);
        
        List<DiffNode> diff = Comparator.compare(map1, map2);
        
        assertEquals(5, diff.size());
        
        // Проверяем порядок (должен быть алфавитный)
        assertEquals("follow", diff.get(0).getKey());
        assertEquals(DiffType.REMOVED, diff.get(0).getType());
        
        assertEquals("host", diff.get(1).getKey());
        assertEquals(DiffType.UNCHANGED, diff.get(1).getType());
        
        assertEquals("proxy", diff.get(2).getKey());
        assertEquals(DiffType.REMOVED, diff.get(2).getType());
        
        assertEquals("timeout", diff.get(3).getKey());
        assertEquals(DiffType.CHANGED, diff.get(3).getType());
        
        assertEquals("verbose", diff.get(4).getKey());
        assertEquals(DiffType.ADDED, diff.get(4).getType());
    }
}
