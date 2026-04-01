package hexlet.code.parsers;

public class ParserFactory {

    public static Parser getParser(String format) {
        if (format == null || format.isEmpty()) {
            throw new IllegalArgumentException("Format cannot be null or empty");
        }

        return switch (format.toLowerCase()) {
            case "json" -> new JsonParser();
            case "yml", "yaml" -> new YamlParser();
            default -> throw new IllegalArgumentException("Unsupported data format: " + format);
        };
    }
}
