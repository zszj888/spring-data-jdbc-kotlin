package com.example.demo

import org.instancio.Instancio
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean


@SpringBootApplication
class DemoApplication {
    @Bean
    fun init(repository: ContactRepository): ApplicationRunner {
        return ApplicationRunner {
            repository.deleteAll()
            (1..1000).forEach()   { i ->
                repository.save(
                    Contact(null, "John$i", "Smith$i", "fdsfd$i@fds",
                        Status(null,"OK$i"),Company(null,"Google $i"))
                )
            }
        }
    }
}

fun main(args: Array<String>) {
    runApplication<DemoApplication>(*args)

}
