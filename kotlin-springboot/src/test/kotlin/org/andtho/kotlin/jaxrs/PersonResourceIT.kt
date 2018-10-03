package org.andtho.kotlin.jaxrs

import org.andtho.kotlin.domain.Person
import org.andtho.kotlin.mongodb.MongoServerTestResource
import org.andtho.kotlin.mongodb.PersonMongoDbRepository
import org.glassfish.jersey.client.JerseyClientBuilder
import org.junit.Before
import org.junit.ClassRule
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.RequestEntity
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.test.context.junit4.SpringRunner
import java.net.URI
import javax.ws.rs.core.GenericType
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PersonResourceIT {

    companion object {
        @ClassRule @JvmField
        val mongoServer = MongoServerTestResource
    }

    @LocalServerPort
    var port : Int = 0

    @Autowired
    lateinit var datastore: PersonMongoDbRepository
    @Autowired
    lateinit var restTemplate : TestRestTemplate

/*    @Before
    fun setMessageConverter() {
        println("******************* port: $port *********************")
        val messageConverters = restTemplate.restTemplate.messageConverters
        val converter = MappingJackson2HttpMessageConverter()
        converter.supportedMediaTypes = arrayListOf(MediaType.ALL)
        messageConverters.add(converter)
    }*/

    @Test
    fun `test resource with jersey client`() {
        // create testdata
        datastore.save(Person(firstname = "test1", lastname = "lastname1"))
        datastore.save(Person(firstname = "test2", lastname = "lastname2"))
        datastore.save(Person(firstname = "test3", lastname = "lastname2"))
        datastore.save(Person(firstname = "test4", lastname = "lastname2"))

        val client = JerseyClientBuilder.newClient()
                .register(MyObjectMapper.createObjectMapper())

        val response = client.target("http://localhost:$port/person")
                .request()
                .accept(javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE)
                .buildGet()
                .invoke()

        assertEquals(200, response.status)
        val entity = response.readEntity(object : GenericType<List<Person>>() {})
        entity.map { person -> person.lastname }.forEach { println(it) }
    }

    @Test
    fun `get person by id`() {
        val key = datastore.save(Person(firstname = "test", lastname = "lastname"))

        val responseEntity = restTemplate.getForEntity("/person/${key.id}", Person::class.java)
        assertNotNull(responseEntity)
        assertEquals(200, responseEntity.statusCodeValue)
        val person = responseEntity.body
        assertEquals("test", person?.firstname)
        assertEquals("lastname", person?.lastname)
    }

    @Test
    fun `get list of people`() {

        // create testdata
        datastore.save(Person(firstname = "test1", lastname = "lastname1"))
        datastore.save(Person(firstname = "test2", lastname = "lastname2"))
        datastore.save(Person(firstname = "test3", lastname = "lastname2"))
        datastore.save(Person(firstname = "test4", lastname = "lastname2"))

        val requestEntity = RequestEntity<Any>(HttpMethod.GET, URI.create("/person"))

        val responseEntity = restTemplate.exchange(requestEntity, object : ParameterizedTypeReference<List<Person>>() {})

        assertNotNull(responseEntity)
        assertEquals(200, responseEntity.statusCodeValue)
        assertTrue(responseEntity.body.size >= 4)

        responseEntity.body.forEach { person ->
            println("Found resources: [${person.firstname} ${person.lastname}] " +
                    ", born [${person.birthdate}]")
        }
    }

    @Test
    fun `create person by http post`() {
        val name = "Foo"
        val personUnderTest = Person(firstname = name, lastname = "Bar")

        val responseEntity = restTemplate.postForEntity("/person", personUnderTest, Person::class.java)
        assertNotNull(responseEntity)
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.statusCode)

        datastore.findAll().stream()
                .filter { it -> it.lastname.equals("Bar") }
                .forEach { person ->
                    assertEquals("Foo", person.firstname)
                    assertEquals("Bar", person.lastname)
                    println("Found person with name: ${person.firstname} ${person.lastname} ")
                }
    }

    @Test
    fun `get list of people by lastname`() {

        // create testdata
        datastore.save(Person(firstname = "test1", lastname = "lastname1"))
        datastore.save(Person(firstname = "test2", lastname = "lastname2"))
        datastore.save(Person(firstname = "test3", lastname = "lastname2"))
        datastore.save(Person(firstname = "test4", lastname = "lastname2"))


    }
}


