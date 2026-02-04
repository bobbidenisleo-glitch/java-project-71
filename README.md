# Diff Calculator (Вычислитель отличий)

![Java CI](https://github.com/bobbidenisleo-glitch/java-project-71/actions/workflows/ci.yml/badge.svg)
![Checkstyle](https://img.shields.io/badge/checkstyle-passing-brightgreen)
![Tests](https://img.shields.io/badge/tests-passing-brightgreen)
![Java](https://img.shields.io/badge/Java-21-blue)
![Gradle](https://img.shields.io/badge/Gradle-8.7-green)

Программа для сравнения двух конфигурационных файлов (JSON, YAML).

## 📦 Установка

```bash
git clone https://github.com/bobbidenisleo-glitch/java-project-71.git
cd java-project-71
./gradlew installDist
```

## 🚀 Использование

### Базовая команда:
```bash
./app/build/install/app/bin/app file1.json file2.json
```

### Опции:
```bash
# Справка
./app/build/install/app/bin/app -h

# Версия
./app/build/install/app/bin/app -V

# Указание формата вывода
./app/build/install/app/bin/app -f stylish file1.json file2.json
```

## 📊 Пример работы

### Сравнение плоских JSON файлов:

**file1.json:**
```json
{
  "host": "hexlet.io",
  "timeout": 50,
  "proxy": "123.234.53.22",
  "follow": false
}
```

**file2.json:**
```json
{
  "timeout": 20,
  "verbose": true,
  "host": "hexlet.io"
}
```

**Результат:**
```bash
$ ./app/build/install/app/bin/app file1.json file2.json
{
  - follow: false
    host: hexlet.io
  - proxy: 123.234.53.22
  - timeout: 50
  + timeout: 20
  + verbose: true
}
```

## 🎥 Демонстрация

[![asciicast](https://asciinema.org/a/NFIQgLVMu1ymFsqg4ESeOQeXi.svg)](https://asciinema.org/a/NFIQgLVMu1ymFsqg4ESeOQeXi)

## 🛠 Разработка

```bash
# Запуск тестов
./gradlew test

# Проверка стиля кода
./gradlew checkstyleMain checkstyleTest

# Полная сборка
./gradlew build

# Отчёт покрытия кода
./gradlew jacocoTestReport

# Через Makefile
make test
make checkstyle
make build
```

## 📜 Лицензия

MIT
