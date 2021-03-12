package io.github.devatherock.velocity.tester.util

import org.apache.velocity.VelocityContext
import org.apache.velocity.app.Velocity
import java.io.StringWriter

/**
 * Utility class that performs the template processing
 */
class VelocityUtil {
    companion object {
        /**
         * Expands processes the supplied template with the provided parameters
         *
         * @param template the template text to process
         * @param parameters the list of parameters to process the template with
         */
        fun expandTemplate(template: String, parameters: Map<String, Any>?): String {
            var writer = StringWriter()
            var context = VelocityContext()

            if(null != parameters) {
                for ((key, value) in parameters) {
                    if ("true" == value || "false" == value) {
                        context.put(key, value.toBoolean())
                    } else {
                        context.put(key, value)
                    }
                }
            }
            Velocity.evaluate(context, writer, "", template)

            return writer.toString().trim()
        }
    }
}