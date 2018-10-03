package org.andtho.kotlin.jaxrs

import org.springframework.stereotype.Component
import java.lang.Exception
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider

@Provider
@Component
class MyExceptionMapper : ExceptionMapper<Exception>{
    override fun toResponse(p0: Exception?): Response {
        p0?.printStackTrace()
        return Response.status(500).entity(p0?.message).build()
    }
}