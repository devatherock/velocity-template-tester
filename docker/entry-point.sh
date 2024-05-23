#!/bin/sh

java -XX:+UnlockExperimentalVMOptions \
    -Dcom.sun.management.jmxremote ${JAVA_OPTS} -jar velocity-template-tester.jar
