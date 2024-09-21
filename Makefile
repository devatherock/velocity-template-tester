docker_tag=latest
all=false
ui_host=http://localhost:8080
start_containers=true

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
	DOCKER_TAG=$(docker_tag) docker compose up --wait
	./gradlew integrationTest
	docker-compose down
ui-test:
ifeq ($(start_containers), true)
	DOCKER_TAG=$(docker_tag) docker compose up --wait
endif	
	UI_HOST=$(ui_host) npx playwright test
ifeq ($(start_containers), true)
	docker-compose down
endif			
ui-test-debug:
	UI_HOST=$(ui_host) npx playwright test --ui --headed
