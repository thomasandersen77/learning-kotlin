
package org.andtho.kotlin.web.restkotlin.person

import org.junit.Test
import java.time.LocalDate
import kotlin.test.assertEquals

class PersonTest {

    @Test
    fun test_age() {
        val person = Person(birthdate = LocalDate.now().minusYears(40))
        assertEquals(40, person.age())
    }
}