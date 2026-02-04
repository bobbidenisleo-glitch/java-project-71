package hexlet.code.parsers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.util.Map;

public class YamlParser implements Parser {
    private final ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
    
    @Override
    public Map<String, Object> parse(String content) throws Exception {
        return objectMapper.readValue(content, new TypeReference<Map<String, Object>>() {});
    }
}
