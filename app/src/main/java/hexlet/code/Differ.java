package hexlet.code;
import hexlet.code.Comparator;
import hexlet.code.formatters.StylishFormatter;
import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.JsonFormatter;
import hexlet.code.model.DiffNode;
import hexlet.code.parsers.Parser;
import hexlet.code.parsers.JsonParser;
import hexlet.code.parsers.YamlParser;
import hexlet.code.model.DiffType;
import hexlet.code.parsers.ParserFactory;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class Differ {
    
    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }
    
    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        Map<String, Object> data1 = parseFile(filePath1);
        Map<String, Object> data2 = parseFile(filePath2);
        
        List<DiffNode> diffNodes = Comparator.compare(data1, data2);
        hexlet.code.formatters.Formatter formatter = getFormatter(format);
        
        return formatter.format(diffNodes);
    }
    
    private static Map<String, Object> parseFile(String filePath) throws IOException {
        try {
            String content = Files.readString(Path.of(filePath));
            String extension = getFileExtension(filePath);
            
            // Обработка пустых файлов
            if (content == null || content.trim().isEmpty() || 
                    content.trim().equals("{}") || content.trim().equals("---")) {
                return java.util.Collections.emptyMap();
            }
            
            Parser parser = ParserFactory.getParser(filePath);
            return parser.parse(content);
        } catch (Exception e) {
            throw new IOException("Error parsing file: " + filePath, e);
        }
    }
    
    private static String getFileExtension(String filePath) {
        int dotIndex = filePath.lastIndexOf('.');
        return dotIndex > 0 ? filePath.substring(dotIndex + 1).toLowerCase() : "";
    }
    
    
    
    private static boolean isEqual(Object obj1, Object obj2) {
        if (obj1 == null && obj2 == null) {
            return true;
        }
        if (obj1 == null || obj2 == null) {
            return false;
        }
        
        // Для вложенных структур используем сравнение через toString
        return obj1.toString().equals(obj2.toString());
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
