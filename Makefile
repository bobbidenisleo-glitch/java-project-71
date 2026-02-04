.PHONY: build

install:
	./gradlew installDist

build:
	./gradlew build

clean:
	./gradlew clean

test:
	./gradlew test

checkstyle:
	./gradlew checkstyleMain checkstyleTest

run-dist:
	./app/build/install/app/bin/app

run:
	./gradlew run

lint:
	./gradlew checkstyleMain
