package org.andtho.kotlin.mongodb

import org.andtho.kotlin.domain.Address
import org.andtho.kotlin.domain.City
import org.andtho.kotlin.domain.Person
import org.junit.ClassRule
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import kotlin.test.assertEquals

@RunWith(SpringRunner::class)
@SpringBootTest
class PersonMongoDbRepositoryTest {

    companion object {
        @ClassRule
        @JvmField
        val mongoServer = MongoServerTestResource
    }

    @Autowired lateinit var repo: PersonMongoDbRepository

/*    @Test
    fun `find by id`() {
        repo.save(Person(address = Address(street = "Stryken 45", zicode = "3300", city = City.HOKKSUND)))
        repo.save(Person(address = Address(street = "Loesmoen", zicode = "3300", city = City.HOKKSUND)))
        repo.save(Person(address = Address(street = "Drammensveien 3", zicode = "0103", city = City.OSLO)))
        repo.save(Person(address = Address(street = "Per Sivles gate 1", zicode = "3022", city = City.DRAMMEN)))

        val allPeople = repo.findAll()
        assertEquals(4, allPeople.size)
        allPeople.forEach { it -> println(it) }
        
        val filteredPeopleList = allPeople
                .map { person -> person.address }
                .filter { address -> address?.city == City.HOKKSUND }
        assertEquals(2, filteredPeopleList.size)
        filteredPeopleList.forEach { println(it) }
    }*/
}