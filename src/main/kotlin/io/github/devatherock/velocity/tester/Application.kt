package io.github.devatherock.velocity.tester

import io.micronaut.runtime.Micronaut

/**
 * The application is defined in this way so we can easily reference
 * a class to pass to [start][io.micronaut.runtime.Micronaut.start].
 */
object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
                 .packages("")
                 .mainClass(Application.javaClass)
                 .start()
    }
}


