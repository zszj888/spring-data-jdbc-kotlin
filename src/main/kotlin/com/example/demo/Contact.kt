package com.example.demo

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import org.springframework.data.annotation.Id

data class Contact(
    @Id
    var id: Int? = null,
    val firstName: String, val lastName: String, @Email
    @NotEmpty val email: String, val status: Status?, val company: Company?
)

data class Status(
    @Id
    var id: Int? = null, val name: String
)

data class Company(
    @Id
    var id: Int? = null, val name: String
)
