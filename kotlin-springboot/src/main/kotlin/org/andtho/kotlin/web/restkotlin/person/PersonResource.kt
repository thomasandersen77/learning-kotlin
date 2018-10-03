package org.andtho.kotlin.web.restkotlin.person

import org.andtho.kotlin.web.restkotlin.mongodb.PersonMongoDbRepository
import org.springframework.stereotype.Component
import javax.ws.rs.*

@Component
@Path("person")
class PersonResource constructor(val repository: PersonMongoDbRepository) {

    @GET
    @Path("{_id}")
    @Produces("application/json")
    fun getPerson(@PathParam("_id") id : String) : Person = repository.findById(id)

    @GET
    @Produces("application/json")
    fun getPersonList() : List<Person> {
        val personList = repository.findAll()
        return personList
    }

    @POST
    @Consumes("application/json")
    fun createPerson(person: Person) {
        repository.save(person)
    }

    @GET
    @Produces("application/json")
    fun getPersonByLastname(@QueryParam("lastname") lastname : String) : List<Person> {
        val people = repository.findByLastname(lastname)
        return people
    }
}