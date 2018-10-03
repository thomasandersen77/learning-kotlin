package org.andtho.kotlin.domain

import org.mongodb.morphia.annotations.Entity

@Entity
class Address constructor(val street : String, val zicode : String, val city : City) {
    override fun toString(): String {
        return "$street, $zicode ${city.pretty()}"
    }
}