.PHONY: build run test clean installDist run-dist

# Сборка проекта
build:
	./gradlew build

# Запуск через gradle
run:
	./gradlew run

# Запуск тестов
test:
	./gradlew test

# Очистка проекта
clean:
	./gradlew clean

# Создание дистрибутива
installDist:
	./gradlew installDist

# Запуск исполняемого файла (основная задача)
run-dist:
	./build/install/app/bin/app

# Проверка обновлений зависимостей
check-updates:
	./gradlew dependencyUpdates
