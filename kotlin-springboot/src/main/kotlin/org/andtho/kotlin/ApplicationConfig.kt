package org.andtho.kotlin

import org.andtho.kotlin.jaxrs.MyExceptionMapper
import org.andtho.kotlin.jaxrs.MyObjectMapper
import org.andtho.kotlin.jaxrs.PersonResource
import org.andtho.kotlin.jaxrs.RequestResponseFilter
import org.glassfish.jersey.logging.LoggingFeature
import org.glassfish.jersey.server.ResourceConfig
import org.springframework.stereotype.Component
import java.util.logging.Level
import java.util.logging.Logger

@Component
class JerseyConfig : ResourceConfig() {

    init {
        registerEndpoints()
    }

    final fun registerEndpoints() {
        register(PersonResource::class.java)
        register(MyObjectMapper::class.java)
        register(MyExceptionMapper::class.java)
        register(RequestResponseFilter::class.java)
        register(LoggingFeature(Logger.getLogger(LoggingFeature.DEFAULT_LOGGER_NAME),
                Level.FINE,
                LoggingFeature.Verbosity.PAYLOAD_ANY,
                Integer.MAX_VALUE)
        )
    }
}

