package org.andtho.kotlin.web.restkotlin.person

import org.mongodb.morphia.annotations.Entity
import org.mongodb.morphia.annotations.Id
import java.time.LocalDate
import java.time.Period

@Entity
class Person constructor(@Id var id : String? = null,
                        val firstname : String = "",
                        val lastname : String = "",
                        val birthdate : LocalDate = LocalDate.now()
            ) {

    constructor() : this(null)

    /* functional methods */

    fun age() : Int = Period.between(birthdate, LocalDate.now()).years

    fun birthYear() : Int = birthdate.year
}