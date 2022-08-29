package io.github.devatherock.velocity.tester.model

import io.swagger.v3.oas.annotations.media.Schema

/**
 * Represents a request to expand a template
 */
@Schema(name = "ExpandTemplateRequest", description = "Represents a request to expand a template")
class ExpandTemplateRequest {
    /**
     * The Velocity template to expand
     */
    @Schema(description = "The Velocity template to expand", example = "Hello \${user}")
    lateinit var template: String

    /**
     * Parameters to use to expand the template
     */
    @Schema(description = "Parameters to use to expand the template")
    val parameters: Map<String, Any>? = null
}
