package org.andtho.kotlin.domain

import org.mongodb.morphia.annotations.Entity
import org.mongodb.morphia.annotations.Id
import java.time.LocalDate
import java.time.Period

@Entity
data class Person constructor(
                        @Id var id : String? = null,
                        val firstname : String = "",
                        val lastname : String = "",
                        val birthdate : LocalDate = LocalDate.now()
)