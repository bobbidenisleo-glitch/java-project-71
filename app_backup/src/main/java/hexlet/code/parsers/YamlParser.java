package hexlet.code.parsers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.util.Map;

public final class YamlParser implements Parser {
    private final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

    @Override
    public Map<String, Object> parse(String content) throws Exception {
        return mapper.readValue(content, Map.class);
    }
}
