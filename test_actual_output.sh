#!/bin/bash
cd app/src/test/resources
echo "=== Текущий вывод Differ ==="
java -cp "../../../build/classes/java/main:../../../build/libs/*" hexlet.code.Differ file1.yml file2.yml stylish 2>&1
echo "=== Ожидаемый вывод ==="
cat expected/yaml_stylish.txt
