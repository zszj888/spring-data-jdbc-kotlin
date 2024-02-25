package com.example.demo

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("contact")
data class PlainContact(
    @Id
    var id: Int? = null, val firstName: String, val lastName: String, val email: String
)