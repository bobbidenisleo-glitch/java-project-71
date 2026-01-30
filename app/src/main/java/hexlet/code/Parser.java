package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.util.Map;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Parser {
    
    public static Map<String, Object> parse(String filePath) throws Exception {
        // Получаем абсолютный путь
        Path path = Paths.get(filePath).toAbsolutePath().normalize();
        
        // Читаем содержимое файла
        String content = Files.readString(path);
        
        // Определяем формат файла по расширению
        String fileName = path.getFileName().toString();
        
        if (fileName.endsWith(".json")) {
            return parseJson(content);
        } else if (fileName.endsWith(".yaml") || fileName.endsWith(".yml")) {
            return parseYaml(content);
        } else {
            throw new IllegalArgumentException("Unsupported file format: " + fileName);
        }
    }
    
    private static Map<String, Object> parseJson(String content) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(content, new TypeReference<Map<String, Object>>() {});
    }
    
    private static Map<String, Object> parseYaml(String content) throws Exception {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        return mapper.readValue(content, new TypeReference<Map<String, Object>>() {});
    }
}
