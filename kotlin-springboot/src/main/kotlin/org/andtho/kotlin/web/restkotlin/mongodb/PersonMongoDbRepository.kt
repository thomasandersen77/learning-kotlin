package org.andtho.kotlin.web.restkotlin.mongodb

import org.andtho.kotlin.web.restkotlin.person.Person
import org.springframework.data.repository.Repository


interface PersonMongoDbRepository : Repository<Person, String> {

    fun findByLastname(lastname : String) : List<Person>
    fun findAll() : List<Person>
    fun findById(id : String) : Person
    fun save(person: Person) : Person
}