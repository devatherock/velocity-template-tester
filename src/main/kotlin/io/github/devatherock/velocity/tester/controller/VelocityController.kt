package io.github.devatherock.velocity.tester.controller

import com.fasterxml.jackson.databind.ObjectMapper
import io.github.devatherock.velocity.tester.util.VelocityUtil
import io.micronaut.http.HttpHeaders
import io.micronaut.http.HttpResponse
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

    /**
     * Handles a JSON request payload
     *
     * @param request
     * @param headers
     * @return response
     */
    @Post("/expandTemplate", produces = [MediaType.TEXT_PLAIN, MediaType.TEXT_HTML], consumes = [MediaType.APPLICATION_JSON])
    fun expandJsonTemplate(@Body request: String, headers: HttpHeaders): HttpResponse<String> {
        var parsedRequest = objectMapper.readValue(request, Map::class.java)

        val result = VelocityUtil.expandTemplate(
            parsedRequest.get("template") as String,
            parsedRequest.get("parameters") as Map<String, Any>?
        )
        return HttpResponse.ok(result).contentType(contentType(headers))
    }

    /**
     * Handles a YAML request payload
     *
     * @param request
     * @param headers
     * @return response
     */
    @Post("/expandTemplate", produces = [MediaType.TEXT_PLAIN, MediaType.TEXT_HTML], consumes = [MediaType.APPLICATION_YAML])
    fun expandYamlTemplate(@Body request: String, headers: HttpHeaders): HttpResponse<String> {
        var parsedRequest = Yaml().load<Map<String, Any>>(request)

        val result = VelocityUtil.expandTemplate(
            parsedRequest.get("template") as String,
            parsedRequest.get("parameters") as Map<String, Any>?
        )
        return HttpResponse.ok(result).contentType(contentType(headers))
    }

    /**
     * Forms output {@code content-type} header based on input {@code accept} header
     *
     * @param headers
     * @return content type
     */
    private fun contentType(headers: HttpHeaders): MediaType {
        return if (headers.accept().contains(MediaType.TEXT_HTML_TYPE)) MediaType.TEXT_HTML_TYPE else MediaType.TEXT_PLAIN_TYPE
    }
}
