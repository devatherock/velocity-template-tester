package io.github.devatherock.velocity.tester.controller

import io.github.devatherock.velocity.tester.entities.TemplateRequest
import io.github.devatherock.velocity.tester.util.VelocityUtil
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Consumes
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import org.yaml.snakeyaml.Yaml

/**
 * Controller to process the templates
 */
@Controller("/api")
class VelocityController {

    @Consumes(MediaType.APPLICATION_JSON)
    @Post("/expandTemplate")
    fun expandJsonTemplate(@Body request: TemplateRequest): String {
        return VelocityUtil.expandTemplate(request.template, request.parameters)
    }

    @Consumes(MediaType.APPLICATION_YAML)
    @Post("/expandTemplate")
    fun expandYamlTemplate(@Body request: String): String {
        var parsedRequest = Yaml().load<Any>(request) as Map<String, Any>
        return VelocityUtil.expandTemplate(parsedRequest.get("template") as String,
                parsedRequest.get("parameters") as Map<String, Any>)
    }
}