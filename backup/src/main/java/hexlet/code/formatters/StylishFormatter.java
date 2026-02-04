package hexlet.code.formatters;

import hexlet.code.model.DiffNode;
import hexlet.code.model.DiffType;
import java.util.List;

public class StylishFormatter implements Formatter {
    @Override
    public String format(List<DiffNode> diffNodes) {
        StringBuilder result = new StringBuilder("{\n");
        
        for (DiffNode node : diffNodes) {
            String key = node.getKey();
            DiffType type = node.getType();
            Object oldValue = node.getOldValue();
            Object newValue = node.getNewValue();
            
            switch (type) {
                case ADDED:
                    result.append("  + ").append(key).append(": ").append(formatValue(newValue)).append("\n");
                    break;
                case REMOVED:
                    result.append("  - ").append(key).append(": ").append(formatValue(oldValue)).append("\n");
                    break;
                case CHANGED:
                    result.append("  - ").append(key).append(": ").append(formatValue(oldValue)).append("\n");
                    result.append("  + ").append(key).append(": ").append(formatValue(newValue)).append("\n");
                    break;
                case UNCHANGED:
                    result.append("    ").append(key).append(": ").append(formatValue(oldValue)).append("\n");
                    break;
            }
        }
        
        result.append("}");
        return result.toString();
    }
    
    private String formatValue(Object value) {
        if (value == null) {
            return "null";
        }
        if (value instanceof String) {
            return value.toString();
        }
        return String.valueOf(value);
    }
}
