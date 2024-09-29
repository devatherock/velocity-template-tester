package io.github.devatherock.velocity.tester.controller

import java.nio.file.Files
import java.nio.file.Paths

import io.micronaut.test.extensions.spock.annotation.MicronautTest

/**
 * Integration test for {@link VelocityController}
 */
@MicronautTest(startApplication = false)
class VelocityControllerIntegrationSpec extends VelocityControllerSpec {
    static final LOG_MAP = [
            'logback-json.xml': '"message":"Startup completed',
            'logback.xml': 'INFO  io.micronaut.runtime.Micronaut - Startup completed',
    ]

    void 'test log format'() {
        given:
        String expectedLog = LOG_MAP.find {
            System.getenv('JAVA_OPTS').contains(it.key)
        }.value

        when:
        String logs = Files.readString(Paths.get('logs-intg.txt'))

        then:
        logs.contains(expectedLog)
        logs.contains('Request POST /api/expandTemplate')
    }
}
