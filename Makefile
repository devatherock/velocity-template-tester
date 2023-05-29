DOCKER_TAG=latest

clean:
	./gradlew clean
docker-build:
	./gradlew clean build
	docker build -t devatherock/velocity-template-tester:$(DOCKER_TAG) -f docker/Dockerfile .	
docker-run:
	docker run --rm \
	-p 8080:8080 \
	devatherock/velocity-template-tester:$(DOCKER_TAG)
integration-test:
	docker-compose up &
	./gradlew integrationTest
	docker-compose down	