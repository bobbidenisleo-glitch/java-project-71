package hexlet.code.formatters;

import hexlet.code.model.DiffNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

public class JsonFormatter implements Formatter {
    
    @Override
    public String format(List<DiffNode> diff) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(diff);
    }
}
