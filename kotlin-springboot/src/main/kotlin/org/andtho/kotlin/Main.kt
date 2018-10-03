package org.andtho.kotlin

import com.mongodb.MongoClient
import org.andtho.kotlin.domain.Person
import org.andtho.kotlin.mongodb.PersonMongoDbRepository
import org.mongodb.morphia.Datastore
import org.mongodb.morphia.Morphia
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@Configuration
@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = [PersonMongoDbRepository::class])
class RestKotlinApplication {

    @Autowired
    lateinit var env : Environment

    @Bean
    fun datastore() : Datastore {
        val morphia = Morphia()
        morphia.map(Person::class.java)
        // override this bean for dynamic port
        val mongoClient = MongoClient( env.getProperty("mongodb.host"),  env.getProperty("mongodb.port").toInt() )
        return morphia.createDatastore(mongoClient, "test")
    }
}


fun main(args: Array<String>) {
    SpringApplication.run(RestKotlinApplication::class.java, *args)
}