package org.andtho.kotlin.domain

enum class City {
    OSLO,
    DRAMMEN,
    KONGSBERG,
    TØNSBERG,
    TRONDHEIM,
    HOKKSUND,
    MOSS;

    fun pretty() : String {
        val toLowerCase = this.name
                .toLowerCase()
        return toLowerCase
                .substring(0, 1)
                .toUpperCase() + name.substring(1, name.length)
    }
}
