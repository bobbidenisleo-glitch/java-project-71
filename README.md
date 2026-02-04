# JSON/YAML Comparator

[![Java CI](https://github.com/bobbidenisleo-glitch/java-project-71/actions/workflows/ci.yml/badge.svg)](https://github.com/bobbidenisleo-glitch/java-project-71/actions/workflows/ci.yml)

## Описание проекта
Утилита командной строки для сравнения JSON и YAML файлов с выводом различий в трех форматах.

## Функциональность
- Сравнение плоских JSON файлов
- Сравнение YAML файлов
- Вывод различий в форматах: stylish (по умолчанию), plain, json
- Автоматическое определение формата файлов

## Использование

### Сборка и тестирование
```bash
# Сборка проекта
make build

# Запуск всех тестов
make test

# Проверка стиля кода
make checkstyle

# Очистка проекта
make clean
```

### Запуск приложения
```bash
# Сравнение двух файлов
java -jar app/build/libs/app.jar file1.json file2.json

# С указанием формата вывода
java -jar app/build/libs/app.jar file1.yml file2.yml --format plain
```

## Технологии
- **Java 17** - язык программирования
- **JUnit 5** - фреймворк для тестирования
- **Gradle** - система сборки
- **Checkstyle** - проверка стиля кода
- **GitHub Actions** - непрерывная интеграция

## Структура проекта
```
java-project-71/
├── .github/workflows/ci.yml
├── config/checkstyle/checkstyle.xml
├── app/build.gradle.kts
├── Makefile
└── app/src/
    ├── main/java/hexlet/code/
    └── test/java/hexlet/code/
```

## Автор
[bobbidenisleo-glitch](https://github.com/bobbidenisleo-glitch)

## Лицензия
MIT
