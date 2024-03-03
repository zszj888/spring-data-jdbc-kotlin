package com.example.demo

import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.jdbc.core.convert.EntityRowMapper
import org.springframework.data.jdbc.core.convert.JdbcConverter
import org.springframework.data.jdbc.core.mapping.JdbcMappingContext
import org.springframework.jdbc.core.RowMapper


@SpringBootApplication
class DemoApplication {
    @Bean
    fun init(repository: ContactRepository): ApplicationRunner {
        return ApplicationRunner {
            repository.deleteAll()
            (1..10).forEach() { i ->
                repository.save(
                    Contact(
                        null, "John$i", "Smith$i", "fdsfd$i@fds",
                        Status(null, "OK$i"), Company(null, "Google $i")
                    )
                )
            }
        }
    }

    @Bean
    fun rowMapper(jdbcConverter: JdbcConverter, jdbcMappingContext: JdbcMappingContext,): RowMapper<Contact> {
        val requiredPersistentEntity = jdbcMappingContext.getRequiredPersistentEntity(Contact::class.java)
        return EntityRowMapper<Contact>(requiredPersistentEntity, jdbcConverter);
    }
}

fun main(args: Array<String>) {
    runApplication<DemoApplication>(*args)

}
