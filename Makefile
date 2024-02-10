docker_tag=latest
all=false

clean:
	./gradlew clean
check:
ifeq ($(all), true)
	yamllint -d relaxed . --no-warnings
endif
	./gradlew check	
fast-build:
	./gradlew build $(additional_gradle_args)
docker-build:
	docker build -t devatherock/velocity-template-tester:$(docker_tag) -f docker/Dockerfile .	
docker-run:
	docker run --rm \
	-p 8080:8080 \
	devatherock/velocity-template-tester:$(docker_tag)
integration-test:
	docker-compose up &
	./gradlew integrationTest
	docker-compose down	