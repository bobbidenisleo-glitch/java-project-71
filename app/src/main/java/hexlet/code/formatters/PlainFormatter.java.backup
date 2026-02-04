package hexlet.code.formatters;

import hexlet.code.model.DiffNode;
import hexlet.code.model.DiffType;
import java.util.List;

public class PlainFormatter implements Formatter {
    @Override
    public String format(List<DiffNode> diffNodes) {
        if (diffNodes == null || diffNodes.isEmpty()) {
            return "";
        }
        
        StringBuilder result = new StringBuilder();
        
        for (DiffNode node : diffNodes) {
            String key = node.getKey();
            DiffType type = node.getType();
            Object oldValue = node.getOldValue();
            Object newValue = node.getNewValue();
            
            if (type == DiffType.UNCHANGED) {
                continue;
            }
            
            if (result.length() > 0) {
                result.append("\n");
            }
            
            switch (type) {
                case ADDED:
                    result.append("Property '")
                          .append(key)
                          .append("' was added with value: ")
                          .append(formatValue(newValue));
                    break;
                    
                case REMOVED:
                    result.append("Property '")
                          .append(key)
                          .append("' was removed");
                    break;
                    
                case CHANGED:
                    result.append("Property '")
                          .append(key)
                          .append("' was updated. From ")
                          .append(formatValue(oldValue))
                          .append(" to ")
                          .append(formatValue(newValue));
                    break;
                    
                default:
                    break;
            }
        }
        
        return result.toString();
    }
    
    private String formatValue(Object value) {
        if (value == null) {
            return "null";
        }
        
        if (value instanceof String) {
            return "'" + value + "'";
        }
        
        if (value instanceof java.util.Map || value instanceof Iterable) {
            return "[complex value]";
        }
        
        return String.valueOf(value);
    }
}
