package hexlet.code.model;

import java.util.ArrayList;
import java.util.List;

public class DiffNode {
    private String key;
    private DiffType type;
    private Object oldValue;
    private Object newValue;
    private List<DiffNode> children;
    
    public DiffNode(String key, DiffType type, Object oldValue, Object newValue) {
        this(key, type, oldValue, newValue, new ArrayList<>());
    }
    
    public DiffNode(String key, DiffType type, Object oldValue, Object newValue, 
                   List<DiffNode> children) {
        this.key = key;
        this.type = type;
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.children = children != null ? children : new ArrayList<>();
    }
    
    // Геттеры
    public String getKey() { return key; }
    public DiffType getType() { return type; }
    public Object getOldValue() { return oldValue; }
    public Object getNewValue() { return newValue; }
    public List<DiffNode> getChildren() { return children; }
    
    // Вспомогательные методы
    public boolean hasChildren() { return !children.isEmpty(); }
    
    @Override
    public String toString() {
        return String.format("DiffNode{key='%s', type=%s, oldValue=%s, newValue=%s}", 
            key, type, oldValue, newValue);
    }
}
