
package org.andtho.kotlin.web.restkotlin

import com.mongodb.annotations.ThreadSafe
import org.andtho.kotlin.web.restkotlin.person.Person
import org.junit.ClassRule
import org.junit.Test
import org.junit.runner.RunWith
import org.mongodb.morphia.Datastore
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.http.RequestEntity
import org.springframework.http.ResponseEntity
import org.springframework.test.context.junit4.SpringRunner
import java.net.URI
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

@ThreadSafe
@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RestKotlinApplicationTests {

	companion object {
		/*
		 * Instructs the Kotlin compiler not to generate getters/setters for this property
		 * and expose it as a field.
		 */
		@ClassRule @JvmField
		val mongoServer = MongoServerTestResource
	}

	// @get:Rule
	// val mongoServer = MongoServerPerTestResource

	@Autowired lateinit var restTemplate: TestRestTemplate
	@Autowired lateinit var datastore : Datastore

	@Test
	fun `get person by id`() {
		val key = datastore.save(Person(firstname = "test", lastname = "lastname"))

		val responseEntity = restTemplate.getForEntity("/person/${key.id}", Person::class.java)
		assertNotNull(responseEntity)
		assertEquals(200, responseEntity.statusCodeValue)
		val person = responseEntity.body
		assertEquals("test", person.firstname)
		assertEquals("lastname", person.lastname)
	}

	@Test
	fun `get list of people`() {
		// create testdata
		datastore.save(Person(firstname = "test1", lastname = "lastname1"))
		datastore.save(Person(firstname = "test2", lastname = "lastname2"))
		datastore.save(Person(firstname = "test3", lastname = "lastname2"))
		datastore.save(Person(firstname = "test4", lastname = "lastname2"))

		val requestEntity = RequestEntity<Any>(HttpMethod.GET, URI.create("/person"))

		// create typereference for response serialization
		class ListOfPeople : ParameterizedTypeReference<List<Person>>() // can this be done inline in the exchange method
		val responseEntity : ResponseEntity<List<Person>> = restTemplate.exchange(requestEntity, ListOfPeople())

		assertNotNull(responseEntity)
		assertEquals(200, responseEntity.statusCodeValue)
		assertTrue( responseEntity.body.size >= 4 )

		responseEntity.body.forEach { person ->
			println("Found person: [${person.firstname} ${person.lastname}] " +
					", born [${person.birthdate}]")
		}
	}

	@Test
	fun `create person by http post`() {
		val name = "Foo"
		val personUnderTest = Person(firstname = name, lastname = "Bar")

		val responseEntity = restTemplate.postForEntity("/person", personUnderTest, Person::class.java)
		assertNotNull(responseEntity)
		assertEquals(200, responseEntity.statusCodeValue)

		datastore.createQuery(Person::class.java)
				.filter("firstname", name)
				.asIterable()
				.forEach { person ->
					assertEquals("Foo", person.firstname)
					assertEquals("Bar", person.lastname)
					println("Found person with name: ${person.firstname} ${person.lastname} ")
				}
	}
}
