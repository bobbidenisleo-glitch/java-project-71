package hexlet.code;

import hexlet.code.model.DiffNode;
import hexlet.code.model.DiffType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.Objects;

public class Comparator {
    
    public static List<DiffNode> compare(Map<String, Object> map1, Map<String, Object> map2) {
        List<DiffNode> result = new ArrayList<>();
        
        // Используем TreeSet для автоматической сортировки ключей
        TreeSet<String> allKeys = new TreeSet<>();
        allKeys.addAll(map1.keySet());
        allKeys.addAll(map2.keySet());
        
        for (String key : allKeys) {
            Object value1 = map1.get(key);
            Object value2 = map2.get(key);
            
            if (!map2.containsKey(key)) {
                // Ключ удалён из второго файла
                result.add(new DiffNode(key, DiffType.REMOVED, value1, null));
            } else if (!map1.containsKey(key)) {
                // Ключ добавлен во второй файл
                result.add(new DiffNode(key, DiffType.ADDED, null, value2));
            } else if (!Objects.equals(value1, value2)) {
                // Значение изменено
                result.add(new DiffNode(key, DiffType.CHANGED, value1, value2));
            } else {
                // Значение без изменений
                result.add(new DiffNode(key, DiffType.UNCHANGED, value1, value2));
            }
        }
        
        return result;
    }
}
