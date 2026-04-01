package hexlet.code.formatters;

import hexlet.code.model.DiffNode;
import hexlet.code.model.DiffType;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class PlainFormatter implements Formatter {

    @Override
    public String format(List<DiffNode> diff) throws Exception {
        return formatDiff(diff, "");
    }

    private String formatDiff(List<DiffNode> diff, String path) {
        return diff.stream()
                .map(node -> formatNode(node, path))
                .filter(line -> !line.isEmpty())
                .collect(Collectors.joining("\n"));
    }

    private String formatNode(DiffNode node, String path) {
        String key = node.getKey();
        DiffType type = node.getType();
        Object oldValue = node.getOldValue();
        Object newValue = node.getNewValue();

        String fullPath = path.isEmpty() ? key : path + "." + key;

        switch (type) {
            case ADDED:
                return String.format("Property '%s' was added with value: %s",
                        fullPath, formatValue(newValue));
            case REMOVED:
                return String.format("Property '%s' was removed", fullPath);
            case CHANGED:
                return String.format("Property '%s' was updated. From %s to %s",
                        fullPath, formatValue(oldValue), formatValue(newValue));
            case UNCHANGED:
                return "";
            default:
                return "";
        }
    }

    private String formatValue(Object value) {
        if (value == null) {
            return "null";
        }
        if (value instanceof String) {
            return "'" + value + "'";
        }
        if (value instanceof Map || value instanceof List) {
            return "[complex value]";
        }
        return value.toString();
    }
}
