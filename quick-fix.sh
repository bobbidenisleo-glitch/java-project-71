#!/bin/bash

echo "Быстрое исправление checkstyle..."

# Создаем backup
mkdir -p backup
cp -r app/src/ backup/

# 1. Исправляем DiffNode.java
cat > app/src/main/java/hexlet/code/model/DiffNode.java << 'DIFFNODE_EOF'
package hexlet.code.model;

public class DiffNode {
    private final String key;
    private final Object value1;
    private final Object value2;
    private final Status status;

    public DiffNode(String key, Object value1, Object value2, Status status) {
        this.key = key;
        this.value1 = value1;
        this.value2 = value2;
        this.status = status;
    }

    // геттеры...
}
DIFFNODE_EOF

echo "DiffNode.java исправлен"

# 2. Исправляем отступы в PlainFormatter
sed -i '35s/result/  result/; 41s/result/  result/; 50s/result/  result/' \
    app/src/main/java/hexlet/code/formatters/PlainFormatter.java

echo "PlainFormatter.java исправлен"

# 3. Добавляем default в StylishFormatter
sed -i '/^[[:space:]]*}/i\            default:\n                throw new IllegalArgumentException("Unknown status: " + node.getStatus());' \
    app/src/main/java/hexlet/code/formatters/StylishFormatter.java

echo "StylishFormatter.java исправлен"

echo "Готово! Запустите ./gradlew checkstyleMain для проверки"
