package hexlet.code.parsers;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;

public final class JsonParser implements Parser {
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public Map<String, Object> parse(String content) throws Exception {
        return mapper.readValue(content, Map.class);
    }
}
