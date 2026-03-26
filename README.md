[![Actions Status](https://github.com/bobbidenisleo-glitch/java-project-71/workflows/hexlet-check/badge.svg)](https://github.com/bobbidenisleo-glitch/java-project-71/actions)
[![Maintainability](https://api.codeclimate.com/v1/badges/ваш-идентификатор/maintainability)](https://codeclimate.com/github/bobbidenisleo-glitch/java-project-71/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/ваш-идентификатор/test_coverage)](https://codeclimate.com/github/bobbidenisleo-glitch/java-project-71/test_coverage)


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

## Пример сравнения файлов со вложенными структурами

Проект поддерживает сравнение файлов с вложенными структурами.

Пример вывода stylish формата:
{
    chars1: [a, b, c]
  - chars2: [d, e, f]
  + chars2: false
  - checked: false
  + checked: true
}
## Формат вывода "plain"

Плоский формат предназначен для интеграции с другими системами:

```bash
./build/install/app/bin/app -f plain file1.json file2.json

Пример вывода:
text

Property 'chars2' was updated. From [complex value] to false
Property 'checked' was updated. From false to true
Property 'default' was updated. From null to [complex value]
Property 'id' was updated. From 45 to null
Property 'key1' was removed
Property 'key2' was added with value: 'value2'

Особенности plain формата:

    Простые значения выводятся как есть (строки в кавычках)

    Составные значения (объекты/массивы) обозначаются как [complex value]

    Для вложенных свойств используется точечная нотация: common.setting1

    Изменения: Property 'key' was updated. From old to new

    Добавления: Property 'key' was added with value: value

    Удаления: Property 'key' was removed
