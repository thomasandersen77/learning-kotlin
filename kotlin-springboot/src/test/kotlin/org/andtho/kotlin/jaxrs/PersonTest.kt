
package org.andtho.kotlin.jaxrs

import org.andtho.kotlin.domain.Person
import org.junit.Test
import java.time.LocalDate

class PersonTest {

    @Test
    fun test_age() {
        val person = Person(birthdate = LocalDate.now().minusYears(40))
        //assertEquals(40, person.age())
    }
}