package org.andtho.kotlin.web.restkotlin.mongodb

import org.andtho.kotlin.web.restkotlin.person.Person
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
    @Test
    fun `find by id`() {
        repo.save(Person())
        repo.save(Person())
        repo.save(Person())
        repo.save(Person())

        val allPeople = repo.findAll()
        assertEquals(4, allPeople.size)
    }
}