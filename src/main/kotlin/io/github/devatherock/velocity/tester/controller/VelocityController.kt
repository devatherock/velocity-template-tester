package io.github.devatherock.velocity.tester.controller

import com.fasterxml.jackson.databind.ObjectMapper
import io.github.devatherock.velocity.tester.model.ExpandTemplateRequest
import io.github.devatherock.velocity.tester.util.VelocityUtil
import io.micronaut.http.HttpHeaders
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.yaml.snakeyaml.Yaml

/**
 * Controller to process the templates
 */
@Controller("/api")
class VelocityController {
    val objectMapper = ObjectMapper()

    /**
     * Accepts a JSON request and expands the supplied Velocity template
     *
     * @param request
     * @param headers
     * @return response the expanded template string/html
     */
    @Post(
        "/expandTemplate",
        produces = [MediaType.TEXT_PLAIN, MediaType.TEXT_HTML],
        consumes = [MediaType.APPLICATION_JSON],
    )
    @Operation(
        summary = "expandJsonTemplate",
        description = "Accepts a JSON request and expands the supplied Velocity template",
        responses = [
            ApiResponse(
                description = "the expanded template string/html",
                content = [
                    Content(mediaType = MediaType.TEXT_PLAIN, schema = Schema(implementation = String::class, example = "Hello foo")),
                    Content(
                        mediaType = MediaType.TEXT_HTML,
                        schema = Schema(implementation = String::class, example = "<html>Hello!</html>"),
                    ),
                ],
            ),
        ],
    )
    fun expandJsonTemplate(
        @Body request: ExpandTemplateRequest,
        headers: HttpHeaders,
    ): HttpResponse<String> {
        val result =
            VelocityUtil.expandTemplate(
                request.template,
                request.parameters,
            )
        return HttpResponse.ok(result).contentType(contentType(headers))
    }

    /**
     * Accepts a YAML request and expands the supplied Velocity template
     *
     * @param request
     * @param headers
     * @return response the expanded template string/html
     */
    @Post(
        "/expandTemplate",
        produces = [MediaType.TEXT_PLAIN, MediaType.TEXT_HTML],
        consumes = [MediaType.APPLICATION_YAML],
    )
    @Operation(
        summary = "expandYamlTemplate",
        description = "Accepts a YAML request and expands the supplied Velocity template",
        responses = [
            ApiResponse(
                description = "the expanded template string/html",
                content = [
                    Content(mediaType = MediaType.TEXT_PLAIN, schema = Schema(implementation = String::class, example = "Hello foo")),
                    Content(
                        mediaType = MediaType.TEXT_HTML,
                        schema = Schema(implementation = String::class, example = "<html>Hello!</html>"),
                    ),
                ],
            ),
        ],
    )
    fun expandYamlTemplate(
        @Body request: String,
        headers: HttpHeaders,
    ): HttpResponse<String> {
        var parsedRequest = Yaml().load<Map<String, Any>>(request)

        val result =
            VelocityUtil.expandTemplate(
                parsedRequest.get("template") as String,
                parsedRequest.get("parameters") as Map<String, Any>?,
            )
        return HttpResponse.ok(result).contentType(contentType(headers))
    }

    /**
     * Forms output {@code content-type} header based on input {@code accept} header
     *
     * @param headers
     * @return content type
     */
    private fun contentType(headers: HttpHeaders): MediaType =
        if (headers
                .accept()
                .contains(MediaType.TEXT_HTML_TYPE)
        ) {
            MediaType.TEXT_HTML_TYPE
        } else {
            MediaType.TEXT_PLAIN_TYPE
        }
}
