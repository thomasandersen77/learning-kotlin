package org.andtho.kotlin.jaxrs

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.springframework.stereotype.Component
import javax.ws.rs.ext.ContextResolver
import javax.ws.rs.ext.Provider

@Component
@Provider
class MyObjectMapper : ContextResolver<ObjectMapper> {
    override fun getContext(p0: Class<*>?): ObjectMapper {
        return createObjectMapper()
    }

    companion object {
        fun createObjectMapper() : ObjectMapper {
            return ObjectMapper()
                    .registerModule(JavaTimeModule())
                    .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                    .findAndRegisterModules()
        }
    }
}