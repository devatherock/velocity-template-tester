package io.github.devatherock.velocity.tester.controller

import javax.inject.Inject

import groovy.json.JsonOutput

import org.yaml.snakeyaml.Yaml

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

/**
 * Test class for {@link VelocityController}
 */
abstract class VelocityControllerSpec extends Specification {

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
            'template': '<html><body>Hello ${user}</body></html>'
    ]

    @Unroll
    void 'test expand template - no parameters, content type - #contentType'() {
        when:
        HttpResponse response = httpClient.toBlocking().exchange(
                HttpRequest.POST('/api/expandTemplate', requestBody)
                        .contentType(contentType)
                        .accept(acceptHeader), String
        )

        then:
        response.body() == '<html><body>Hello ${user}</body></html>'
        response.header('content-type') == outputContentType

        where:
        requestBody << [
                JsonOutput.toJson(requestWithoutParameters),
                JsonOutput.toJson(requestWithoutParameters),
                JsonOutput.toJson(requestWithoutParameters),
                new Yaml().dump(requestWithoutParameters),
                new Yaml().dump(requestWithoutParameters),
                new Yaml().dump(requestWithoutParameters),
        ]
        contentType << [
                'application/json',
                'application/json',
                'application/json',
                'application/x-yaml',
                'application/x-yaml',
                'application/x-yaml',
        ]
        acceptHeader << [
                'text/plain',
                'text/html',
                '*/*',
                'text/plain',
                'text/html',
                '*/*',
        ]
        outputContentType << [
                'text/plain',
                'text/html',
                'text/plain',
                'text/plain',
                'text/html',
                'text/plain',
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
                'false',
                true,
                false
        ]
        expectedOutput << [
                'Good morning, World!',
                'Good morning, World!',
                'Good morning, World!',
                'Good evening, World!'
        ]
    }

    @Unroll
    void 'test expand template - #tool tool'() {
        given:
        def request = [
                'template': template
        ]

        when:
        String response = httpClient.toBlocking().retrieve(
                HttpRequest.POST('/api/expandTemplate', JsonOutput.toJson(request))
                        .contentType('application/json'))

        then:
        response == expectedOutput

        where:
        tool   | expectedOutput
        'esc'  | '<img src="http://logos.com/my-logo.png?label=small+size">'
        'json' | 'value'
        'math' | '10'
        'log'  | 'hola'

        and:
        template << [
                '''<img src="http://logos.com/my-logo.png?label=$esc.url('small size')">''',
                '''$json.parse('{"key":"value"}').key''',
                '$math.max(10, 5)',
                '''
                $log.info("Hello World!")
                hola
                '''.stripIndent().trim(),
        ]
    }

    void 'test expand template - date tool'() {
        given:
        def request = [
                'template': '''$date.get('iso')'''
        ]

        when:
        String response = httpClient.toBlocking().retrieve(
                HttpRequest.POST('/api/expandTemplate', JsonOutput.toJson(request))
                        .contentType('application/json'))

        then:
        response =~ '[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}'
    }
}
