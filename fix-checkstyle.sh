#!/bin/bash

echo "=== Исправление checkstyle ошибок ==="

# 1. Исправляем звездочки в импортах тестовых файлов
echo "1. Исправляем импорты в тестовых файлах..."

# Список тестовых файлов
TEST_FILES=(
    "app/src/test/java/hexlet/code/AppTest.java"
    "app/src/test/java/hexlet/code/ComparatorTest.java"
    "app/src/test/java/hexlet/code/StylishFormatterTest.java"
    "app/src/test/java/hexlet/code/DifferTest.java"
    "app/src/test/java/hexlet/code/FlatJsonTest.java"
)

# Для каждого файла заменяем звездочки
for file in "${TEST_FILES[@]}"; do
    if [ -f "$file" ]; then
        echo "  Обрабатываем $file"
        
        # Создаем временный файл с исправленными импортами
        sed -i.bak '
            /import static org.junit.jupiter.api.Assertions\.\*;/{
                s/import static org.junit.jupiter.api.Assertions\.\*;//g
                a\
import static org.junit.jupiter.api.Assertions.assertEquals;\
import static org.junit.jupiter.api.Assertions.assertNotNull;\
import static org.junit.jupiter.api.Assertions.assertTrue;\
import static org.junit.jupiter.api.Assertions.assertFalse;
                d
            }
        ' "$file"
        
        # Удаляем бэкап файлы
        rm -f "$file.bak"
    fi
done

# 2. Исправляем отступы в ParserTest.java
echo "2. Исправляем отступы в ParserTest.java..."
if [ -f "app/src/test/java/hexlet/code/ParserTest.java" ]; then
    # Заменяем отступы для тройных кавычек
    sed -i.bak '
        /String json = """/,/""";/{
            s/^ \{12\}"""/            """/g
        }
        /String yaml = """/,/""";/{
            s/^ \{12\}"""/            """/g
        }
    ' "app/src/test/java/hexlet/code/ParserTest.java"
    rm -f "app/src/test/java/hexlet/code/ParserTest.java.bak"
fi

# 3. Исправляем DiffNode.java
echo "3. Исправляем DiffNode.java..."
if [ -f "app/src/main/java/hexlet/code/model/DiffNode.java" ]; then
    # Исправляем конструктор с фигурными скобками
    sed -i.bak '
        /this.key = key; this.value1 = value1; this.value2 = value2; this.status = status;/{
            s/this.key = key; this.value1 = value1; this.value2 = value2; this.status = status;/\
    this.key = key;\
    this.value1 = value1;\
    this.value2 = value2;\
    this.status = status;/g
        }
    ' "app/src/main/java/hexlet/code/model/DiffNode.java"
    rm -f "app/src/main/java/hexlet/code/model/DiffNode.java.bak"
fi

# 4. Исправляем PlainFormatter.java
echo "4. Исправляем отступы в PlainFormatter.java..."
if [ -f "app/src/main/java/hexlet/code/formatters/PlainFormatter.java" ]; then
    # Добавляем 2 пробела к строкам с методами
    sed -i.bak '
        35s/^ \{26\}result/                result/
        41s/^ \{26\}result/                result/
        50s/^ \{26\}result/                result/
    ' "app/src/main/java/hexlet/code/formatters/PlainFormatter.java"
    rm -f "app/src/main/java/hexlet/code/formatters/PlainFormatter.java.bak"
fi

# 5. Исправляем StylishFormatter.java
echo "5. Добавляем default в switch в StylishFormatter.java..."
if [ -f "app/src/main/java/hexlet/code/formatters/StylishFormatter.java" ]; then
    # Проверяем, есть ли уже default
    if ! grep -q "default:" "app/src/main/java/hexlet/code/formatters/StylishFormatter.java"; then
        # Добавляем default перед закрывающей скобкой switch
        sed -i.bak '
            /switch (node.getStatus()) {/,/^[[:space:]]*}/{
                /^[[:space:]]*}/i\
            default:\
                throw new IllegalArgumentException("Unknown status: " + node.getStatus());
            }
        ' "app/src/main/java/hexlet/code/formatters/StylishFormatter.java"
        rm -f "app/src/main/java/hexlet/code/formatters/StylishFormatter.java.bak"
    fi
fi

echo "=== Готово! Проверяем результаты... ==="

# Запускаем checkstyle для проверки
./gradlew checkstyleMain checkstyleTest --warning-mode none
