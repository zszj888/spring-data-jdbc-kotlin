package com.example.demo

import org.springframework.data.repository.ListCrudRepository

interface StatusRepository : ListCrudRepository<Status, Long>