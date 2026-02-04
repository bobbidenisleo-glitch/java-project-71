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
            
            switch (type) {
                case UNCHANGED:
                    lines.put(key, "    " + node.getKey() + ": " + formatValue(node.getOldValue()));
                    break;
                case ADDED:
                    lines.put(key, "  + " + node.getKey() + ": " + formatValue(node.getNewValue()));
                    break;
                case REMOVED:
                    lines.put(key, "  - " + node.getKey() + ": " + formatValue(node.getOldValue()));
                    break;
                case CHANGED:
                    lines.put(key, "  - " + node.getKey() + ": " + formatValue(node.getOldValue()));
                    lines.put(key + "_new", "  + " + node.getKey() + ": " + formatValue(node.getNewValue()));
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
        if (value instanceof String) {
            return value.toString();
        }
        return String.valueOf(value);
    }
}
