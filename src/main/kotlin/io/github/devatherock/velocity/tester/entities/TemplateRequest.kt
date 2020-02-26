package io.github.devatherock.velocity.tester.entities

/**
 * Request for template processing
 */
class TemplateRequest {
    var template: String = ""
    val parameters: Map<String, Any> = HashMap()
}