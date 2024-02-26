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
            repository.save(Instancio.create(Contact::class.java).apply { id = null })
        }
    }
}

fun main(args: Array<String>) {
    runApplication<DemoApplication>(*args)

}
