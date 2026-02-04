#!/bin/bash

echo "Исправление импортов в тестовых файлах..."

# Список файлов для исправления
FILES=(
    "app/src/test/java/hexlet/code/StylishFormatterTest.java"
    "app/src/test/java/hexlet/code/DifferTest.java"
    "app/src/test/java/hexlet/code/FlatJsonTest.java"
    "app/src/test/java/hexlet/code/AppTest.java"
    "app/src/test/java/hexlet/code/ComparatorTest.java"
)

for file in "${FILES[@]}"; do
    if [ -f "$file" ]; then
        echo "  Исправляю $file"
        # Удаляем строку с import ....*;
        sed -i '/import org.junit.jupiter.api.Assertions\.\*;/d' "$file"
        
        # Определяем какие импорты нужны
        NEEDS_ASSERT_EQUALS=$(grep -q "assertEquals\|assertNotEquals" "$file" && echo "true")
        NEEDS_ASSERT_TRUE=$(grep -q "assertTrue\|assertFalse" "$file" && echo "true")
        NEEDS_ASSERT_NOT_NULL=$(grep -q "assertNotNull\|assertNull" "$file" && echo "true")
        
        # Создаем временный файл с правильными импортами
        TEMP_FILE="${file}.tmp"
        > "$TEMP_FILE"
        
        if [ "$NEEDS_ASSERT_EQUALS" = "true" ]; then
            echo "import static org.junit.jupiter.api.Assertions.assertEquals;" >> "$TEMP_FILE"
        fi
        if [ "$NEEDS_ASSERT_TRUE" = "true" ]; then
            echo "import static org.junit.jupiter.api.Assertions.assertTrue;" >> "$TEMP_FILE"
            echo "import static org.junit.jupiter.api.Assertions.assertFalse;" >> "$TEMP_FILE"
        fi
        if [ "$NEEDS_ASSERT_NOT_NULL" = "true" ]; then
            echo "import static org.junit.jupiter.api.Assertions.assertNotNull;" >> "$TEMP_FILE"
        fi
        
        # Добавляем остальное содержимое файла
        cat "$file" >> "$TEMP_FILE"
        
        # Заменяем оригинальный файл
        mv "$TEMP_FILE" "$file"
    fi
done

echo "Готово!"
