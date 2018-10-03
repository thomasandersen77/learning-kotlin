package org.andtho.kotlin.jaxrs

import org.springframework.stereotype.Component
import javax.ws.rs.container.*
import javax.ws.rs.ext.Provider

@Provider
@Component
@PreMatching
class RequestResponseFilter : ContainerRequestFilter, ContainerResponseFilter {
    override fun filter(req: ContainerRequestContext?) {
        println("${req?.method} ${req?.mediaType}")
    }

    override fun filter(req: ContainerRequestContext?, res: ContainerResponseContext?) {
        println("${res?.entityClass}")
    }
}