package hexlet.code.model;

public final class DiffNode {
    private String key;
    private DiffType type;
    private Object oldValue;
    private Object newValue;

    public DiffNode(String key, DiffType type, Object oldValue, Object newValue) {
        this.key = key;
        this.type = type;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public String getKey() {
        return key;
    }

    public DiffType getType() {
        return type;
    }

    public Object getOldValue() {
        return oldValue;
    }

    public Object getNewValue() {
        return newValue;
    }
}
