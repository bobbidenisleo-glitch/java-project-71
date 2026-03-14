package hexlet.code;

import hexlet.code.model.DiffNode;
import hexlet.code.formatters.StylishFormatter;
import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.JsonFormatter;

import java.util.List;

public class Formatter {
    public static String format(List<DiffNode> diff, String formatName) throws Exception {
        hexlet.code.formatters.Formatter formatter = switch (formatName) {
            case "stylish" -> new StylishFormatter();
            case "plain" -> new PlainFormatter();
            case "json" -> new JsonFormatter();
            default -> throw new IllegalArgumentException("Unknown format: " + formatName);
        };
        
        return formatter.format(diff);
    }
}
