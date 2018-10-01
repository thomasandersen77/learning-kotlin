package org.andtho.kotlin.web.restkotlin.collections

import org.andtho.kotlin.web.restkotlin.collections.CollectionTest.Element.Companion.create
import org.junit.Test
import java.util.concurrent.atomic.LongAdder

class CollectionTest {

    data class Element<T>(val id : Long, val value : T?) {
        fun hasValue() : Boolean {
            return value != null
        }

        companion object {
            private val counter = LongAdder()
            @JvmStatic
            fun <T> create(value : T) : Element<T> {
                counter.increment()
                return Element(id = counter.sum(), value = value)
            }
        }
    }

    @Test
    fun `iterate list and transform values`() {
        val value = "content"
        val list = arrayListOf(create(value), create(value), create(value), create(null), create(value))
                .asSequence()
                .distinct()
                .filter { it -> it.hasValue() }
                .map { it.id }
                .sortedDescending()
                .toList()
        println(list)
    }
}