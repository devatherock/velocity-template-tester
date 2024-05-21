package io.github.devatherock.velocity.tester.util

import org.apache.velocity.VelocityContext
import org.apache.velocity.app.Velocity
import org.apache.velocity.tools.generic.DateTool
import org.apache.velocity.tools.generic.EscapeTool
import org.apache.velocity.tools.generic.JsonTool
import org.apache.velocity.tools.generic.LogTool
import org.apache.velocity.tools.generic.MathTool
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
        fun expandTemplate(
            template: String,
            parameters: Map<String, Any>?,
        ): String {
            var writer = StringWriter()
            var context = VelocityContext()

            // Add tools to context
            context.put("date", DateTool())
            context.put("esc", EscapeTool())
            context.put("json", JsonTool())
            context.put("math", MathTool())
            context.put("log", LogTool())

            if (null != parameters) {
                for ((key, value) in parameters) {
                    context.put(key, value)
                }
            }
            Velocity.evaluate(context, writer, "", template)

            return writer.toString().trim()
        }
    }
}
