package it.nowicki.jaroslaw

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class KibanaApplication

fun main(args: Array<String>) {
    SpringApplication.run(KibanaApplication::class.java, *args)
}
