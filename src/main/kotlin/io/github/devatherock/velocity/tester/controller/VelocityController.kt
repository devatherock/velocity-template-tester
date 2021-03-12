package io.github.devatherock.velocity.tester.controller

import com.fasterxml.jackson.databind.ObjectMapper
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
    val objectMapper = ObjectMapper()

    @Post("/expandTemplate", produces = [MediaType.TEXT_PLAIN], consumes = [MediaType.APPLICATION_JSON])
    fun expandJsonTemplate(@Body request: String): String {
        var parsedRequest = objectMapper.readValue(request, Map::class.java)
        return VelocityUtil.expandTemplate(parsedRequest.get("template") as String,
                parsedRequest.get("parameters") as Map<String, Any>?)
    }

    @Post("/expandTemplate", produces = [MediaType.TEXT_PLAIN], consumes = [MediaType.APPLICATION_YAML])
    fun expandYamlTemplate(@Body request: String): String {
        var parsedRequest = Yaml().load<Map<String, Any>>(request)
        return VelocityUtil.expandTemplate(parsedRequest.get("template") as String,
                parsedRequest.get("parameters") as Map<String, Any>?)
    }
}