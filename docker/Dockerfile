FROM eclipse-temurin:17.0.8_7-jre-jammy
COPY build/libs/velocity-template-tester-*-all.jar velocity-template-tester.jar
EXPOSE 8080
CMD java -XX:+UnlockExperimentalVMOptions \
    -Dcom.sun.management.jmxremote -noverify ${JAVA_OPTS} -jar velocity-template-tester.jar
