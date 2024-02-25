package com.example.demo

import org.springframework.data.relational.core.mapping.Column

data class Company(
    val name: String,
    @Column("company_id")
    private var id: Int?
)
