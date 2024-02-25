package com.example.demo

import org.springframework.data.repository.ListCrudRepository

interface PlainContactRepository : ListCrudRepository<PlainContact, Long>