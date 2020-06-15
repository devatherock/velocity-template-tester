FROM oracle/graalvm-ce:20.0.0-java11 as graalvm
RUN gu install native-image

COPY . /home/app/micronaut-graal-app
WORKDIR /home/app/micronaut-graal-app

RUN native-image --no-server -cp build/libs/*-all.jar


FROM frolvlad/alpine-glibc

LABEL maintainer="devatherock@gmail.com"
LABEL io.github.devatherock.version="0.6.1"

EXPOSE 8080
RUN apk update \
		&& apk add --no-cache libstdc++ dumb-init

COPY --from=graalvm /home/app/micronaut-graal-app/micronautgraalapp /micronaut-graal-app/micronautgraalapp
ENTRYPOINT ["dumb-init", "--", "/micronaut-graal-app/micronautgraalapp"]