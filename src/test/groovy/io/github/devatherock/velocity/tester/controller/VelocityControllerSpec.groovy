package io.github.devatherock.velocity.tester.controller

import javax.inject.Inject

import groovy.json.JsonOutput

import org.yaml.snakeyaml.Yaml

import io.micronaut.http.HttpRequest
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

/**
 * Test class for {@link VelocityController}
 */
@MicronautTest
class VelocityControllerSpec extends Specification {

    @Inject
    @Client('/')
    HttpClient httpClient

    @Shared
    def requestWithParameters = [
            'template'  : 'Hello ${user}',
            'parameters': [
                    'user': 'World!'
            ]
    ]

    @Shared
    def requestWithoutParameters = [
            'template': 'Hello ${user}'
    ]

    @Unroll
    void 'test expand template - no parameters, content type - #contentType'() {
        when:
        String response = httpClient.toBlocking().retrieve(
                HttpRequest.POST('/api/expandTemplate', requestBody)
                        .contentType(contentType))

        then:
        response == 'Hello ${user}'

        where:
        requestBody << [
                JsonOutput.toJson(requestWithoutParameters),
                new Yaml().dump(requestWithoutParameters)
        ]
        contentType << [
                'application/json',
                'application/x-yaml'
        ]
    }

    @Unroll
    void 'test expand template - parameters present, content type - #contentType'() {
        when:
        String response = httpClient.toBlocking().retrieve(
                HttpRequest.POST('/api/expandTemplate', requestBody)
                        .contentType(contentType))

        then:
        response == 'Hello World!'

        where:
        requestBody << [
                JsonOutput.toJson(requestWithParameters),
                new Yaml().dump(requestWithParameters)
        ]
        contentType << [
                'application/json',
                'application/x-yaml'
        ]
    }

    @Unroll
    void 'test expand template - conditional template, condition: #isMorning'() {
        given:
        def request = [
                'template'  : '''
                    #if(${morning})
                      Good morning, ${user}
                    #else
                      Good evening, ${user}
                    #end  
                 ''',
                'parameters': [
                        'user'   : 'World!',
                        'morning': isMorning
                ]
        ]

        when:
        String response = httpClient.toBlocking().retrieve(
                HttpRequest.POST('/api/expandTemplate', JsonOutput.toJson(request))
                        .contentType('application/json'))

        then:
        response == expectedOutput

        where:
        isMorning << [
                'true',
                'false'
        ]
        expectedOutput << [
                'Good morning, World!',
                'Good evening, World!'
        ]
    }
}
