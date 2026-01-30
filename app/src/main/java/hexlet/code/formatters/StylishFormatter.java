package hexlet.code.formatters;

import hexlet.code.model.DiffNode;
import hexlet.code.model.DiffType;

import java.util.List;

public class StylishFormatter implements Formatter {
    
    @Override
    public String format(List<DiffNode> diff) {
        StringBuilder result = new StringBuilder();
        result.append("{\n");
        
        for (DiffNode node : diff) {
            switch (node.getType()) {
                case UNCHANGED:
                    result.append("    ")
                          .append(node.getKey()).append(": ")
                          .append(formatValue(node.getOldValue()))
                          .append("\n");
                    break;
                    
                case ADDED:
                    result.append("  + ")
                          .append(node.getKey()).append(": ")
                          .append(formatValue(node.getNewValue()))
                          .append("\n");
                    break;
                    
                case REMOVED:
                    result.append("  - ")
                          .append(node.getKey()).append(": ")
                          .append(formatValue(node.getOldValue()))
                          .append("\n");
                    break;
                    
                case CHANGED:
                    // Сначала строка для удалённого значения
                    result.append("  - ")
                          .append(node.getKey()).append(": ")
                          .append(formatValue(node.getOldValue()))
                          .append("\n");
                    // Затем строка для добавленного значения
                    result.append("  + ")
                          .append(node.getKey()).append(": ")
                          .append(formatValue(node.getNewValue()))
                          .append("\n");
                    break;
                    
                default:
                    // NESTED пока не обрабатываем
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
        
        // Для boolean и чисел выводим как есть
        if (value instanceof Boolean || value instanceof Number) {
            return value.toString();
        }
        
        // Для строк
        return value.toString();
    }
}
