package hexlet.code;

import hexlet.code.formatters.Formatter;
import hexlet.code.formatters.StylishFormatter;
import hexlet.code.model.DiffNode;

import java.util.List;
import java.util.Map;

public class Differ {
    
    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }
    
    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        // Читаем и парсим оба файла
        Map<String, Object> data1 = Parser.parse(filePath1);
        Map<String, Object> data2 = Parser.parse(filePath2);
        
        // Сравниваем данные
        List<DiffNode> diff = Comparator.compare(data1, data2);
        
        // Форматируем результат
        Formatter formatter = getFormatter(format);
        return formatter.format(diff);
    }
    
    private static Formatter getFormatter(String format) {
        switch (format.toLowerCase()) {
            case "stylish":
                return new StylishFormatter();
            case "plain":
            case "json":
            default:
                // Пока только stylish поддерживается
                throw new IllegalArgumentException("Unsupported format: " + format + 
                                                  ". Only 'stylish' is supported for now.");
        }
    }
}
