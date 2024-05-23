FROM eclipse-temurin:17.0.11_9-jre-jammy

RUN apt-get update \
	&& apt-get install dumb-init -y

EXPOSE 8080

COPY docker/entry-point.sh entry-point.sh
COPY build/libs/velocity-template-tester-*-all.jar velocity-template-tester.jar

ENTRYPOINT ["/usr/bin/dumb-init", "--"]
CMD ["/bin/sh", "-c", "/entry-point.sh"]
