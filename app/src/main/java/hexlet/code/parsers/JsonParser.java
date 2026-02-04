package hexlet.code.parsers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;

public class JsonParser implements Parser {
    private final ObjectMapper objectMapper = new ObjectMapper();
    
    @Override
    public Map<String, Object> parse(String content) throws Exception {
        return objectMapper.readValue(content, new TypeReference<Map<String, Object>>() {});
    }
}
