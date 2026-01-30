package hexlet.code;

import java.util.Map;

public class Differ {
    
    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }
    
    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        // Читаем и парсим оба файла
        Map<String, Object> data1 = Parser.parse(filePath1);
        Map<String, Object> data2 = Parser.parse(filePath2);
        
        // TODO: Реализовать сравнение и форматирование
        return formatDiff(data1, data2, format);
    }
    
    private static String formatDiff(Map<String, Object> data1, 
                                    Map<String, Object> data2, 
                                    String format) {
        // Временная реализация - просто покажем что прочитали
        StringBuilder result = new StringBuilder();
        result.append("File 1 data: ").append(data1).append("\n");
        result.append("File 2 data: ").append(data2).append("\n");
        result.append("Format: ").append(format).append("\n");
        
        // Простое сравнение для теста
        result.append("\nSimple comparison:\n");
        for (String key : data1.keySet()) {
            if (!data2.containsKey(key)) {
                result.append("- ").append(key).append(": ").append(data1.get(key)).append("\n");
            } else if (!data1.get(key).equals(data2.get(key))) {
                result.append("~ ").append(key).append(": ").append(data1.get(key))
                      .append(" -> ").append(data2.get(key)).append("\n");
            } else {
                result.append("  ").append(key).append(": ").append(data1.get(key)).append("\n");
            }
        }
        for (String key : data2.keySet()) {
            if (!data1.containsKey(key)) {
                result.append("+ ").append(key).append(": ").append(data2.get(key)).append("\n");
            }
        }
        
        return result.toString();
    }
}
