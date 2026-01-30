package hexlet.code.formatters;

import hexlet.code.model.DiffNode;
import java.util.List;

public interface Formatter {
    String format(List<DiffNode> diff);
}
