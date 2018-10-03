package org.andtho.kotlin

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication


@SpringBootApplication
class RestKotlinApplication


fun main(args: Array<String>) {
    SpringApplication.run(RestKotlinApplication::class.java, *args)
}