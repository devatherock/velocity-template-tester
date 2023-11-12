package io.github.devatherock.velocity.tester

import io.micronaut.runtime.Micronaut
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Contact
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.servers.Server

/**
 * The application is defined in this way so we can easily reference
 * a class to pass to [start][io.micronaut.runtime.Micronaut.start].
 */
@OpenAPIDefinition(
    info =
        Info(
            title = "Velocity Template Tester",
            version = "1.2.0",
            description = "Velocity Template Tester API",
            contact = Contact(url = "https://github.com/devatherock", name = "devatherock"),
        ),
    servers = [
        Server(
            url = "https://velocity-template-tester.onrender.com",
            description = "The server where the application is hosted",
        ),
    ],
)
object Application {
    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
            .packages("")
            .mainClass(Application::class.java)
            .start()
    }
}
