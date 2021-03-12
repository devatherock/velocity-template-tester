FROM adoptopenjdk/openjdk11-openj9:jre-11.0.8_10_openj9-0.21.0-alpine
COPY build/libs/velocity-template-tester-*-all.jar velocity-template-tester.jar
EXPOSE 8080
CMD java -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap \
    -Dcom.sun.management.jmxremote -noverify ${JAVA_OPTS} -jar velocity-template-tester.jar
