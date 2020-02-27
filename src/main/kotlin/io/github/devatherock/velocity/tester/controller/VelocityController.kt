package io.github.devatherock.velocity.tester.controller

import io.github.devatherock.velocity.tester.entities.TemplateRequest
import io.github.devatherock.velocity.tester.util.VelocityUtil
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import org.yaml.snakeyaml.Yaml

/**
 * Controller to process the templates
 */
@Controller("/api")
class VelocityController {

    @Post("/expandTemplate", produces = [ MediaType.TEXT_PLAIN ], consumes = [ MediaType.APPLICATION_JSON ])
    fun expandJsonTemplate(@Body request: TemplateRequest): String {
        return VelocityUtil.expandTemplate(request.template, request.parameters)
    }

    @Post("/expandTemplate", produces = [ MediaType.TEXT_PLAIN ], consumes = [ MediaType.APPLICATION_YAML ])
    fun expandYamlTemplate(@Body request: String): String {
        var parsedRequest = Yaml().load<Map<String, Any>>(request)
        return VelocityUtil.expandTemplate(parsedRequest.get("template") as String,
                parsedRequest.get("parameters") as Map<String, Any>)
    }
}