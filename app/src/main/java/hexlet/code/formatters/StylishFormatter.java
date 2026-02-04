package hexlet.code.formatters;

import hexlet.code.model.DiffNode;
import hexlet.code.model.DiffType;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class StylishFormatter implements Formatter {
    
    @Override
    public String format(List<DiffNode> diffNodes) {
        Map<String, String> lines = new TreeMap<>();
        buildLines(diffNodes, "", lines);
        return "{\n" + String.join("\n", lines.values()) + "\n}";
    }
    
    private static void buildLines(List<DiffNode> diff, String parentKey, Map<String, String> lines) {
        for (DiffNode node : diff) {
            String key = parentKey.isEmpty() ? node.getKey() : parentKey + "." + node.getKey();
            DiffType type = node.getType();
            Object oldValue = node.getOldValue();
            Object newValue = node.getNewValue();
            
            switch (type) {
                case UNCHANGED:
                    lines.put(key, "    " + node.getKey() + ": " + formatValue(oldValue));
                    break;
                case ADDED:
                    lines.put(key, "  + " + node.getKey() + ": " + formatValue(newValue));
                    break;
                case REMOVED:
                    lines.put(key, "  - " + node.getKey() + ": " + formatValue(oldValue));
                    break;
                case CHANGED:
                    lines.put(key, "  - " + node.getKey() + ": " + formatValue(oldValue));
                    lines.put(key + "_new", "  + " + node.getKey() + ": " + formatValue(newValue));
                    break;
                default:
                    throw new IllegalArgumentException("Unknown type: " + type);
            }
        }
    }
    
    private static String formatValue(Object value) {
        if (value == null) {
            return "null";
        }
        
        if (value instanceof Map) {
            // Форматируем Map как {key1=value1, key2=value2}
            Map<?, ?> map = (Map<?, ?>) value;
            StringBuilder sb = new StringBuilder("{");
            boolean first = true;
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                if (!first) {
                    sb.append(", ");
                }
                sb.append(entry.getKey()).append("=").append(formatSimpleValue(entry.getValue()));
                first = false;
            }
            sb.append("}");
            return sb.toString();
        }
        
        if (value instanceof List) {
            // Форматируем List как [item1, item2, item3]
            List<?> list = (List<?>) value;
            StringBuilder sb = new StringBuilder("[");
            boolean first = true;
            for (Object item : list) {
                if (!first) {
                    sb.append(", ");
                }
                sb.append(formatSimpleValue(item));
                first = false;
            }
            sb.append("]");
            return sb.toString();
        }
        
        return formatSimpleValue(value);
    }
    
    private static String formatSimpleValue(Object value) {
        if (value == null) {
            return "null";
        }
        if (value instanceof String) {
            return value.toString();
        }
        return String.valueOf(value);
    }
}
