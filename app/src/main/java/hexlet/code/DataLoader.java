package hexlet.code;

import hexlet.code.parsers.ParserFactory;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class DataLoader {
    
    public static Map<String, Object> load(String filePath) throws Exception {
        String content = Files.readString(Path.of(filePath));
        var parser = ParserFactory.getParser(filePath);
        return parser.parse(content);
    }
}
