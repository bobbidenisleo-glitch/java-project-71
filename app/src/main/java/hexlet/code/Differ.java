package hexlet.code;

import hexlet.code.model.DiffNode;
import hexlet.code.parsers.Parser;
import hexlet.code.parsers.ParserFactory;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class Differ {

    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }

    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        Map<String, Object> data1 = parseFile(filePath1);
        Map<String, Object> data2 = parseFile(filePath2);

        List<DiffNode> diffNodes = Comparator.compare(data1, data2);

        return Formatter.format(diffNodes, format);
    }

    private static Map<String, Object> parseFile(String filePath) throws Exception {
        String content = Files.readString(Path.of(filePath));
        String dataFormat = getDataFormat(filePath);

        if (content == null
                || content.trim().isEmpty()
                || content.trim().equals("{}")
                || content.trim().equals("---")) {
            return java.util.Collections.emptyMap();
        }

        Parser parser = ParserFactory.getParser(dataFormat);
        return parser.parse(content);
    }

    private static String getDataFormat(String filePath) {
        int dotIndex = filePath.lastIndexOf('.');
        if (dotIndex > 0 && dotIndex < filePath.length() - 1) {
            return filePath.substring(dotIndex + 1).toLowerCase();
        }
        throw new IllegalArgumentException("Cannot determine data format from: " + filePath);
    }
}
