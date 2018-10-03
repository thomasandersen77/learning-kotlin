package org.andtho.kotlin

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.mongodb.MongoClient
import org.andtho.kotlin.mongodb.PersonMongoDbRepository
import org.andtho.kotlin.domain.Person
import org.andtho.kotlin.resources.PersonResource
import org.glassfish.jersey.server.ResourceConfig
import org.mongodb.morphia.Datastore
import org.mongodb.morphia.Morphia
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories
import org.springframework.stereotype.Component
import javax.ws.rs.ext.ContextResolver
import javax.ws.rs.ext.Provider

@Component
final class JerseyConfig : ResourceConfig() {

    init {
        registerEndpoints()
    }

    fun registerEndpoints() {
        register(PersonMongoDbRepository::class.java)
        register(PersonResource::class.java)
        register(MyObjectMapper::class.java)
    }
}

@Configuration
@EnableMongoRepositories(basePackageClasses = [PersonMongoDbRepository::class])
class MongoDbrepository {

}

@Component
@Provider
class MyObjectMapper : ContextResolver<ObjectMapper> {
    override fun getContext(p0: Class<*>?): ObjectMapper {
        return createObjectMapper()
    }

    companion object {
        fun createObjectMapper() : ObjectMapper {
            return ObjectMapper()
                    .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true)
                    .findAndRegisterModules()
        }
    }
}


@Component
@Configuration
class ConfigureMorphia @Autowired constructor(val env : Environment) {
    @Bean
    fun datastore() : Datastore {
        val morphia = Morphia()
        morphia.map(Person::class.java)
        // override this bean for dynamic port
        val mongoClient = MongoClient( env.getProperty("mongodb.host"),  env.getProperty("mongodb.port").toInt() )
        return morphia.createDatastore(mongoClient, "test")
    }
}