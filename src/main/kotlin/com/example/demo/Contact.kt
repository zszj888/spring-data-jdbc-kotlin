package com.example.demo

import org.springframework.data.annotation.Id

data class Contact(
    @Id
    var id: Int? = null,
    val firstName: String, val lastName: String, val email: String,
    val status: Status?, val company: Company?
)