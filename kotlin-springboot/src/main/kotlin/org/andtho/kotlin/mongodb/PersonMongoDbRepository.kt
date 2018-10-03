package org.andtho.kotlin.mongodb

import org.andtho.kotlin.domain.Person
import org.springframework.data.repository.Repository


interface PersonMongoDbRepository : Repository<Person, String> {

    fun findByLastname(lastname : String) : List<Person>
    fun findAll() : List<Person>
    fun findById(id : String) : Person
    fun save(person: Person) : Person
}