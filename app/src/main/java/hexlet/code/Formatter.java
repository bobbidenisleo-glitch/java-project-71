package hexlet.code;

import hexlet.code.formatters.StylishFormatter;
import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.JsonFormatter;
import java.util.List;
import hexlet.code.model.DiffNode;

public class Formatter {

    public static String format(List<DiffNode> diff, String formatName) throws Exception {
        hexlet.code.formatters.Formatter formatter = getFormatter(formatName);
        return formatter.format(diff);
    }

    private static hexlet.code.formatters.Formatter getFormatter(String format) {
        return switch (format.toLowerCase()) {
            case "stylish" -> new StylishFormatter();
            case "plain" -> new PlainFormatter();
            case "json" -> new JsonFormatter();
            default -> throw new IllegalArgumentException(
                "Unsupported format: " + format + ". Supported formats: 'stylish', 'plain', 'json'");
        };
    }
}
