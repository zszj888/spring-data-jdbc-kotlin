package com.example.demo

import org.springframework.data.relational.core.mapping.Column

data class Status(
    val name: String
) {
    @Column("status_id")
    var id: Int? = null
}
