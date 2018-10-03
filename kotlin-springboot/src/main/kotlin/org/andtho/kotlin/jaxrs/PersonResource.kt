package org.andtho.kotlin.jaxrs

import org.andtho.kotlin.domain.Person
import org.andtho.kotlin.mongodb.PersonMongoDbRepository
import org.springframework.stereotype.Component
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Component
@Path("person")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class PersonResource constructor(val repository: PersonMongoDbRepository) {

    @GET
    @Path("{_id}")
    fun getPerson(@PathParam("_id") id : String) : Person {
        return repository.findById(id)
    }

    @GET
    fun getPersonList() : List<Person> {
        val personList = repository.findAll()
        return personList
    }

    @POST
    fun createPerson(person: Person) {
        repository.save(person)
    }

    @GET
    fun getPersonByLastname(@QueryParam("lastname") lastname : String) : List<Person> {
        val people = repository.findByLastname(lastname)
        return people
    }
}