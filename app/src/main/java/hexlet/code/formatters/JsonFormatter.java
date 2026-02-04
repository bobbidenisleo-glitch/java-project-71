package hexlet.code.formatters;

import hexlet.code.model.DiffNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class JsonFormatter implements Formatter {
    @Override
    public String format(List<DiffNode> diffNodes) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(diffNodes);
        } catch (Exception e) {
            throw new RuntimeException("Error formatting to JSON", e);
        }
    }
}
