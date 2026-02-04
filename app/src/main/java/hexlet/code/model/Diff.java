package hexlet.code.model;

public class Diff {
    private final DiffType type;
    private final Object oldValue;
    private final Object newValue;
    
    public Diff(DiffType type, Object oldValue, Object newValue) {
        this.type = type;
        this.oldValue = oldValue;
        this.newValue = newValue;
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
