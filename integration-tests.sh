#!/bin/bash

docker_tag=$1
additional_gradle_args=$2
log_files=('logback-json.xml' 'https://raw.githubusercontent.com/devatherock/velocity-template-tester/master/src/main/resources/logback.xml')

set -e

for log_file in "${log_files[@]}"
do
  java_options="-Xmx640m -Dlogback.configurationFile=${log_file}"
  rm -rf logs-intg.txt
  JAVA_OPTS="${java_options}" DOCKER_TAG=${docker_tag} docker compose up --wait
  docker logs -f velocity-template-tester-intg > logs-intg.txt &
  JAVA_OPTS="${java_options}" ./gradlew clean integrationTest ${additional_gradle_args}
  docker-compose down
done
