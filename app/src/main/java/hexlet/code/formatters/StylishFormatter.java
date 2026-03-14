package hexlet.code.formatters;

import hexlet.code.model.DiffNode;
import hexlet.code.model.DiffType;
import java.util.List;
import java.util.Map;

public class StylishFormatter implements Formatter {
    
    @Override
    public String format(List<DiffNode> diff) {
        return formatDiff(diff, 0);
    }

    private String formatDiff(List<DiffNode> diff, int depth) {
        StringBuilder result = new StringBuilder("{\n");
        String indent = "  ".repeat(depth);
        String nextIndent = "  ".repeat(depth + 1);
        
        for (DiffNode node : diff) {
            String key = node.getKey();
            DiffType type = node.getType();
            Object oldValue = node.getOldValue();
            Object newValue = node.getNewValue();

            switch (type) {
                case ADDED:
                    result.append(nextIndent).append("+ ").append(key).append(": ")
                          .append(formatValue(newValue, depth + 1)).append("\n");
                    break;
                case REMOVED:
                    result.append(nextIndent).append("- ").append(key).append(": ")
                          .append(formatValue(oldValue, depth + 1)).append("\n");
                    break;
                case CHANGED:
                    result.append(nextIndent).append("- ").append(key).append(": ")
                          .append(formatValue(oldValue, depth + 1)).append("\n");
                    result.append(nextIndent).append("+ ").append(key).append(": ")
                          .append(formatValue(newValue, depth + 1)).append("\n");
                    break;
                case UNCHANGED:
                    result.append(nextIndent).append("  ").append(key).append(": ")
                          .append(formatValue(oldValue, depth + 1)).append("\n");
                    break;
                default:
                    break;
            }
        }
        
        result.append(indent).append("}");
        return result.toString();
    }

    private String formatValue(Object value, int depth) {
        if (value == null) {
            return "null";
        }
        
        if (value instanceof String) {
            return (String) value;
        }
        
        if (value instanceof Map || value instanceof List) {
            return "[complex value]";
        }
        
        return value.toString();
    }
}
